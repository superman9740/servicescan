//
//  CustomCameraViewController.m
//  CameraApp
//
//  Created by Shane Dickson on 12/17/13.
//  Copyright (c) 2013 Jay. All rights reserved.
//

#import "CustomCameraViewController.h"

static inline double radians (double degrees) {return degrees * M_PI/180;}


@interface CustomCameraViewController ()

@end

@implementation CustomCameraViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
    images = [[NSMutableArray alloc] initWithCapacity:10];
    
    _previewLayer.frame = _cameraView.frame;
    [self setupCaptureSession:[self backCamera]];
    rectangleView = [[CustomCameraRectangle alloc] initWithFrame:CGRectMake(20, 20, 200, 200)];
    rectangleView.center = CGPointMake(self.view.bounds.size.width / 2, self.view.bounds.size.height / 2 - 20);
    
    
    [self.view addSubview:rectangleView];
    
    

    
   
 
    
}



-(void)viewDidAppear:(BOOL)animated
{
    
    
    
}

- (void)setupCaptureSession:(AVCaptureDevice*)camera
{
    NSError *error = nil;
    
    _session = [[AVCaptureSession alloc] init];
    _session.sessionPreset = AVCaptureSessionPresetHigh;
    
    
    error=nil;
    AVCaptureInput* cameraInput = [[AVCaptureDeviceInput alloc] initWithDevice:camera error:&error];
    
    [_session setSessionPreset:AVCaptureSessionPresetHigh];
    [_session addInput:cameraInput];
    
    
    _previewLayer = [AVCaptureVideoPreviewLayer layerWithSession:_session];
    _previewLayer.frame = _cameraView.frame;
    _previewLayer.videoGravity = AVLayerVideoGravityResizeAspectFill;
    [_previewLayer.connection setVideoOrientation:AVCaptureVideoOrientationPortrait];
    //[_cameraView.layer insertSublayer:_previewLayer atIndex:0];
    [_cameraView.layer addSublayer:_previewLayer];
    
    
  //  [self setStillImageOutput:[[AVCaptureStillImageOutput alloc] init]];
    
    self.output = [[AVCaptureMetadataOutput alloc] init];
    [self.session addOutput:self.output];
    dispatch_queue_t dispatchQueue;
    dispatchQueue = dispatch_queue_create("myQueue", NULL);
    [self.output setMetadataObjectsDelegate:self queue:dispatchQueue];
    [self.output setMetadataObjectTypes:[NSArray arrayWithObject:AVMetadataObjectTypeQRCode]];
    [self.view bringSubviewToFront:_topView];
    [self.view bringSubviewToFront:_bottomView];
    
    [_session startRunning];
    
    
}

- (void)captureOutput:(AVCaptureOutput *)captureOutput didOutputMetadataObjects:(NSArray *)metadataObjects
       fromConnection:(AVCaptureConnection *)connection
{
    for(AVMetadataObject *current in metadataObjects) {
        if([current isKindOfClass:[AVMetadataMachineReadableCodeObject class]]) {
                NSString *scannedValue = [((AVMetadataMachineReadableCodeObject *) current) stringValue];
            dispatch_async(dispatch_get_main_queue(), ^{
            
                [self.session stopRunning];
                [self.delegate didCaptureQRCode:scannedValue];
                [self.session startRunning];
                
               // [_previewLayer removeFromSuperlayer];
                
                
            });
            
            
            
            
            
            }
        }
    }


-(IBAction)switchCameras:(id)sender
{
    [_session stopRunning];
    if(usingFrontCamera)
    {
        [self setupCaptureSession:[self backCamera]];
        
    }
    else
    {
        [self setupCaptureSession:[self frontCamera]];
        
    }
    
}

- (AVCaptureDevice *)frontCamera {
    NSArray *devices = [AVCaptureDevice devicesWithMediaType:AVMediaTypeVideo];
    for (AVCaptureDevice *device in devices) {
        if ([device position] == AVCaptureDevicePositionFront)
        {
            usingFrontCamera = YES;
            [_session beginConfiguration];
            [device lockForConfiguration:nil];
            
            [device setTorchMode:AVCaptureTorchModeAuto];
            [device unlockForConfiguration];
            [_session commitConfiguration];
            
            return device;
        }
    }
    return nil;
}

- (AVCaptureDevice *)backCamera {
    NSArray *devices = [AVCaptureDevice devicesWithMediaType:AVMediaTypeVideo];
    for (AVCaptureDevice *device in devices) {
        if ([device position] == AVCaptureDevicePositionBack)
        {
            usingFrontCamera = NO;
            [_session beginConfiguration];
            [device lockForConfiguration:nil];
            
            [device setTorchMode:AVCaptureTorchModeAuto];
            [device unlockForConfiguration];
            [_session commitConfiguration];
            
            return device;
        }
    }
    return nil;
}



-(IBAction)selectFromCameraRoll:(id)sender;
{
    pickerController = [[UIImagePickerController alloc] init];
    pickerController.sourceType = UIImagePickerControllerSourceTypePhotoLibrary;
    
    pickerController.mediaTypes =     [UIImagePickerController availableMediaTypesForSourceType:UIImagePickerControllerSourceTypeCamera];
    
    pickerController.allowsEditing = NO;
    pickerController.delegate = self;
    
    [self presentViewController:pickerController animated:YES completion:nil];
    
    
}

- (void) imagePickerController: (UIImagePickerController *) picker didFinishPickingMediaWithInfo: (NSDictionary *) info {
    
    NSString *mediaType = [info objectForKey: UIImagePickerControllerMediaType];
    UIImage *originalImage, *editedImage, *imageToSave;
    
    // Handle a still image capture
    if (CFStringCompare ((CFStringRef) mediaType, kUTTypeImage, 0)
        == kCFCompareEqualTo) {
        
        editedImage = (UIImage *) [info objectForKey:
                                   UIImagePickerControllerEditedImage];
        originalImage = (UIImage *) [info objectForKey:
                                     UIImagePickerControllerOriginalImage];
        
        if (editedImage) {
            imageToSave = editedImage;
        } else {
            imageToSave = originalImage;
        }
        
        [images addObject:imageToSave];
        [self updatePicRollView:self];
        
    
    [pickerController dismissViewControllerAnimated:YES completion:nil];
    }
    
}


-(IBAction)done:(id)sender
{
    
   // [_delegate didFinishImageSelection:images];
    [self dismissViewControllerAnimated:YES completion:nil];
    
}


- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    
}

@end
