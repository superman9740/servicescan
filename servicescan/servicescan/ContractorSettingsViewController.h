//
//  ContractorSettingsViewController.h
//  servicescan
//
//  Created by sdickson on 1/22/14.
//  Copyright (c) 2014 ambro. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface ContractorSettingsViewController : UIViewController<UITextFieldDelegate>
{
    
    
}

@property (nonatomic, strong) IBOutlet UITextField* contractorFirstName;
@property (nonatomic, strong) IBOutlet UITextField* contractorLastName;
@property (nonatomic, strong) IBOutlet UITextField* contractorAddress;
@property (nonatomic, strong) IBOutlet UITextField* contractorCity;
@property (nonatomic, strong) IBOutlet UITextField* contractorState;
@property (nonatomic, strong) IBOutlet UITextField* contractorZip;
@property (nonatomic, strong) IBOutlet UITextField* contractorPhone;

-(IBAction)updateInformation:(id)sender;

@end
