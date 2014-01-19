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
    
    
    
    NSString* urlString = [NSString stringWithFormat:@"http://10.0.0.4:8080/ServiceScanServerSide/GetScanData?qrCode=%@",[[AppController sharedInstance] qrCode]];
    
    NSURL* url = [NSURL URLWithString:urlString];
    
    NSError* error = nil;
    NSURLResponse* response = nil;
    NSURLRequest* request = [NSURLRequest requestWithURL:url];
    
    NSData* jsonData = [NSURLConnection sendSynchronousRequest:request returningResponse:&response error:&error];
   
    NSString* jsonString = [[NSString alloc] initWithData:jsonData encoding:NSUTF8StringEncoding];
    
    NSDictionary* dict = [NSJSONSerialization JSONObjectWithData:jsonData options:0 error:&error];
    
    NSString* contractorFirstName = [dict valueForKey:@"contractorFirstName"];
    
    NSString* contractorAddress = [dict valueForKey:@"contractorAddress"];
    
    NSString* applianceModel = [dict valueForKey:@"applianceModel"];
    
    _contractorName.text = contractorFirstName;
    _contractorAddress.text = contractorAddress;
    _applianceModel.text = applianceModel;
    
}

-(IBAction)requestService:(id)sender
{
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
