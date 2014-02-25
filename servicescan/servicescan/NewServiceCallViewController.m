//
//  NewServiceCallViewController.m
//  servicescan
//
//  Created by sdickson on 2/24/14.
//  Copyright (c) 2014 ambro. All rights reserved.
//

#import "NewServiceCallViewController.h"

@interface NewServiceCallViewController ()

@end

@implementation NewServiceCallViewController

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
	// Do any additional setup after loading the view.
}

-(void)saveServiceCall:(id)sender
{
        
    
    
        NSString* tempStr = [NSString stringWithFormat:@"http://servicescans.com/SaveServiceCall?qrCode=%@&notes=%@",[[AppController sharedInstance] qrCode],_notesView.text];
    
        NSString* urlString = [tempStr stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
        
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
        
        
        
        
        
        UIAlertView* alertView = [[UIAlertView alloc] initWithTitle:@"Save Record" message:@"The service call has been saved." delegate:self cancelButtonTitle:@"OK" otherButtonTitles:nil];
    alertView.delegate = self;
    
        [alertView show];
        
    


    
}
-(void)alertView:(UIAlertView *)alertView clickedButtonAtIndex:(NSInteger)buttonIndex
{
 
    [self.navigationController popViewControllerAnimated:YES];
    



}

- (BOOL)textView:(UITextView *)textView shouldChangeTextInRange:(NSRange)range replacementText:(NSString *)text{
    if ([text isEqualToString:@"\n"]) {
        [self.view endEditing:YES];
    }
    
    return YES;
    
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
