//
//  ContractorScanCompletedViewController.m
//  servicescan
//
//  Created by sdickson on 1/17/14.
//  Copyright (c) 2014 ambro. All rights reserved.
//

#import "ContractorScanCompletedViewController.h"
#import "AppController.h"

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
    self.scrollView.contentSize = CGSizeMake(self.view.frame.size.width,800 );
    
    
    
}

-(IBAction)saveApplicanceRecord:(id)sender
{

    ServiceScan* scan = [[ServiceScan alloc] init];
    Contractor* contractor = [[AppController sharedInstance] contractor];
    
    NSString* contractorName = contractor.firstName;
    NSArray* names = [contractorName componentsSeparatedByString:@" "];
    
    NSString* customerName = _customerFirstName.text;
    
    names = [customerName componentsSeparatedByString:@" "];
    
    scan.customerFirstName = names[0];
    scan.customerLastName = names[1];
    scan.customerAddress = _customerAddress.text;
    scan.customerCity = _customerCity.text;
    scan.customerState = _customerState.text;
    scan.customerZip = _customerZip.text;
    scan.customerPhone = _customerPhone.text;
    scan.applianceSerial = _applianceSerial.text;
    scan.applianceModel = _applianceModel.text;
    scan.applianceType = _applianceType.text;
    scan.qrCode = [[AppController sharedInstance] qrCode];
    scan.deviceToken = [[AppController sharedInstance] deviceToken];
    
    
    NSData* tempData = [scan getJson];
    
    NSString* tempStr = [[NSString alloc] initWithData:tempData encoding:NSUTF8StringEncoding];

    NSString* urlString = [NSString stringWithFormat:@"http://servicescans.com/SaveNewServiceScan?scan=%@",[tempStr stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding]];
    
    NSURL* url = [NSURL URLWithString:urlString];
    
    NSError* error = nil;
    NSURLResponse* response = nil;
    NSURLRequest* request = [NSURLRequest requestWithURL:url];
    
    
    NSData* jsonData = [NSURLConnection sendSynchronousRequest:request returningResponse:&response error:&error];
    if(error.code == -1004)
    {
        
        
        UIAlertView* alertView = [[UIAlertView alloc] initWithTitle:@"Service Error" message:@"There was an error connecting to the server.  Please try again." delegate:self cancelButtonTitle:@"OK" otherButtonTitles:nil];
        [alertView show];
        return;
        
    }
    
    NSString* jsonString = [[NSString alloc] initWithData:jsonData encoding:NSUTF8StringEncoding];
    
    if([jsonString isEqualToString:@"-1\n"])
    {
        UIAlertView* alertView = [[UIAlertView alloc] initWithTitle:@"Service Error" message:@"That QR code has already been associated with a service contractor.  Please try again." delegate:self cancelButtonTitle:@"OK" otherButtonTitles:nil];
        [alertView show];
        return;
        
        
    }

    
    
    
    
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
