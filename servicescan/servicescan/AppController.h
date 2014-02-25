//
//  AppController.h
//  servicescan
//
//  Created by sdickson on 1/16/14.
//  Copyright (c) 2014 ambro. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "ServiceScan.h"
#import "Contractor.h"
#import "Request.h"
#import "InAppPurchase.h"
#import "ServiceCallHistory.h"

@interface AppController : NSObject
{
    
    
}

@property (assign, nonatomic) NSInteger userType;
@property (strong, nonatomic) NSString* qrCode;
@property (strong, nonatomic) ServiceScan* serviceScan;
@property (strong, nonatomic) NSString* deviceToken;
@property (strong, nonatomic) Contractor* contractor;
@property (strong, nonatomic) NSMutableArray* contractorHistory;
@property (strong, nonatomic) NSMutableArray* serviceCallHistory;



+ (id)sharedInstance;

-(void)loginAsUser;
-(void)loginAsContractor;
-(void)updateContractorInfo:(Contractor*)contractor;
-(void)loadContractorHistory;
-(void)loadServiceCallHistory;
-(void)purchaseRoll:(InAppPurchase*)inappPurchase;

@end
