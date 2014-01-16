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
    
   
    
    
       
}

-(IBAction)showScanner:(id)sender
{
    
    
    cameraViewController = [self.storyboard instantiateViewControllerWithIdentifier:@"camera"];
    cameraViewController.delegate = self;
    [self presentViewController:cameraViewController animated:YES completion:nil];
    
    
    
    
}
- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
