//
//  AppController.m
//  servicescan
//
//  Created by sdickson on 1/16/14.
//  Copyright (c) 2014 ambro. All rights reserved.
//

#import "AppController.h"
#import "Flurry.h"

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
        _serviceCallHistory = [[NSMutableArray alloc] initWithCapacity:10];
        
        [self loadContractorInfo];
        [self loadContractorHistory];
        _locManager = [[CLLocationManager alloc] init];
        _locManager.delegate = self;
        _locManager.desiredAccuracy = kCLLocationAccuracyBestForNavigation;
        [_locManager startUpdatingLocation];
        
        
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
    
    
    
    NSData* tempData = [contractor getJson];
    
    NSString* tempStr = [[NSString alloc] initWithData:tempData encoding:NSUTF8StringEncoding];
    
    NSString* urlString = [NSString stringWithFormat:@"http://servicescans.com/AddNewContractor?contractor=%@",[tempStr stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding]];
    
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

    
}
-(void)loadContractorInfo
{
    
    NSUserDefaults *defaults = [NSUserDefaults standardUserDefaults];
    NSData *encodedObject = [defaults objectForKey:@"contractor"];
    Contractor* contractor = [NSKeyedUnarchiver unarchiveObjectWithData:encodedObject];
    self.contractor = contractor;
    
}
-(void)loadServiceCallHistory
{
    [self.serviceCallHistory removeAllObjects];
    
    
    NSString* urlString = [NSString stringWithFormat:@"http://servicescans.com/GetServiceCallHistory?qrCode=%@",[[AppController sharedInstance] qrCode]];
    
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
     
        NSDictionary* timeDict = [dict objectForKey:@"createdTimestamp"];
        NSString* timeVal = [timeDict valueForKey:@"time"];
        if([timeVal class] == [NSNull class])
        {
            continue;
            
        }
        NSTimeInterval intervaldep=timeVal.doubleValue/1000;
        
        NSDate* dateObj = [NSDate dateWithTimeIntervalSince1970:intervaldep];
        NSDateFormatter *formatter = [[NSDateFormatter alloc] init];
        [formatter setDateFormat:@"MM-dd-yyyy hh:mm:ss"];
        
        //Optionally for time zone converstions
        //[formatter setTimeZone:[NSTimeZone timeZoneWithName:@"..."]];
        
        NSString *stringFromDate = [formatter stringFromDate:dateObj];
        
        
        
        NSString* notes = [dict valueForKey:@"notes"];
        ServiceCallHistory* serviceCallHistoryObject = [[ServiceCallHistory alloc] init];
        serviceCallHistoryObject.date = stringFromDate;
        serviceCallHistoryObject.notes = notes;
        [[[AppController sharedInstance] serviceCallHistory] addObject:serviceCallHistoryObject];
        
        
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
-(void)loadContractorHistory
{
    
    
    
    NSData* tempData = [self.contractor getJson];
    
    NSString* tempStr = [[NSString alloc] initWithData:tempData encoding:NSUTF8StringEncoding];
    
    NSString* urlString = [NSString stringWithFormat:@"http://servicescans.com/GetHistoryForContractor?contractor=%@",[tempStr stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding]];
    
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
    NSDictionary* dict =  [NSJSONSerialization JSONObjectWithData: jsonData  options: NSJSONReadingAllowFragments   error: &error];
   
    Request* requestObj = [[Request alloc] init];
        
    requestObj.userFirstName = dict[@"customerFirstName"];
    requestObj.userLastName = dict[@"customerLastName"];
    requestObj.city = dict[@"customerCity"];
    requestObj.state = dict[@"customerState"];
    requestObj.zip = dict[@"customerZip"];
    requestObj.phone = dict[@"customerPhone"];
    [_contractorHistory addObject:requestObj];
        
        
    
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
#pragma mark core location
- (void)locationManager:(CLLocationManager *)manager didUpdateLocations:(NSArray *)locations
{
    _location = [locations objectAtIndex:0];
    
   
    
    
    [Flurry setLatitude:_location.coordinate.latitude    longitude:_location.coordinate.longitude     horizontalAccuracy:_location.horizontalAccuracy verticalAccuracy:_location.verticalAccuracy];
    
    NSLog(@"Location has been updated:  lat - %+.6f lon - %+.6f",_location.coordinate.latitude, _location.coordinate.longitude);
    
    
    
    
}


#pragma mark credit card
-(void)purchaseRoll:(InAppPurchase*)inappPurchase;
{
    
    
    NSData* tempData = [inappPurchase getJson];
    NSData* contractorData = [[[AppController sharedInstance] contractor] getJson];
    
    NSString* tempStr = [[NSString alloc] initWithData:tempData encoding:NSUTF8StringEncoding];
    NSString* tempStr2 = [[NSString alloc] initWithData:contractorData encoding:NSUTF8StringEncoding];
    
    NSString* urlString = [NSString stringWithFormat:@"http://servicescans.com/PurchaseRoll?inapp_purchase=%@&contractor=%@",[tempStr stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding], [tempStr2 stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding]];
    
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
@end
