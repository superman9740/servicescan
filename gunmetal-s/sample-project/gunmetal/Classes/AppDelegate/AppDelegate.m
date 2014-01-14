//
//  AppDelegate.m
//  gunmetal
//
//  Created by Valentin on 5/13/122.
//  Copyright (c) 2012 AppDesignVault. All rights reserved.
//

#import "AppDelegate.h"
#import "MasterViewController.h"
#import "Utils.h"

@implementation AppDelegate

@synthesize window = _window;

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
    UIUserInterfaceIdiom idiom = [[UIDevice currentDevice] userInterfaceIdiom];
    if (idiom == UIUserInterfaceIdiomPad) 
    {
        [self customizeiPadTheme];
        
        [self iPadInit];
    }
    else 
    {
        [self customizeiPhoneTheme];
        
        [self configureiPhoneTabBar];
    }

    // Override point for customization after application launch.
    return YES;
}

-(void)customizeiPhoneTheme
{
    [[UIApplication sharedApplication] 
     setStatusBarStyle:UIStatusBarStyleBlackOpaque animated:NO];
    
    UIImage *navBarImage = [UIImage tallImageNamed:@"menubar.png"];
    
    if(![Utils isVersion6AndBelow]){
        navBarImage = [UIImage tallImageNamed:@"menubar-7.png"];
        
        [[UINavigationBar appearance] setTitleTextAttributes:
         @{UITextAttributeTextColor: [UIColor colorWithRed:255.0/255.0 green:255.0/255.0 blue:255.0/255.0 alpha:1.0],
           UITextAttributeTextShadowColor: [UIColor colorWithRed:0.0 green:0.0 blue:0.0 alpha:0.8],
           UITextAttributeTextShadowOffset: [NSValue valueWithUIOffset:UIOffsetMake(0, -1)]}];
    }
    
    [[UINavigationBar appearance] setBackgroundImage:navBarImage forBarMetrics:UIBarMetricsDefault];
    
    
    UIImage *barButton = [[UIImage tallImageNamed:@"menubar-button.png"] resizableImageWithCapInsets:UIEdgeInsetsMake(0, 4, 0, 4)];
    
    [[UIBarButtonItem appearance] setBackgroundImage:barButton forState:UIControlStateNormal 
                                          barMetrics:UIBarMetricsDefault];
    
    UIImage *backButton = [[UIImage tallImageNamed:@"back.png"] resizableImageWithCapInsets:UIEdgeInsetsMake(0, 14, 0, 4)];
    
    [[UIBarButtonItem appearance] setBackButtonBackgroundImage:backButton forState:UIControlStateNormal 
                                                    barMetrics:UIBarMetricsDefault];
    
    [[UISearchBar appearance] setBackgroundImage:[UIImage tallImageNamed:@"searchbar-bg.png"]];
    //[[UISearchBar appearance] setSearchFieldBackgroundImage:[UIImage tallImageNamed:@"searchbar-field.png"] forState:UIControlStateNormal];
    
    UIImage *minImage = [[UIImage tallImageNamed:@"slider-fill"] resizableImageWithCapInsets:UIEdgeInsetsMake(0, 10, 0, 10)];
    UIImage *maxImage = [UIImage tallImageNamed:@"slider-track.png"];
    UIImage *thumbImage = [UIImage tallImageNamed:@"slider-handle.png"];
    
    [[UISlider appearance] setMaximumTrackImage:maxImage forState:UIControlStateNormal];
    [[UISlider appearance] setMinimumTrackImage:minImage forState:UIControlStateNormal];
    [[UISlider appearance] setThumbImage:thumbImage forState:UIControlStateNormal];
    [[UISlider appearance] setThumbImage:thumbImage forState:UIControlStateHighlighted];
    
    UIImage* tabBarBackground = [UIImage tallImageNamed:@"tabbar.png"];
    [[UITabBar appearance] setBackgroundImage:tabBarBackground];
    
    [[UITabBar appearance] setShadowImage:[UIImage imageNamed:@"transparentShadow.png"]];
    
    [[UITabBar appearance] setSelectionIndicatorImage:[UIImage tallImageNamed:@"tabbar-active.png"]];

}

-(void)customizeiPadTheme
{
    UIImage *navBarImage = [[UIImage tallImageNamed:@"menubar-right.png"] resizableImageWithCapInsets:UIEdgeInsetsMake(5, 5, 0, 5)];
    
    if(![Utils isVersion6AndBelow]){
        navBarImage = [[UIImage tallImageNamed:@"menubar-right-7.png"] resizableImageWithCapInsets:UIEdgeInsetsMake(5, 5, 0, 5)];
    }
    
    [[UINavigationBar appearance] setBackgroundImage:navBarImage forBarMetrics:UIBarMetricsDefault]; 
    [[UINavigationBar appearance] setTitleTextAttributes:
     @{UITextAttributeTextColor: [UIColor colorWithRed:255.0/255.0 green:255.0/255.0 blue:255.0/255.0 alpha:1.0], 
      UITextAttributeTextShadowColor: [UIColor colorWithRed:0.0 green:0.0 blue:0.0 alpha:0.8], 
      UITextAttributeTextShadowOffset: [NSValue valueWithUIOffset:UIOffsetMake(0, -1)]}];

    
    UIImage *minImage = [UIImage tallImageNamed:@"slider-fill.png"];
    UIImage *maxImage = [UIImage tallImageNamed:@"slider-track.png"];
    UIImage *thumbImage = [UIImage tallImageNamed:@"slider-handle.png"];
    [[UISearchBar appearance] setBackgroundImage:[UIImage tallImageNamed:@"searchbar-bg.png"]];
    
    [[UISlider appearance] setMaximumTrackImage:maxImage forState:UIControlStateNormal];
    [[UISlider appearance] setMinimumTrackImage:minImage forState:UIControlStateNormal];
    [[UISlider appearance] setThumbImage:thumbImage forState:UIControlStateNormal];
    [[UISlider appearance] setThumbImage:thumbImage forState:UIControlStateHighlighted];
    
    
    UIImage *barItemImage = [[UIImage tallImageNamed:@"menubar-button.png"] resizableImageWithCapInsets:UIEdgeInsetsMake(0, 5, 0, 5)];
    [[UIBarButtonItem appearance] setBackgroundImage:barItemImage forState:UIControlStateNormal barMetrics:UIBarMetricsDefault];
    
      
}


-(void)iPadInit
{
    UISplitViewController *splitViewController = (UISplitViewController *)self.window.rootViewController; 
    
    splitViewController.delegate = [splitViewController.viewControllers lastObject];
    
    
    id<MasterViewControllerDelegate> delegate = [splitViewController.viewControllers lastObject];
    UINavigationController* nav = (splitViewController.viewControllers)[0];
    
    MasterViewController* master = (nav.viewControllers)[0];
    
    master.delegate = delegate;
    
}


-(void)configureiPhoneTabBar
{
    UITabBarController *tabBarController = (UITabBarController *)self.window.rootViewController;
	
    UIViewController *controller1 = [tabBarController viewControllers][0];
    [self configureTabBarItemWithImageName:@"tab-icon1.png" andText:@"Elements" forViewController:controller1];
    
    
    UIViewController *controller2 = [tabBarController viewControllers][1];
    [self configureTabBarItemWithImageName:@"tab-icon2.png" andText:@"Buttons" forViewController:controller2];
    
    
    UIViewController *controller3 = [tabBarController viewControllers][2];
    [self configureTabBarItemWithImageName:@"tab-icon3.png" andText:@"List" forViewController:controller3];
    
    
    UIViewController *controller4 = [tabBarController viewControllers][3];
    [self configureTabBarItemWithImageName:@"tab-icon4.png" andText:@"Other" forViewController:controller4];
}

-(void)configureTabBarItemWithImageName:(NSString*)imageName andText:(NSString *)itemText forViewController:(UIViewController *)viewController
{
    UIImage* icon1 = [UIImage tallImageNamed:imageName];
    UITabBarItem *item1 = [[UITabBarItem alloc] initWithTitle:itemText image:icon1 tag:0];
    [item1 setFinishedSelectedImage:icon1 withFinishedUnselectedImage:icon1];
    
    [viewController setTabBarItem:item1];
}

							
- (void)applicationWillResignActive:(UIApplication *)application
{
    /*
     Sent when the application is about to move from active to inactive state. This can occur for certain types of temporary interruptions (such as an incoming phone call or SMS message) or when the user quits the application and it begins the transition to the background state.
     Use this method to pause ongoing tasks, disable timers, and throttle down OpenGL ES frame rates. Games should use this method to pause the game.
     */
}

- (void)applicationDidEnterBackground:(UIApplication *)application
{
    /*
     Use this method to release shared resources, save user data, invalidate timers, and store enough application state information to restore your application to its current state in case it is terminated later. 
     If your application supports background execution, this method is called instead of applicationWillTerminate: when the user quits.
     */
}

- (void)applicationWillEnterForeground:(UIApplication *)application
{
    /*
     Called as part of the transition from the background to the inactive state; here you can undo many of the changes made on entering the background.
     */
}

- (void)applicationDidBecomeActive:(UIApplication *)application
{
    /*
     Restart any tasks that were paused (or not yet started) while the application was inactive. If the application was previously in the background, optionally refresh the user interface.
     */
}

- (void)applicationWillTerminate:(UIApplication *)application
{
    /*
     Called when the application is about to terminate.
     Save data if appropriate.
     See also applicationDidEnterBackground:.
     */
}

@end
