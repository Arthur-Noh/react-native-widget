import Foundation
import WidgetKit

@objc(Widget)
class Widget: NSObject {

  private var suiteName: String?

  @objc(setSuiteName:)
  func setSuiteName(suiteName: String) {
    self.suiteName = suiteName
  }

  @objc(saveTextToUserDefaults:)
  func saveTextToUserDefaults(text: String) {
    guard let suiteName = suiteName else {
      print("App Group suiteName not set")
      return
    }

    let sharedDefaults = UserDefaults(suiteName: suiteName)
    sharedDefaults?.set(text, forKey: "widgetText")
    sharedDefaults?.synchronize()

    // 추가로 위젯을 즉시 업데이트 하도록 요청하기
    if #available(iOS 14.0, *) {
      WidgetCenter.shared.reloadAllTimelines()
    } else {
      print("WidgetCenter is not available on this iOS version")
    }
  }
}
