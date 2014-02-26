//
//  InAppPurchase.m
//  servicescan
//
//  Created by sdickson on 1/30/14.
//  Copyright (c) 2014 ambro. All rights reserved.
//

#import "InAppPurchase.h"
#import "AppController.h"

@implementation InAppPurchase

-(NSData*)getJson
{
    Contractor* contractor = [[AppController sharedInstance] contractor];
    
    
    NSString* nameOnCard = [NSString stringWithFormat:@"%@ %@", contractor.firstName, contractor.lastName];
    
    
    
    NSMutableDictionary* dict = [NSMutableDictionary dictionaryWithObjectsAndKeys:nameOnCard, @"nameOnCard", self.cardNumber, @"cardNumber", self.cardAddress, @"cardAddress", self.cardAddress, @"cardAddress", self.cardCity, @"cardCity",self.cardState, @"cardState", self.cardZip, @"cardZip", self.cardExpMonth, @"cardExpMonth", self.cardExpYear, @"cardExpYear", self.cardCVV, @"cardCVV", self.email, @"email", self.quantity, @"quantity", nil];
    
    NSError* error = nil;
    NSData *jsonData = [NSJSONSerialization dataWithJSONObject:dict
                                                       options:0
                                                         error:&error];
    
    
    NSString* tempStr = [[NSString alloc] initWithData:jsonData encoding:NSUTF8StringEncoding];
    
    NSLog(@"Json Object:  %@", tempStr);
    
    return jsonData;
    
    
    
    
    
}
@end
