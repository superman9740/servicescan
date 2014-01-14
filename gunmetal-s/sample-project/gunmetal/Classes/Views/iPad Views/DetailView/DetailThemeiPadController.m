//
//  DetailThemeiPadController.m
//  gunmetal
//
//  Created by Valentin on 5/13/12.
//  Copyright (c) 2012 AppDesignVault. All rights reserved.
//

#import "DetailThemeiPadController.h"
#import "AppDelegate.h"
#import "STSegmentedControl.h"
#import "RCSwitchOnOff.h"
#import "PopoverDemoController.h"
#import "CustomPopoverBackgroundView.h"
#import <QuartzCore/QuartzCore.h>
#import "BlockAlertView.h"
#import "SampleSegment.h"
#import "Utils.h"

@implementation DetailThemeiPadController
@synthesize navigationItem;
@synthesize viewTestImg;

@synthesize toolbar, shadowView, progressBar, slider, scrollView, showPopoverButton, popoverController;


#pragma mark - View lifecycle

- (void)viewDidLoad {
    
    if(![Utils isVersion6AndBelow]){
        [[UIApplication sharedApplication] setStatusBarStyle:UIStatusBarStyleLightContent];
        UINavigationBar *navBar = (UINavigationBar *)[self.view viewWithTag:1];
        CGRect newFrame = navBar.frame;
        newFrame.size.height += 20;
        navBar.frame = newFrame;
    }
    
    UIColor* bgColor = [UIColor colorWithPatternImage:[UIImage tallImageNamed:@"bg_landscape.png"]];
    [self.view setBackgroundColor:bgColor];
    
    CALayer* shadowLayer = [self createShadowWithFrame:CGRectMake(0, 0, 768, 5)];
    [shadowView.layer addSublayer:shadowLayer];
    [self.view addSubview:shadowView];
    
    RCSwitchOnOff* onSwitch = [[RCSwitchOnOff alloc] initWithFrame:CGRectMake(300, 280, 80, 36)];
    [onSwitch setOn:YES];    
    [scrollView addSubview:onSwitch];
    
    RCSwitchOnOff* offSwitch = [[RCSwitchOnOff alloc] initWithFrame:CGRectMake(390, 280, 80, 36)];
    [offSwitch setOn:NO];    
    [scrollView addSubview:offSwitch];
    
    self.progressBar = [[ADVPopoverProgressBar alloc] initWithFrame:CGRectMake(218, 340, 327, 24) andProgressBarColor:ADVProgressBarBlue];
    [progressBar setProgress:0.5];    
    [scrollView addSubview:progressBar];    
    
    [slider setFrame:CGRectMake(218, 410, 327, 24)];
    
    NSArray *objects = @[@"Yes", @"No", @"Maybe"];
    SampleSegment *segment = [[SampleSegment alloc] init];
    segment.titles = objects;
	segment.frame = CGRectMake(250, 460, 250, 45);
	segment.autoresizingMask = UIViewAutoresizingFlexibleWidth;
	[scrollView addSubview:segment];
        
    [super viewDidLoad];
}

- (IBAction)showAlert:(id)sender {
    BlockAlertView *alert = [BlockAlertView alertWithTitle:@"Alert Title" message:@"This is an alert message"];
    
    [alert setDestructiveButtonWithTitle:@"Cancel" block:nil];
    [alert addButtonWithTitle:@"OK" block:^{
        [self showAlert:nil];
    }];
    [alert show];
}

-(IBAction)valueChanged:(id)sender {
    if([sender isKindOfClass:[UISlider class]]) {
        UISlider *s = (UISlider*)sender;
        
        if(s.value >= 0.0 && s.value <= 1.0) {
            [progressBar setProgress:s.value];
        }
    }
}

-(CALayer *)createShadowWithFrame:(CGRect)frame {
    CAGradientLayer *gradient = [CAGradientLayer layer];
    gradient.frame = frame;    
    
    UIColor* lightColor = [[UIColor blackColor] colorWithAlphaComponent:0.0];
    UIColor* darkColor = [[UIColor blackColor] colorWithAlphaComponent:0.3];
    
    gradient.colors = @[(id)darkColor.CGColor, (id)lightColor.CGColor];
    
    return gradient;
}

- (void)viewDidUnload {
    [self setViewTestImg:nil];
    [self setNavigationItem:nil];
    [super viewDidUnload];
}

- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation {
	return YES;
}

- (void)popoverDemoControllerDidFinish:(PopoverDemoController *)controller {
    [self.popoverController dismissPopoverAnimated:YES];
    self.popoverController = nil;
}

- (void)popoverControllerDidDismissPopover:(UIPopoverController *)popoverController {
    self.popoverController = nil;
}

- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    if ([[segue identifier] isEqualToString:@"showPopover"]) {
        [self togglePopover:nil];
        [[segue destinationViewController] setDelegate:self];
        UIPopoverController *p = [(UIStoryboardPopoverSegue *)segue popoverController];
        p.popoverBackgroundViewClass = [KSCustomPopoverBackgroundView class];
        self.popoverController = p;
        
        popoverController.delegate = self;
    }
}

- (IBAction)togglePopover:(id)sender {
    if (self.popoverController) {
        [self.popoverController dismissPopoverAnimated:YES];
        self.popoverController = nil;
    }
}



- (void)splitViewController: (UISplitViewController *)splitViewController 
     willHideViewController:(UIViewController *)viewController 
          withBarButtonItem:(UIBarButtonItem *)barButtonItem
       forPopoverController: (UIPopoverController *)popoverController
{
    barButtonItem.title = @"Master";
    barButtonItem.tintColor = [UIColor whiteColor];
    NSMutableArray *items = [self.navigationItem.leftBarButtonItems mutableCopy]; 
    [items insertObject:barButtonItem atIndex:0];
    self.navigationItem.leftBarButtonItems = items; 

}


- (void)splitViewController:(UISplitViewController *)splitController 
     willShowViewController:(UIViewController *)viewController
  invalidatingBarButtonItem:(UIBarButtonItem *)barButtonItem
{
    NSMutableArray *items = [self.navigationItem.leftBarButtonItems mutableCopy]; 
    [items removeObject:barButtonItem];
    self.navigationItem.leftBarButtonItems = items; 
    //masterPopoverController = nil;
}



-(UIBarButtonItem*)createBarButtonWithImageName:(NSString *)imageName andSelectedImage:(NSString*)selectedImageName {
    UIImage* buttonImage = [UIImage tallImageNamed:imageName];
    
    UIButton* button = [[UIButton alloc] initWithFrame:CGRectMake(0, 0, buttonImage.size.width, buttonImage.size.height)];
    [button setImage:buttonImage forState:UIControlStateNormal];
    [button setImage:[UIImage tallImageNamed:selectedImageName] forState:UIControlStateHighlighted];
    
    
    UIBarButtonItem* barButton = [[UIBarButtonItem alloc] initWithCustomView:button];
    
    return barButton;
}

-(UIBarButtonItem*)createBarButtonWithImageName:(NSString *)imageName selectedImage:(NSString*)selectedImageName andSelector:(SEL)selector {
    UIImage* buttonImage = [UIImage tallImageNamed:imageName];
    
    UIButton* button = [[UIButton alloc] initWithFrame:CGRectMake(0, 0, buttonImage.size.width, buttonImage.size.height)];
    [button setImage:buttonImage forState:UIControlStateNormal];
    [button setImage:[UIImage tallImageNamed:selectedImageName] forState:UIControlStateHighlighted];
    [button addTarget:self action:selector forControlEvents:UIControlEventTouchUpInside];
    
    UIBarButtonItem* barButton = [[UIBarButtonItem alloc] initWithCustomView:button];
    
    return barButton;
}

@end
