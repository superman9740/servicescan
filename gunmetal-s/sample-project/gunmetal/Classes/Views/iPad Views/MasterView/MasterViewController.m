//
//  MasterViewController.m
//  gunmetal
//
//  Created by Valentin on 5/13/12.
//  Copyright (c) 2012 AppDesignVault. All rights reserved.
//



#import "MasterViewController.h"
#import "MasterCell.h"
#import "AppDelegate.h"
#import "Utils.h"

@implementation MasterViewController

@synthesize masterTableView, delegate;

@synthesize models;

#pragma mark - View lifecycle

- (void)viewDidLoad {
    self.title = @"Items";   
    
    UIImage *navBarImage = [UIImage tallImageNamed:@"menubar-left.png"];
    
    if(![Utils isVersion6AndBelow]){
        navBarImage = [UIImage tallImageNamed:@"menubar-7.png"];
    }
    
    [self.navigationController.navigationBar setBackgroundImage:navBarImage 
                                       forBarMetrics:UIBarMetricsDefault];
    
    masterTableView.delegate = self;
    masterTableView.dataSource = self;
    
    UIColor* bgColor = [UIColor colorWithPatternImage:[UIImage tallImageNamed:@"bg.png"]];
    [self.view setBackgroundColor:bgColor];
    
    [super viewDidLoad];
}


- (void)viewDidUnload {
    [super viewDidUnload];
}


- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation {
	return YES;
}

#pragma mark - Table view data source

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return 5;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    
    static NSString *CellIdentifier = @"MasterCell"; 
    
    MasterCell *cell = (MasterCell *)[tableView dequeueReusableCellWithIdentifier:CellIdentifier];
    
    [cell setSelectionStyle:UITableViewCellSelectionStyleNone];
    
    cell.disclosureImageView.image = [UIImage tallImageNamed:@"list-arrow.png"];
    
    if(![Utils isVersion6AndBelow]){
        cell.backgroundColor = [UIColor clearColor];
    }
    
    return cell;
    
}

#pragma mark - Table view delegate

-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
    return 80;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {

    
}


@end
