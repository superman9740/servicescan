//
//  CustomCameraRectangle.m
//  Contxt
//
//  Created by sdickson on 1/3/14.
//

#import "CustomCameraRectangle.h"

@implementation CustomCameraRectangle

- (id)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self) {
        [self setBackgroundColor:[UIColor clearColor]];
        [self.layer setBorderWidth:2.0];
        [self.layer setCornerRadius:4.0];
        [self.layer setBorderColor:[UIColor yellowColor].CGColor];
        
        
    }
    return self;
}

- (void)animationDidStop:(CAAnimation *)theAnimation finished:(BOOL)flag
{
    [self.layer removeAllAnimations];
    [self.layer setBorderColor:[UIColor clearColor].CGColor];
    
    
}

// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
/*
- (void)drawRect:(CGRect)rect
{
   
}
*/


@end
