//
//  ScanViewController.h
//  servicescan
//
//  Created by sdickson on 1/14/14.
//  Copyright (c) 2014 ambro. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "CustomCameraViewController.h"
#import "UserScanCompletedViewController.h"

@class AppController;



@interface ScanViewController : UIViewController
{

    
    CustomCameraViewController* cameraViewController;
    UserScanCompletedViewController* userScanCompletedViewController;
    
    
}

-(IBAction)showScanner:(id)sender;
-(IBAction)resetUserType:(id)sender;
-(IBAction)closeScanner:(id)sender;


@end
