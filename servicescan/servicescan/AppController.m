//
//  AppController.m
//  servicescan
//
//  Created by sdickson on 1/16/14.
//  Copyright (c) 2014 ambro. All rights reserved.
//

#import "AppController.h"

static  AppController* sharedInstance = nil;

@implementation AppController
+ (AppController *)sharedInstance {
    if (sharedInstance == nil)
    {
            sharedInstance = [[super allocWithZone:NULL] init];
        
        
        
    }
    
    return sharedInstance;
}

-(id)init
{
    
    self = [super init];
    if(self)
    {
    
        NSUserDefaults *defaults = [NSUserDefaults standardUserDefaults];
        NSString* userID = [defaults objectForKey:@"userType"];
        if(userID == nil)
        {
            _userType = -1;
            
        }
        else
        {
            _userType = userID.intValue;
            
        }
        
    }
    
    return self;
    
}
@end
