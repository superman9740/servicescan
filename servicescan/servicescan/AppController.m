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
        _userType = [defaults integerForKey:@"userType"];
        
        
    }
    
    return self;
    
}


-(void)loginAsUser
{
    NSUserDefaults *defaults = [NSUserDefaults standardUserDefaults];
    _userType = 1;
    
    [defaults setInteger:1 forKey:@"userType"];
    
    
    [defaults synchronize];
    
    
}
-(void)loginAsContractor
{
    NSUserDefaults *defaults = [NSUserDefaults standardUserDefaults];
    _userType = 2;
    
    [defaults setInteger:2 forKey:@"userType"];
    
    [defaults synchronize];

    
}
@end
