//
//  MasterViewController.h
//  gunmetal
//
//  Created by Valentin on 5/13/12.
//  Copyright (c) 2012 AppDesignVault. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <QuartzCore/QuartzCore.h>

@protocol MasterViewControllerDelegate;

@interface MasterViewController : UIViewController <UITableViewDelegate, UITableViewDataSource>

@property (nonatomic, strong) IBOutlet UITableView* masterTableView;

@property (nonatomic, strong) NSArray* models;

@property (nonatomic, unsafe_unretained) id<MasterViewControllerDelegate> delegate;

@end


@protocol MasterViewControllerDelegate <NSObject>


@end