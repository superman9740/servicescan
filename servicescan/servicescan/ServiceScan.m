//
//  ServiceScan.m
//  servicescan
//
//  Created by Shane Dickson on 1/19/14.
//  Copyright (c) 2014 ambro. All rights reserved.
//

#import "ServiceScan.h"

@implementation ServiceScan


-(NSData*)getJson
{
    
    

    NSMutableDictionary* dict = [NSMutableDictionary dictionaryWithObjectsAndKeys:self.contractorFirstName, @"contractorFirstName", self.contractorLastName, @"contractorLastName", self.contractorAddress, @"contractorAddress", self.contractorCity, @"contractorCity",self.contractorState, @"contractorState", self.contractorZip, @"contractorZip", self.contractorPhone, @"contractorPhone", self.customerFirstName, @"customerFirstName", self.customerLastName, @"customerLastName", self.customerAddress, @"customerAddress", self.customerCity, @"customerCity",self.customerState, @"customerState", self.customerZip, @"customerZip", self.customerPhone, @"customerPhone", self.applianceSerial, @"applianceSerial", self.applianceModel, @"applianceModel", self.applianceType, @"applianceType", self.qrCode, @"qrCode", nil];
    
    NSError* error = nil;
    NSData *jsonData = [NSJSONSerialization dataWithJSONObject:dict
                                                       options:0
                                                         error:&error];
    
    
    NSString* tempStr = [[NSString alloc] initWithData:jsonData encoding:NSUTF8StringEncoding];
    
    NSLog(@"Json Object:  %@", tempStr);
    
    return jsonData;
    
    
    
    
    
}

@end
