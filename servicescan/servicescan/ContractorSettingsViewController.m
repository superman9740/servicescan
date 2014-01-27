//
//  ContractorSettingsViewController.m
//  servicescan
//
//  Created by sdickson on 1/22/14.
//  Copyright (c) 2014 ambro. All rights reserved.
//

#import "ContractorSettingsViewController.h"
#import "AppController.h"

@interface ContractorSettingsViewController ()

@end

@implementation ContractorSettingsViewController

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
    self.title = @"Contractor Settings";
    Contractor* contractor = [[AppController sharedInstance] contractor];
    
    
    self.contractorFirstName.text = [NSString stringWithFormat:@"%@ %@", contractor.firstName, contractor.lastName];
    self.contractorAddress.text = contractor.address;
    self.contractorCity.text = contractor.city;
    self.contractorState.text = contractor.state;
    self.contractorZip.text = contractor.zip;
    self.contractorPhone.text = contractor.phone;
    
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
    self.scrollView.contentSize = CGSizeMake(self.view.frame.size.width,800 );

    
}
-(void)keyboardDidShow:(NSNotification*)notification
{
    NSDictionary* info = [notification userInfo];
    CGSize kbSize = [[info objectForKey:UIKeyboardFrameBeginUserInfoKey] CGRectValue].size;
    
    self.scrollView.frame=CGRectMake(0, 0, self.view.frame.size.width, self.view.frame.size.height-kbSize.height);
    
    UIView *activeField;
    if (activeTextField!=nil)
        activeField=activeTextField;
    
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

-(IBAction)updateInformation:(id)sender
{
    
    Contractor* contractor = [[AppController sharedInstance] contractor];
    if(contractor == nil)
    {
        contractor = [[Contractor alloc] init];
        
    }
    NSArray* names = [self.contractorFirstName.text componentsSeparatedByString:@" "];
    
    if([names count] > 0)
    {
        contractor.firstName = names[0];
        contractor.lastName = names[1];
    }
    else
    {
        contractor.firstName = self.contractorFirstName.text;
        contractor.lastName = @" ";
        
        
    }

    contractor.address = self.contractorAddress.text;
    contractor.city = self.contractorCity.text;
    contractor.state = self.contractorState.text;
    contractor.zip = self.contractorZip.text;
    contractor.phone = self.contractorPhone.text;
    
    [[AppController sharedInstance] updateContractorInfo:contractor];
    

    
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
