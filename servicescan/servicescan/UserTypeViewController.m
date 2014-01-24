//
//  UserTypeViewController.m
//  servicescan
//
//  Created by sdickson on 1/16/14.
//  Copyright (c) 2014 ambro. All rights reserved.
//

#import "UserTypeViewController.h"

@interface UserTypeViewController ()

@end

@implementation UserTypeViewController

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
}


-(IBAction)logInAsUser:(id)sender
{
    [[AppController sharedInstance] loginAsUser];
    
    UIAlertView* alertView = [[UIAlertView alloc] initWithTitle:@"Set User Type" message:@"Your login type is now set as user." delegate:self cancelButtonTitle:@"OK" otherButtonTitles:nil];
    [alertView show];
    
    
    
}

-(IBAction)logInAsContractor:(id)sender
{
    [[AppController sharedInstance] loginAsContractor];
    
    UIAlertView* alertView = [[UIAlertView alloc] initWithTitle:@"Set User Type" message:@"Your login type is now set as contractor." delegate:self cancelButtonTitle:@"OK" otherButtonTitles:nil];
    [alertView show];

    
    
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
