//
//  InAppPurchaseViewController.h
//  servicescan
//
//  Created by sdickson on 1/28/14.
//  Copyright (c) 2014 ambro. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface InAppPurchaseViewController : UIViewController<UIPickerViewDataSource, UIPickerViewDelegate>
{
    
    
}

@property (nonatomic, strong) IBOutlet UIScrollView* scrollView;
@property (nonatomic, strong) IBOutlet UIPickerView* pickerView;
@property (nonatomic, strong) IBOutlet UILabel* numberOfRolls;


@property (weak, nonatomic) UITextField* activeTextField;
@property (nonatomic) BOOL viewWasMoved;


@end
