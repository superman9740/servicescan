//
//  AppDelegate.m
//  servicescan
//
//  Created by sdickson on 1/14/14.
//  Copyright (c) 2014 ambro. All rights reserved.
//

#import "AppDelegate.h"
#import "UIImage+iPhone5.h"
#import "Utils.h"
#import "AppController.h"
#import "Flurry.h"


@implementation AppDelegate

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{

    [AppController sharedInstance];
    
      [[UIApplication sharedApplication] setStatusBarStyle:UIStatusBarStyleBlackOpaque animated:NO];
    
    UIImage *navBarImage = [UIImage tallImageNamed:@"menubar.png"];
    
    
    
    if(![Utils isVersion6AndBelow]){
        navBarImage = [UIImage tallImageNamed:@"menubar-7.png"];
        
        [[UINavigationBar appearance] setTitleTextAttributes:
         @{UITextAttributeTextColor: [UIColor colorWithRed:255.0/255.0 green:255.0/255.0 blue:255.0/255.0 alpha:1.0],
           UITextAttributeTextShadowColor: [UIColor colorWithRed:0.0 green:0.0 blue:0.0 alpha:0.8],
           UITextAttributeTextShadowOffset: [NSValue valueWithUIOffset:UIOffsetMake(0, -1)]}];
    }
    
    
    [[UINavigationBar appearance] setBackgroundImage:navBarImage forBarMetrics:UIBarMetricsDefault];
    
    
    UIImage* tabBarBackground = [UIImage tallImageNamed:@"tabbar.png"];
    [[UITabBar appearance] setBackgroundImage:tabBarBackground];
    
    [[UITabBar appearance] setShadowImage:[UIImage imageNamed:@"transparentShadow.png"]];
    
    [[UITabBar appearance] setSelectionIndicatorImage:[UIImage tallImageNamed:@"tabbar-active.png"]];
    [[UITextField appearance] setBackground:[UIImage imageNamed:@"text-input.png"]];
    

    [[UIApplication sharedApplication] registerForRemoteNotificationTypes:(UIRemoteNotificationTypeBadge | UIRemoteNotificationTypeSound|UIRemoteNotificationTypeAlert)];
    
    application.applicationIconBadgeNumber = 0;
    
    [Flurry setCrashReportingEnabled:YES];
    [Flurry startSession:@"YDZDNJH88ZTNW44DNGBT"];
    
    return YES;
}
							
- (void)applicationWillResignActive:(UIApplication *)application
{
    // Sent when the application is about to move from active to inactive state. This can occur for certain types of temporary interruptions (such as an incoming phone call or SMS message) or when the user quits the application and it begins the transition to the background state.
    // Use this method to pause ongoing tasks, disable timers, and throttle down OpenGL ES frame rates. Games should use this method to pause the game.
}

- (void)applicationDidEnterBackground:(UIApplication *)application
{
    // Use this method to release shared resources, save user data, invalidate timers, and store enough application state information to restore your application to its current state in case it is terminated later. 
    // If your application supports background execution, this method is called instead of applicationWillTerminate: when the user quits.
}

- (void)applicationWillEnterForeground:(UIApplication *)application
{
    // Called as part of the transition from the background to the inactive state; here you can undo many of the changes made on entering the background.
}

- (void)applicationDidBecomeActive:(UIApplication *)application
{
    // Restart any tasks that were paused (or not yet started) while the application was inactive. If the application was previously in the background, optionally refresh the user interface.
}

- (void) application:(UIApplication *)application didReceiveLocalNotification:(UILocalNotification *)notification
{
    application.applicationIconBadgeNumber = notification.applicationIconBadgeNumber-1;
    [[AppController sharedInstance] loadContractorHistory];
    
    
}
- (void) application:(UIApplication *)application didReceiveRemoteNotification:(NSDictionary *)userInfo
{
    
    NSDictionary *aps = (NSDictionary *)[userInfo objectForKey:@"aps"];
    NSDictionary *alert = (NSDictionary *)[aps objectForKey:@"alert"];
    
    
    
    NSString* qrCode = [alert valueForKey:@"action-loc-key"];
    
    NSString* urlString = [NSString stringWithFormat:@"http://servicescans.com/LookupScan?qrCode=%@",qrCode];
    
    NSURL* url = [NSURL URLWithString:urlString];
    
    NSError* error = nil;
    NSURLResponse* response = nil;
    NSURLRequest* request = [NSURLRequest requestWithURL:url];
    
    NSData* jsonData = [NSURLConnection sendSynchronousRequest:request returningResponse:&response error:&error];
    if(error.code == -1004)
    {
        
        
       // UIAlertView* alertView = [[UIAlertView alloc] initWithTitle:@"Service Error" message:@"There was an error connecting to the server.  Please try again." delegate:self cancelButtonTitle:@"OK" otherButtonTitles:nil];
      //  [alertView show];
        return;
        
    }
    
    NSString* jsonString = [[NSString alloc] initWithData:jsonData encoding:NSUTF8StringEncoding];
    
    if([jsonString isEqualToString:@"-1\n"])
    {
      //  UIAlertView* alertView = [[UIAlertView alloc] initWithTitle:@"Service Error" message:@"That QR code doesn't seem to be associated with a service contractor.  Please try again." delegate:self cancelButtonTitle:@"OK" otherButtonTitles:nil];
       // [alertView show];
        return;
        
        
    }
    NSDictionary* dict = [NSJSONSerialization JSONObjectWithData:jsonData options:0 error:&error];
    
    
    NSString* applianceModel = [dict valueForKey:@"applianceModel"];
    ServiceScan* serviceScan = [[AppController sharedInstance] serviceScan];
    serviceScan.applianceModel = applianceModel;
    serviceScan.customerFirstName = [dict valueForKey:@"customerFirstName"];
    serviceScan.customerLastName = [dict valueForKey:@"customerLastName"];
    serviceScan.customerAddress = [dict valueForKey:@"customerAddress"];
    serviceScan.customerCity = [dict valueForKey:@"customerCity"];
    serviceScan.customerState = [dict valueForKey:@"customerState"];
    serviceScan.customerZip = [dict valueForKey:@"customerZip"];
    serviceScan.customerPhone = [dict valueForKey:@"customerPhone"];
    
    NSString* customerRequest = [NSString stringWithFormat:@"Customer %@ %@ has sent you a request for service.  Their address is %@ %@ %@ %@.  They can be reached at %@",
                                 serviceScan.customerFirstName, serviceScan.customerLastName, serviceScan.customerAddress, serviceScan.customerCity,serviceScan.customerState,
                                 serviceScan.customerZip, serviceScan.customerPhone];
    
    UIAlertView* alertView = [[UIAlertView alloc] initWithTitle:@"Incoming Request" message:customerRequest delegate:self cancelButtonTitle:@"OK" otherButtonTitles:nil];
    [alertView show];
    
    [[AppController sharedInstance] loadContractorHistory];
    
    
}


- (void)application:(UIApplication *)app didRegisterForRemoteNotificationsWithDeviceToken:(NSData *)devToken
{
    const unsigned *tokenBytes = [devToken bytes];
    NSString *hexToken = [NSString stringWithFormat:@"%08x%08x%08x%08x%08x%08x%08x%08x",
                          ntohl(tokenBytes[0]), ntohl(tokenBytes[1]), ntohl(tokenBytes[2]),
                          ntohl(tokenBytes[3]), ntohl(tokenBytes[4]), ntohl(tokenBytes[5]),
                          ntohl(tokenBytes[6]), ntohl(tokenBytes[7])];
    [[AppController sharedInstance] setDeviceToken:hexToken];
    
    
}

- (void)application:(UIApplication *)app didFailToRegisterForRemoteNotificationsWithError:(NSError *)err {
    
    NSLog(@"Error in registration. Error: %@", err);
    
}




- (void)applicationWillTerminate:(UIApplication *)application
{
    // Called when the application is about to terminate. Save data if appropriate. See also applicationDidEnterBackground:.
}

@end
