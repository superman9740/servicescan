//
//  ServiceCallHistoryViewController.m
//  servicescan
//
//  Created by sdickson on 2/25/14.
//  Copyright (c) 2014 ambro. All rights reserved.
//

#import "ServiceCallHistoryViewController.h"
#import "UIImage+iPhone5.h"
#import "Utils.h"
#import "MasterCell.h"


@interface ServiceCallHistoryViewController ()

@end

@implementation ServiceCallHistoryViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
	
    self.title = @"Service Call History";
    self.tableView.backgroundColor = [UIColor colorWithPatternImage:[UIImage tallImageNamed:@"bg.png"]];
    self.tableView.separatorStyle = UITableViewCellSeparatorStyleNone;
    [[AppController sharedInstance] loadServiceCallHistory];
    
    
}
-(void)viewWillAppear:(BOOL)animated
{
    [self.view setBackgroundColor:[UIColor colorWithPatternImage:[UIImage imageNamed:@"bg.png"]]];
    
    
    
}

-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
    return 80;
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return [[[AppController sharedInstance] serviceCallHistory] count];
}

#pragma mark - Table view delegate

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    static NSString *CellIdentifier = @"Cell";
    ServiceCallHistory* serviceCall = [[[AppController sharedInstance] serviceCallHistory] objectAtIndex:indexPath.row];
    
    MasterCell *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier];
    cell.disclosureImageView.image = [UIImage tallImageNamed:@"list-arrow.png"];
    
    if(![Utils isVersion6AndBelow]){
        cell.backgroundColor = [UIColor clearColor];
    }
    
    cell.titleLabel.text = [NSString stringWithFormat:@"Date:  %@", serviceCall.date];
    cell.textLabel.text = [NSString stringWithFormat:@" %@ ", serviceCall.notes];
    
    
    return cell;
}


#pragma mark - Table view delegate

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
    NSLog(@"Your implementation! :)");
}


- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
