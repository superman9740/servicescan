//
//  NewServiceCallViewController.h
//  servicescan
//
//  Created by sdickson on 2/24/14.
//  Copyright (c) 2014 ambro. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "AppController.h"

@interface NewServiceCallViewController : UIViewController<UITextFieldDelegate>
{
    
    
}
@property (nonatomic, strong) IBOutlet UITextView* notesView;


-(IBAction)saveServiceCall:(id)sender;

@end
