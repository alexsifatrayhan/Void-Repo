# Void Repo - AIDE Lite Build Instructions

This project is built using strictly **Native Android Framework** (Legacy) classes to ensure compatibility with **AIDE Lite** and older mobile development environments.

## Highlights for AIDE Lite
- **Zero External Dependencies**: No `androidx`, No `MaterialComponents`.
- **Pure Java & XML**: Uses standard `android.app.Activity` and basic widgets.
- **Offline Friendly**: No Maven libraries to download; builds instantly.

## Project Structure
- `app/src/main/java`: Contains the Java source code.
  - `MainActivity.java`: UI and Panic Mode (using standard `Activity`).
  - `FileLocker.java`: Core obfuscation logic (Rename/Move).
  - `CryptoUtils.java`: AES-256 encryption.
- `app/src/main/res`: Contains the XML layouts and glassy UI resources.
- `app/src/main/AndroidManifest.xml`: Permissions and Stealth Aliases.

## How to Build in AIDE
1. Copy the project folder to your device.
2. Open **AIDE**.
3. Select **"Open Project"** -> find the root directory.
4. Tap **"Run"**. Since there are no heavy libraries, it will compile quickly even on low-end devices.

## Features
- **File Obfuscation**: Randomized renaming and moving to `.data` hidden folder.
- **Passkey Protection**: AES-encrypted mapping.
- **Panic Mode**: Auto-lock on app minimize.
- **Stealth Mode**: "Calculator" icon disguise.
- **Branding**: 🦋⃟ᴠͥɪͣᴘͫ•𝐒𝐑𝟕 𝐌𝐨𝐝𝐬
