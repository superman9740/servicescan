//
//  InAppPurchase.h
//  servicescan
//
//  Created by sdickson on 1/30/14.
//  Copyright (c) 2014 ambro. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface InAppPurchase : NSObject
{
    
    
}

@property (nonatomic, strong) NSString* cardNameOnCard;
@property (nonatomic, strong) NSString* cardNumber;
@property (nonatomic, strong) NSString* cardAddress;
@property (nonatomic, strong) NSString* cardCity;
@property (nonatomic, strong) NSString* cardState;
@property (nonatomic, strong) NSString* cardZip;
@property (nonatomic, strong) NSString* cardExpMonth;
@property (nonatomic, strong) NSString* cardExpYear;
@property (nonatomic, strong) NSString* cardCVV;
@property (nonatomic, strong) NSString* email;




-(NSData*)getJson;

@end
