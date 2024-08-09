#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(Widget, NSObject)

// 'setSuiteName' Method
RCT_EXTERN_METHOD(setSuiteName:(NSString *)suiteName)

// 'saveTextToUserDefaults' Method
RCT_EXTERN_METHOD(saveTextToUserDefaults:(NSString *)text)

+ (BOOL)requiresMainQueueSetup
{
return NO;
}

@end
