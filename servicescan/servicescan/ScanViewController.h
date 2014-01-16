//
//  ScanViewController.h
//  servicescan
//
//  Created by sdickson on 1/14/14.
//  Copyright (c) 2014 ambro. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "CustomCameraViewController.h"

@interface ScanViewController : UIViewController
{

    
     CustomCameraViewController* cameraViewController;
    
    
}

-(IBAction)showScanner:(id)sender;


@end
