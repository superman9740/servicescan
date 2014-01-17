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
    
    [self.view addSubview:cameraViewController.view];
    
    
    
    
    
    
}

-(void)didCaptureQRCode:(NSString*)code
{
    NSInteger userType = [[AppController sharedInstance] userType];
    
    [[AppController sharedInstance] setQrCode:code];
    switch (userType)
    {
        case 1:
        {
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
- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
