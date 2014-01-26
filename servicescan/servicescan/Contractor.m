//
//  Contractor.m
//  servicescan
//
//  Created by sdickson on 1/22/14.
//  Copyright (c) 2014 ambro. All rights reserved.
//

#import "Contractor.h"

@implementation Contractor
- (void)encodeWithCoder:(NSCoder *)encoder {
    [encoder encodeObject:self.firstName forKey:@"firstName"];
    [encoder encodeObject:self.lastName forKey:@"lastName"];
    [encoder encodeObject:self.address forKey:@"address"];
    [encoder encodeObject:self.city forKey:@"city"];
    [encoder encodeObject:self.state forKey:@"state"];
    [encoder encodeObject:self.zip forKey:@"zip"];
    [encoder encodeObject:self.phone forKey:@"phone"];




}

- (id)initWithCoder:(NSCoder *)decoder {
    if((self = [super init])) {
        //decode properties, other class vars
        self.firstName = [decoder decodeObjectForKey:@"firstName"];
        self.lastName = [decoder decodeObjectForKey:@"lastName"];
        self.address = [decoder decodeObjectForKey:@"address"];
        self.city = [decoder decodeObjectForKey:@"city"];
        self.state = [decoder decodeObjectForKey:@"state"];
        self.zip = [decoder decodeObjectForKey:@"zip"];
        self.phone = [decoder decodeObjectForKey:@"phone"];
        
    }
    return self;
}

-(NSData*)getJson
{
    
    
    NSMutableDictionary* dict = [NSMutableDictionary dictionaryWithObjectsAndKeys:self.firstName, @"contractorFirstName", self.lastName, @"contractorLastName", self.address, @"contractorAddress", self.city, @"contractorCity",self.state, @"contractorState", self.zip, @"contractorZip", self.phone, @"contractorPhone", nil];
    
    NSError* error = nil;
    NSData *jsonData = [NSJSONSerialization dataWithJSONObject:dict
                                                       options:0
                                                         error:&error];
    
    
    NSString* tempStr = [[NSString alloc] initWithData:jsonData encoding:NSUTF8StringEncoding];
    
    NSLog(@"Json Object:  %@", tempStr);
    
    return jsonData;
    
    
    
    
    
}

@end
