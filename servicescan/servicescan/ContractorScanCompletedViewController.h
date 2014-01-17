//
//  ContractorScanCompletedViewController.h
//  servicescan
//
//  Created by sdickson on 1/17/14.
//  Copyright (c) 2014 ambro. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface ContractorScanCompletedViewController : UIViewController<UITextFieldDelegate>

{
    
    
}

@property (nonatomic, strong) IBOutlet UIScrollView* scrollView;
@property (weak, nonatomic) UITextField* activeTextField;
@property (nonatomic) BOOL viewWasMoved;

-(IBAction)saveApplicanceRecord:(id)sender;

@end
