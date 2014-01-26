//
//  ServiceScan.h
//  servicescan
//
//  Created by Shane Dickson on 1/19/14.
//  Copyright (c) 2014 ambro. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface ServiceScan : NSObject
{
    
    
}



@property (nonatomic, strong) NSString* customerFirstName;
@property (nonatomic, strong) NSString* customerLastName;
@property (nonatomic, strong) NSString* customerAddress;
@property (nonatomic, strong) NSString* customerCity;
@property (nonatomic, strong) NSString* customerState;

@property (nonatomic, strong) NSString* customerZip;
@property (nonatomic, strong) NSString* customerPhone;

@property (nonatomic, strong) NSString* applianceSerial;
@property (nonatomic, strong) NSString* applianceModel;
@property (nonatomic, strong) NSString* applianceType;


@property (nonatomic, strong) NSString* qrCode;
@property (nonatomic, strong) NSString* deviceToken;






-(NSData*)getJson;


@end
