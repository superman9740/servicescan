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
        _serviceScan = [[ServiceScan alloc] init];
        _contractorHistory = [[NSMutableArray alloc] initWithCapacity:10];
        
        [self loadContractorInfo];
        [self loadContractorHistory];
        
        
    }
    
    return self;
    
}
-(void)updateContractorInfo:(Contractor*)contractor
{
    
    NSData *encodedObject = [NSKeyedArchiver archivedDataWithRootObject:contractor];
    NSUserDefaults *defaults = [NSUserDefaults standardUserDefaults];
    [defaults setObject:encodedObject forKey:@"contractor"];
    [defaults synchronize];
    self.contractor = contractor;
    
}
-(void)loadContractorInfo
{
    
    NSUserDefaults *defaults = [NSUserDefaults standardUserDefaults];
    NSData *encodedObject = [defaults objectForKey:@"contractor"];
    Contractor* contractor = [NSKeyedUnarchiver unarchiveObjectWithData:encodedObject];
    self.contractor = contractor;
    
}

-(void)loadContractorHistory
{
    
    
    
    NSData* tempData = [self.contractor getJson];
    
    NSString* tempStr = [[NSString alloc] initWithData:tempData encoding:NSUTF8StringEncoding];
    
    NSString* urlString = [NSString stringWithFormat:@"http://servicescans.com:8080/ServiceScanServerSide/GetHistoryForContractor?contractor=%@",[tempStr stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding]];
    
    NSURL* url = [NSURL URLWithString:urlString];
    
    NSError* error = nil;
    NSURLResponse* response = nil;
    NSURLRequest* request = [NSURLRequest requestWithURL:url];
    
    
    NSData* jsonData = [NSURLConnection sendSynchronousRequest:request returningResponse:&response error:&error];
    if(error.code == -1004)
    {
        
        /*
        UIAlertView* alertView = [[UIAlertView alloc] initWithTitle:@"Service Error" message:@"There was an error connecting to the server.  Please try again." delegate:self cancelButtonTitle:@"OK" otherButtonTitles:nil];
        [alertView show];
        return;
        */
    }
    NSString* returnString = [[NSString alloc] initWithData:jsonData encoding:NSUTF8StringEncoding];
    if([returnString isEqualToString:@"-1\n"])
    {
        return;
        
    }
    NSArray* dictArray =  [NSJSONSerialization JSONObjectWithData: jsonData  options: NSJSONReadingAllowFragments   error: &error];
    
    for (NSDictionary* dict in dictArray)
    {
        Request* request = [[Request alloc] init];
        
        request.userFirstName = dict[@"customerFirstName"];
        request.userLastName = dict[@"customerLastName"];
        request.city = dict[@"customerCity"];
        request.state = dict[@"customerState"];
        request.zip = dict[@"customerZip"];
        request.phone = dict[@"customerPhone"];
        [_contractorHistory addObject:request];
        
        
    }
    
    /*
    if([dict n])
    {
        UIAlertView* alertView = [[UIAlertView alloc] initWithTitle:@"Service Error" message:@"That QR code has already been associated with a service contractor.  Please try again." delegate:self cancelButtonTitle:@"OK" otherButtonTitles:nil];
        [alertView show];
        return;
        
        
    }
    else
    {
        
        
    }
  */
    

    
    

    
    
    
    
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
