//
//  ServiceScan.m
//  servicescan
//
//  Created by Shane Dickson on 1/19/14.
//  Copyright (c) 2014 ambro. All rights reserved.
//

#import "ServiceScan.h"
#import "AppController.h"

@implementation ServiceScan


-(NSData*)getJson
{
    
    Contractor* contractor = [[AppController sharedInstance] contractor];
    

    NSMutableDictionary* dict = [NSMutableDictionary dictionaryWithObjectsAndKeys:contractor.firstName, @"contractorFirstName", contractor.lastName, @"contractorLastName", contractor.address, @"contractorAddress", contractor.city, @"contractorCity",contractor.state, @"contractorState", contractor.zip, @"contractorZip", contractor.phone, @"contractorPhone", self.customerFirstName, @"customerFirstName", self.customerLastName, @"customerLastName", self.customerAddress, @"customerAddress", self.customerCity, @"customerCity",self.customerState, @"customerState", self.customerZip, @"customerZip", self.customerPhone, @"customerPhone", self.applianceSerial, @"applianceSerial", self.applianceModel, @"applianceModel", self.applianceType, @"applianceType", self.qrCode, @"qrCode",self.deviceToken, @"deviceToken", nil];
    
    NSError* error = nil;
    NSData *jsonData = [NSJSONSerialization dataWithJSONObject:dict
                                                       options:0
                                                         error:&error];
    
    
    NSString* tempStr = [[NSString alloc] initWithData:jsonData encoding:NSUTF8StringEncoding];
    
    NSLog(@"Json Object:  %@", tempStr);
    
    return jsonData;
    
    
    
    
    
}

@end
