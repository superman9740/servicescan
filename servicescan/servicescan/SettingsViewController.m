//
//  SettingsViewController.m
//  servicescan
//
//  Created by sdickson on 1/22/14.
//  Copyright (c) 2014 ambro. All rights reserved.
//

#import "SettingsViewController.h"
#import "UIImage+iPhone5.h"
#import "Utils.h"
#import "MasterCell.h"
#import "ContractorSettingsViewController.h"
#import "UserTypeViewController.h"
#import "InAppPurchaseViewController.h"


@interface SettingsViewController ()

@end

@implementation SettingsViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}


- (void)viewDidLoad
{
    [super viewDidLoad];

    self.tableView.backgroundColor = [UIColor colorWithPatternImage:[UIImage tallImageNamed:@"bg.png"]];
    self.tableView.separatorStyle = UITableViewCellSeparatorStyleNone;

    [self.view setBackgroundColor:[UIColor colorWithPatternImage:[UIImage imageNamed:@"bg.png"]]];
    self.title = @"Settings";
    
    
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

#pragma mark - Table view data source

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    // Return the number of sections.
    return 3;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{

    // Return the number of rows in the section.
    return 1;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    static NSString *CellIdentifier = @"Cell";
    UITableViewCell* cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier];
    
    switch (indexPath.section)
    {
        case 0:
        {
            cell.textLabel.text = @"Address Information";
            break;
            
        }
        case 1:
        {
            cell.textLabel.text = @"Buy more QR Labels";
            break;
            
        }
        case 2:
        {
            cell.textLabel.text = @"Reset User Type";
            break;
            
        }
        default:
            break;
    }
    
    
    
    
    return cell;
}

- (NSString *)tableView:(UITableView *)tableView titleForHeaderInSection:(NSInteger)section
{
    NSString* returnString;
    
    switch (section)
    {
        case 0:
        {
            returnString = @"Contractor Information";
            break;
            
        }
        case 1:
        {
            returnString = @"In-App Purchases";
            break;
            
        }
        case 2:
        {
            returnString = @"Misc";
            break;
            
        }
            
        default:
            break;
    }
    
    return returnString;
    
}
- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    
    switch (indexPath.section)
    {
        case 0:
        {
            ContractorSettingsViewController* viewController = [self.storyboard instantiateViewControllerWithIdentifier:@"contractorSettings"];
            [self.navigationController pushViewController:viewController animated:YES];
            
            break;
            
        }
        case 1:
        {
            InAppPurchaseViewController* purchaseViewController = [self.storyboard instantiateViewControllerWithIdentifier:@"inAppPurchase"];
            [self.navigationController pushViewController:purchaseViewController animated:YES];
            
            break;
            
        }
        case 2:
        {
            
            UserTypeViewController* userTypeViewController = [self.storyboard instantiateViewControllerWithIdentifier:@"resetUserType"];
            [self.navigationController pushViewController:userTypeViewController animated:YES];
            
            break;
            
        }
            
        default:
            break;
    }
    
    
    
    
}

/*
// Override to support conditional editing of the table view.
- (BOOL)tableView:(UITableView *)tableView canEditRowAtIndexPath:(NSIndexPath *)indexPath
{
    // Return NO if you do not want the specified item to be editable.
    return YES;
}
*/

/*
// Override to support editing the table view.
- (void)tableView:(UITableView *)tableView commitEditingStyle:(UITableViewCellEditingStyle)editingStyle forRowAtIndexPath:(NSIndexPath *)indexPath
{
    if (editingStyle == UITableViewCellEditingStyleDelete) {
        // Delete the row from the data source
        [tableView deleteRowsAtIndexPaths:@[indexPath] withRowAnimation:UITableViewRowAnimationFade];
    }   
    else if (editingStyle == UITableViewCellEditingStyleInsert) {
        // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
    }   
}
*/

/*
// Override to support rearranging the table view.
- (void)tableView:(UITableView *)tableView moveRowAtIndexPath:(NSIndexPath *)fromIndexPath toIndexPath:(NSIndexPath *)toIndexPath
{
}
*/

/*
// Override to support conditional rearranging of the table view.
- (BOOL)tableView:(UITableView *)tableView canMoveRowAtIndexPath:(NSIndexPath *)indexPath
{
    // Return NO if you do not want the item to be re-orderable.
    return YES;
}
*/

/*
#pragma mark - Navigation

// In a story board-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}

 */

@end
