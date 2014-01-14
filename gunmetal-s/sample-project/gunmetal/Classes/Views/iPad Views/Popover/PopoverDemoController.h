//
//  PopoverDemoController.h
//  gunmetal
//
//  Created by Valentin on 5/13/12.
//  Copyright (c) 2012 AppDesignVault. All rights reserved.
//

#import <UIKit/UIKit.h>


@protocol PopoverDemoControllerDelegate;


@interface PopoverDemoController : UIViewController <UITableViewDataSource, UITableViewDelegate, UISearchBarDelegate> {
    
    NSMutableArray *listOfItems;
    NSMutableArray *copyListOfItems;
    IBOutlet UISearchBar *searchBar;
    BOOL searching;
    BOOL letUserSelectRow;
}

- (void) searchTableView;
- (void) doneSearching_Clicked:(id)sender;

@property (unsafe_unretained, nonatomic) IBOutlet id <PopoverDemoControllerDelegate> delegate;
@property (strong, nonatomic) IBOutlet UITableView *tableView;

- (IBAction)done:(id)sender;

@end


@protocol PopoverDemoControllerDelegate
- (void)popoverDemoControllerDidFinish:(PopoverDemoController *)controller;
@end