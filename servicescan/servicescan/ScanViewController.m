//
//  ScanViewController.m
//  servicescan
//
//  Created by sdickson on 1/14/14.
//  Copyright (c) 2014 ambro. All rights reserved.
//

#import "ScanViewController.h"
#import "UIImage+iPhone5.h"
#import "Utils.h"
#import "AppController.h"


@interface ScanViewController ()

@end

@implementation ScanViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
    self.title = @"Scan";
    
}
-(void)viewWillAppear:(BOOL)animated
{
    [self.view setBackgroundColor:[UIColor colorWithPatternImage:[UIImage imageNamed:@"bg.png"]]];
    
    NSInteger userType = [[AppController sharedInstance] userType];
    if(userType == 0)
    {
   
        [self performSegueWithIdentifier:@"showUserType" sender:self];

    }
    
   
    
    
       
}

-(IBAction)showScanner:(id)sender
{
    
    
    cameraViewController = [self.storyboard instantiateViewControllerWithIdentifier:@"camera"];
    cameraViewController.delegate = self;
    UIBarButtonItem* barButtonItem = [[UIBarButtonItem alloc] initWithBarButtonSystemItem:UIBarButtonSystemItemDone target:self action:@selector(closeScanner:)];
    self.navigationItem.leftBarButtonItem = barButtonItem;
    [self.view addSubview:cameraViewController.view];
    
    
    
    
    
    
}
-(IBAction)closeScanner:(id)sender
{
    [cameraViewController.view removeFromSuperview];
    
    
}

-(void)didCaptureQRCode:(NSString*)code
{
    [[AppController sharedInstance] setQrCode:code];
    
    
    
    NSInteger userType = [[AppController sharedInstance] userType];
    
    
    [[AppController sharedInstance] setQrCode:code];
    switch (userType)
    {
        case 1:
        {
            
            
            NSString* urlString = [NSString stringWithFormat:@"http://servicescans.com:8080/ServiceScanServerSide/GetScanData?qrCode=%@",[[AppController sharedInstance] qrCode]];
            
            NSURL* url = [NSURL URLWithString:urlString];
            
            NSError* error = nil;
            NSURLResponse* response = nil;
            NSURLRequest* request = [NSURLRequest requestWithURL:url];
            
            NSData* jsonData = [NSURLConnection sendSynchronousRequest:request returningResponse:&response error:&error];
            if(error.code == -1004)
            {
                
                
                UIAlertView* alertView = [[UIAlertView alloc] initWithTitle:@"Service Error" message:@"There was an error connecting to the server.  Please try again." delegate:self cancelButtonTitle:@"OK" otherButtonTitles:nil];
                [alertView show];
                return;
                
            }
            
            NSString* jsonString = [[NSString alloc] initWithData:jsonData encoding:NSUTF8StringEncoding];
            
            if([jsonString isEqualToString:@"-1\n"])
            {
                UIAlertView* alertView = [[UIAlertView alloc] initWithTitle:@"Service Error" message:@"That QR code doesn't seem to be associated with a service contractor.  Please try again." delegate:self cancelButtonTitle:@"OK" otherButtonTitles:nil];
                [alertView show];
                return;
                
                
            }
            NSDictionary* dict = [NSJSONSerialization JSONObjectWithData:jsonData options:0 error:&error];
            
            NSString* contractorFirstName = [dict valueForKey:@"contractorFirstName"];
            
            NSString* contractorAddress = [dict valueForKey:@"contractorAddress"];
            
            NSString* applianceModel = [dict valueForKey:@"applianceModel"];
            ServiceScan* serviceScan = [[AppController sharedInstance] serviceScan];
            serviceScan.contractorFirstName = contractorFirstName;
            serviceScan.contractorAddress = contractorAddress;
            serviceScan.applianceModel = applianceModel;
            
            [self performSegueWithIdentifier:@"showUserScanCompleted" sender:self];
           
            break;
        }
        case 2:
        {
            [self performSegueWithIdentifier:@"showContractorScanCompleted" sender:self];
            break;
            
        }
            
            
        default:
            break;
    }
    
}

-(IBAction)resetUserType:(id)sender
{
    [self performSegueWithIdentifier:@"showUserType" sender:self];
    
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
