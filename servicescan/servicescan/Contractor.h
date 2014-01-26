//
//  Contractor.h
//  servicescan
//
//  Created by sdickson on 1/22/14.
//  Copyright (c) 2014 ambro. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface Contractor : NSObject
{
    
    
}

@property (nonatomic, strong) NSString* firstName;
@property (nonatomic, strong) NSString* lastName;
@property (nonatomic, strong) NSString* address;
@property (nonatomic, strong) NSString* city;
@property (nonatomic, strong) NSString* state;
@property (nonatomic, strong) NSString* zip;
@property (nonatomic, strong) NSString* phone;





-(NSData*)getJson;


@end
