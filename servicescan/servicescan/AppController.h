//
//  AppController.h
//  servicescan
//
//  Created by sdickson on 1/16/14.
//  Copyright (c) 2014 ambro. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface AppController : NSObject
{
    
    
}

@property (assign, nonatomic) NSInteger userType;
@property (strong, nonatomic) NSString* qrCode;

+ (id)sharedInstance;

-(void)loginAsUser;
-(void)loginAsContractor;

@end
