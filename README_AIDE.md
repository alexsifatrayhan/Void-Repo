# Amarok File Security - AIDE Build Instructions

This project is structured as a standard Android Gradle project, compatible with **AIDE**, **Android Studio**, and other Android IDEs.

## Project Structure
- `app/src/main/java`: Contains the Java source code.
  - `MainActivity.java`: UI and lifecycle logic (Panic Mode).
  - `FileLocker.java`: Core obfuscation logic (Rename/Move).
  - `CryptoUtils.java`: AES-256 encryption for the filename mapping.
- `app/src/main/res`: Contains the XML layout and glassy UI resources.
- `app/src/main/AndroidManifest.xml`: Defines permissions and Stealth Aliases (Calculator/Weather).

## How to Build in AIDE
1. Copy the contents of this project to your Android device.
2. Open **AIDE**.
3. Select **"Open Project"** and navigate to the root directory (containing `build.gradle`).
4. AIDE will automatically recognize the Gradle project.
5. Tap **"Run"** to build the APK.

## Features Included
- **File Obfuscation**: Files are renamed to random `.dat` strings and moved to an internal hidden directory.
- **Passkey Protection**: The mapping is encrypted with AES-256 using your passkey.
- **Panic Mode**: A toggle that automatically locks your set files the moment the app loses focus.
- **Stealth Mode**: Changes the app icon to a Calculator to hide in plain sight.

## Local-Only Privacy
This app requires no internet permissions. All operations are strictly local to ensure maximum privacy.
