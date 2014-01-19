//
//  UserScanCompletedViewController.h
//  servicescan
//
//  Created by sdickson on 1/17/14.
//  Copyright (c) 2014 ambro. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "AppController.h"

@interface UserScanCompletedViewController : UIViewController<UITextFieldDelegate>
{
    
    
}

@property (nonatomic, strong) IBOutlet UILabel* contractorName;
@property (nonatomic, strong) IBOutlet UILabel* contractorAddress;
@property (nonatomic, strong) IBOutlet UILabel* applianceModel;



-(IBAction)requestService:(id)sender;

@end
