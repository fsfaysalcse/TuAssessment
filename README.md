### Tu Assessment Setup Guide: A-Z

This guide covers setting up a Kotlin Multiplatform (KMP) project targeting **Android** and **iOS**, along with configuring the necessary SDKs, dependencies, and tools.

---

#### **1. Initial Setup**

##### **Install Prerequisites**
- **JDK**: Install [JDK 11+](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).
- **Kotlin Plugin**: Ensure the Kotlin Multiplatform plugin is installed in IntelliJ IDEA or Android Studio.
- **Android Studio**: Download the latest version with Compose Multiplatform support.
- **Xcode**: Install Xcode for building and running the iOS app.
- **CocoaPods**: Install CocoaPods via:
  ```bash
  sudo gem install cocoapods
  ```

---

#### **2. Create the KMP Project**
1. Open IntelliJ IDEA or Android Studio.
2. Select **File > New > Project**.
3. Choose **Kotlin Multiplatform > Multiplatform Mobile Application**.
4. Configure the project:
  - **Name**: Your project name.
  - **Package Name**: E.g., `com.example.kmpapp`.
  - **Targets**: Select `Android` and `iOS`.

---

#### **3. Project Structure**
- `/composeApp`: Shared UI and business logic.
  - `commonMain`: Shared code (Kotlin, Compose Multiplatform).
  - `androidMain`: Android-specific implementations.
  - `iosMain`: iOS-specific implementations.
- `/androidApp`: Android application entry point.
- `/iosApp`: iOS application entry point (Swift + Compose Multiplatform bridge).

---

#### **4. Configure Build Settings**

##### **`build.gradle.kts` (Root)**

Add Kotlin Multiplatform dependencies:
```kotlin
plugins {
    kotlin("multiplatform") version "1.9.10"
    id("com.android.application")
    id("org.jetbrains.compose") version "1.5.0"
}

repositories {
    google()
    mavenCentral()
}
```

---

#### **5. Add Dependencies**

##### **Compose Multiplatform UI**
In `/composeApp/build.gradle.kts`:
```kotlin
kotlin {
    android()
    ios()
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("androidx.compose.ui:ui:1.5.0")
            }
        }
        val iosMain by getting {
            dependencies {
                implementation("org.jetbrains.compose.ui:ui-ios:1.5.0")
            }
        }
    }
}
```

---

#### **6. Android-Specific Setup**
In `/androidApp/build.gradle.kts`:
```kotlin
android {
    compileSdk = 34
    defaultConfig {
        applicationId = "com.example.kmpapp"
        minSdk = 24
        targetSdk = 34
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.0"
    }
}
```

---

#### **7. iOS-Specific Setup**

##### **Configure CocoaPods**
In `/iosApp/Podfile`:
```ruby
platform :ios, '13.0'
use_frameworks!

target 'iosApp' do
  pod 'ComposeApp', :path => '../composeApp'
end
```

Run:
```bash
cd iosApp
pod install
```

---

#### **8. Run the Project**

##### **Run Android App**
1. Open **Android Studio**.
2. Select the `androidApp` module.
3. Run the app on an Android emulator or device.

##### **Run iOS App**
1. Open the `.xcworkspace` file in Xcode.
2. Select your iOS device or simulator.
3. Build and run the app.

---

#### **9. Debugging**
- **Shared Code**: Debug in IntelliJ IDEA.
- **Platform-Specific Code**: Use platform-specific tools like Android Studio (Logcat) or Xcode (debugger).

---

#### **10. Add Extra Libraries**
You can integrate libraries like `Ktor` for networking, `SQLDelight` for database handling, or `Koin` for dependency injection by adding them to `commonMain` and their platform-specific counterparts.

---

#### **11. Deployment**

##### **Android**
1. Generate an APK or AAB via Android Studio.
2. Upload to the Google Play Console.

##### **iOS**
1. Archive the app in Xcode.
2. Distribute it via TestFlight or the App Store.

---

#### **12. Learning Resources**
- [Kotlin Multiplatform Docs](https://kotlinlang.org/docs/multiplatform.html)
- [Compose Multiplatform](https://www.jetbrains.com/compose/mpp/)

This setup guide ensures that your Kotlin Multiplatform project is configured from start to finish, ready for both Android and iOS.