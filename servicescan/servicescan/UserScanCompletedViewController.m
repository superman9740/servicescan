//
//  UserScanCompletedViewController.m
//  servicescan
//
//  Created by sdickson on 1/17/14.
//  Copyright (c) 2014 ambro. All rights reserved.
//

#import "UserScanCompletedViewController.h"

@interface UserScanCompletedViewController ()

@end

@implementation UserScanCompletedViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
	[self.view setBackgroundColor:[UIColor colorWithPatternImage:[UIImage imageNamed:@"bg.png"]]];
    self.title = @"Scan Details";
    
    
    ServiceScan* serviceScan = [[AppController sharedInstance] serviceScan];
    _contractorName.text = [[[AppController sharedInstance] contractor] firstName];
    _contractorAddress.text =  [[[AppController sharedInstance] contractor] address];
    
    _applianceModel.text =  serviceScan.applianceModel;

       
}

-(IBAction)requestService:(id)sender
{
    
    NSString* urlString = [NSString stringWithFormat:@"http://servicescans.com:8080/ServiceScanServerSide/CreateNewRequest?qrCode=%@",[[AppController sharedInstance] qrCode]];
    
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
    
    
    
    
    UIAlertView* alertView = [[UIAlertView alloc] initWithTitle:@"Request Service" message:@"Your service request has been sent to the contractor." delegate:self cancelButtonTitle:@"OK" otherButtonTitles:nil];
    [alertView show];
    
}

- (BOOL)textFieldShouldReturn:(UITextField *)textField
{
    [textField resignFirstResponder];
    
    return YES;
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
