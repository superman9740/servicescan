//
//  ContractorScanCompletedViewController.m
//  servicescan
//
//  Created by sdickson on 1/17/14.
//  Copyright (c) 2014 ambro. All rights reserved.
//

#import "ContractorScanCompletedViewController.h"

@interface ContractorScanCompletedViewController ()

@end

@implementation ContractorScanCompletedViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
	[self.view setBackgroundColor:[UIColor colorWithPatternImage:[UIImage imageNamed:@"bg.png"]]];
    self.title = @"Appliance Details";
    
    
      [[NSNotificationCenter defaultCenter] addObserver:self
                                             selector:@selector(keyboardDidShow:)
                                                 name:UIKeyboardDidShowNotification
                                               object:nil];
    
    [[NSNotificationCenter defaultCenter] addObserver:self
                                             selector:@selector(keyboardWillHide:)
                                                 name:UIKeyboardWillHideNotification
                                               object:nil];
  
    
    

}
-(void)viewDidAppear:(BOOL)animated
{
       
    self.scrollView.scrollEnabled = YES;
    self.scrollView.contentSize = CGSizeMake(self.view.frame.size.width,600 );
    
    
    
}

-(IBAction)saveApplicanceRecord:(id)sender
{
    
    UIAlertView* alertView = [[UIAlertView alloc] initWithTitle:@"Save Record" message:@"The applicance record has been saved." delegate:self cancelButtonTitle:@"OK" otherButtonTitles:nil];
    [alertView show];

    
}

-(void)keyboardDidShow:(NSNotification*)notification
{
    NSDictionary* info = [notification userInfo];
    CGSize kbSize = [[info objectForKey:UIKeyboardFrameBeginUserInfoKey] CGRectValue].size;
    
    self.scrollView.frame=CGRectMake(0, 0, self.view.frame.size.width, self.view.frame.size.height-kbSize.height);
    
    UIView *activeField;
    if (_activeTextField!=nil)
        activeField=_activeTextField;
    
    if (!CGRectContainsPoint(self.scrollView.frame, activeField.frame.origin)){
        // For some stupid reason, setContentOffset:animated: was not actually moving the view
        // this effectively does the same thing, but actually moves
        [UIView animateWithDuration:0.5 animations:^{
            self.scrollView.contentOffset=CGPointMake(0,  kbSize.height);
        }];
    }
}

-(void)keyboardWillHide:(NSNotification*)notification
{
    self.scrollView.frame=CGRectMake(0, 0, self.view.frame.size.width, self.view.frame.size.height);
}

- (BOOL)textFieldShouldReturn:(UITextField *)textField
{
    [textField resignFirstResponder];
    
    return YES;
}
- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
