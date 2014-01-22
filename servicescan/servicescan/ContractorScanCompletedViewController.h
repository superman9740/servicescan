//
//  ContractorScanCompletedViewController.h
//  servicescan
//
//  Created by sdickson on 1/17/14.
//  Copyright (c) 2014 ambro. All rights reserved.
//

#import <UIKit/UIKit.h>

#import "ServiceScan.h"
#import "AppController.h"

@interface ContractorScanCompletedViewController : UIViewController<UITextFieldDelegate>

{
    
    
}



@property (nonatomic, strong) IBOutlet UITextField* customerFirstName;
@property (nonatomic, strong) IBOutlet UITextField* customerLastName;
@property (nonatomic, strong) IBOutlet UITextField* customerAddress;
@property (nonatomic, strong) IBOutlet UITextField* customerCity;
@property (nonatomic, strong) IBOutlet UITextField* customerState;
@property (nonatomic, strong) IBOutlet UITextField* customerZip;
@property (nonatomic, strong) IBOutlet UITextField* customerPhone;

@property (nonatomic, strong) IBOutlet UITextField* applianceSerial;
@property (nonatomic, strong) IBOutlet UITextField* applianceModel;
@property (nonatomic, strong) IBOutlet UITextField* applianceType;







@property (nonatomic, strong) IBOutlet UIScrollView* scrollView;
@property (weak, nonatomic) UITextField* activeTextField;
@property (nonatomic) BOOL viewWasMoved;

-(IBAction)saveApplicanceRecord:(id)sender;

@end
