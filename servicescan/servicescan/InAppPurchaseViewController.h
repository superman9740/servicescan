//
//  InAppPurchaseViewController.h
//  servicescan
//
//  Created by sdickson on 1/28/14.
//  Copyright (c) 2014 ambro. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "InAppPurchase.h"

@interface InAppPurchaseViewController : UIViewController<UIPickerViewDataSource, UIPickerViewDelegate>
{
    
    
}

@property (nonatomic, strong) IBOutlet UIScrollView* scrollView;
@property (nonatomic, strong) IBOutlet UIPickerView* pickerView;
@property (nonatomic, strong) IBOutlet UILabel* numberOfRolls;
@property (nonatomic, strong) IBOutlet UILabel* totalPrice;

@property (nonatomic, strong) IBOutlet UITextField* cardNumber;
@property (nonatomic, strong) IBOutlet UITextField* address;
@property (nonatomic, strong) IBOutlet UITextField* city;
@property (nonatomic, strong) IBOutlet UITextField* state;
@property (nonatomic, strong) IBOutlet UITextField* zip;
@property (nonatomic, strong) IBOutlet UITextField* expMonth;
@property (nonatomic, strong) IBOutlet UITextField* expYear;
@property (nonatomic, strong) IBOutlet UITextField* cvv;









@property (weak, nonatomic) UITextField* activeTextField;
@property (nonatomic) BOOL viewWasMoved;

-(IBAction)purchaseRoll:(id)sender;

@end
