<div align="center">
    <h1>Messenger App</h1>
    <img src="View/src/main/res/drawable/app_logo.png" alt="Logo" width="200"/>
</div>

A modern messaging application developed using the MVVM pattern with a variety of libraries to ensure a robust and user-friendly experience.

<div align="center">
    <h1>GIF</h1>
    <img src="View/src/main/res/video/example_gif.gif" alt="App GIF" width="200"/>
</div>

## Description

This project is a feature-rich messaging app that enables users to send and receive messages in real-time. The app utilizes a combination of modern Android libraries and Firebase for backend services, ensuring a seamless and responsive user experience. The UI is built using Jetpack Compose, providing a modern look and feel.

## Getting Started

### Dependencies

* Kotlin 1.9.0
* Gradle 8.2.2
* Java 1.8
* Android Studio (latest version recommended)
* [Jetpack Compose](https://developer.android.com/jetpack/compose) for UI
* [Firebase](https://firebase.google.com/) for authentication, database, and storage
* [Dagger-Hilt](https://dagger.dev/hilt/) for dependency injection

Below are the specific libraries used in the project:

```groovy
implementation ("androidx.navigation:navigation-fragment-ktx:2.7.7")
implementation ("androidx.navigation:navigation-ui-ktx:2.7.7")
implementation("androidx.core:core-ktx:1.13.1")
implementation("androidx.appcompat:appcompat:1.6.1")
implementation("com.google.android.material:material:1.12.0")
implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.0")
implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0")
implementation("androidx.activity:activity-compose:1.9.0")
implementation(platform("androidx.compose:compose-bom:2023.08.00"))
implementation ("androidx.compose.runtime:runtime-livedata:1.6.5")
implementation ("com.google.accompanist:accompanist-systemuicontroller:0.24.3-alpha")
implementation("com.google.firebase:firebase-common-ktx:21.0.0")
implementation ("com.google.firebase:firebase-core:21.1.1")
implementation("com.google.firebase:firebase-auth-ktx:23.0.0")
implementation ("androidx.compose.ui:ui:1.6.7")
implementation ("androidx.compose.material:material:1.6.7")
implementation ("androidx.compose.ui:ui-tooling-preview:1.6.7")
implementation ("androidx.navigation:navigation-compose:2.7.7")
implementation ("androidx.compose.runtime:runtime-livedata:1.6.7")
implementation("com.google.firebase:firebase-database:21.0.0")
implementation("com.google.firebase:firebase-storage:21.0.0")
implementation("androidx.navigation:navigation-compose:2.7.7")
androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
runtimeOnly("com.google.dagger:hilt-android:2.49")
runtimeOnly("androidx.navigation:navigation-compose:2.7.7")
runtimeOnly("androidx.compose.runtime:runtime-livedata:0.1.0-dev17")
implementation(platform("androidx.compose:compose-bom:2023.08.00"))
implementation("androidx.compose.ui:ui")
implementation("androidx.compose.ui:ui-graphics")
implementation("androidx.compose.ui:ui-tooling-preview")
implementation("androidx.compose.material3:material3")
implementation ("androidx.activity:activity-compose:1.3.1")
implementation ("io.coil-kt:coil-compose:1.4.0")
testImplementation("junit:junit:4.13.2")
androidTestImplementation("androidx.test.ext:junit:1.1.5")
androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
androidTestImplementation("androidx.compose.ui:ui-test-junit4")
debugImplementation("androidx.compose.ui:ui-tooling")
debugImplementation("androidx.compose.ui:ui-test-manifest")
```

### Installing

1. Clone the repository to your local machine:
   ```bash
   git clone https://github.com/NovikFeed/chat-application.git
   ```
2. Navigate to the project directory:
   ```bash
   cd chat-aplication
   ```
3. Open the project in Android Studio.
4. Sync the project with Gradle files by clicking on "Sync Project with Gradle Files" in Android Studio.

### Executing Program

1. Build and run the app by selecting the desired emulator or connected device.
2. Click the "Run" button in Android Studio or use the following command in your terminal:
   ```bash
   ./gradlew assembleDebug
   ```
3. Once the app is installed on your device or emulator, you can start exploring the features.

## Help

If you encounter any issues, please refer to the following command to check the app's log output for troubleshooting:
```bash
adb logcat
```

## Version History

* 0.2
    * Various bug fixes and optimizations
    * Improved UI and user experience
    * See [commit change](https://github.com/NovikFeed/chat-application/commits/develop/) or See [release history](https://github.com/NovikFeed/chat-application/commits/)
* 0.1
    * Initial Release
    * Basic features implemented

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgments

* Inspired by various open-source projects and tutorials on Android development.

## Other project

[MovieApp](https://github.com/NovikFeed/MoviesApp)
