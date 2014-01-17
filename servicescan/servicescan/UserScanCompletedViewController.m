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
