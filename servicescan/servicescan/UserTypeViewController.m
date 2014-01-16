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
    NSUserDefaults *defaults = [NSUserDefaults standardUserDefaults];
    [defaults setObject:@"1" forKey:@"userType"];
    [defaults synchronize];
    
    
    [self dismissViewControllerAnimated:YES completion:nil];
    
    
}

-(IBAction)logInAsContractor:(id)sender
{
    NSUserDefaults *defaults = [NSUserDefaults standardUserDefaults];
    [defaults setObject:@"2" forKey:@"userType"];
    [defaults synchronize];
    
    [self dismissViewControllerAnimated:YES completion:nil];
    
    
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
