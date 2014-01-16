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
    
    int userType = [[AppController sharedInstance] userType];
    if(userType == -1)
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
- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
