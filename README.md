# PA1414 Software Engineering Project HT26

## Project Description

**Project Name:** *Twitchy*

**Customer Name:** *Fredrik Gullbrandson*

**Student Full Name:** *Jacob Thörn*

### Project Aim and Objectives

*Twitchy is a small wearable device that with the help of a mobile application is designed to detect and track falls/hard impacts on the user. The device is fitted with an accelerometer, gyroscope and a barometric pressure sensor to get accurate readings with as few false positives as possible. The application features a section for viewing historical fall data and a real-time motion graph updating based on the incoming sensor data. To make the system reliable, it's mandatory to go through with a baseline calibration set up.*
*Twitchy is designed for people with an interest in tracking their own falls or impacts, that could vary all the way from elderly, healthcare professionals or young and fit individuals who can implement the tracking in sports. The main features are:* 
* A live motion graph that moves as you fall or get "close" to falling.
* Live-updating visual effects of falls in the application, as well as a "Status" card in the application.
* The ability to set a baseline and get accurate readings personal for you.
* Offers the user a history of historical falls/impacts.
* Low chance of false positives due to multiple sensors.
* Comfortable and unrestricting of day to day life.

### User Personas

| Name | Characteristics | Needs and Expectations | How will they use the system? |
|---|---|---|---|
| *Kerstin* | *71 years old, retired and lives alone. Low technical skill level.* | *Needs falls to be detected and logged to track progress.* | *Wears the device on her belt, or pants. Sometimes checking the history of her falls.* |
| *Kalle* | *40, works in health care. Good technical skill level.* | *Wants to stay informed about his patient's physical health and safety over time.* | *Looks through the fall history directly on the patient's phone during a health checkup.* |
| *John* | *26, professional mountain biker, average technical skill level.* | *Wants to track his falls/crashes during training sessions.* | *Wears the device on his belt during training, he relies on the device logging falls/crashes locally, without internet connection.* |

### Software Requirements

**Functional Requirements**
- *The device should detect falls or hard impacts using data from accelerometer and gyroscope*
- *The application displays a live-updating motion graph based on sensor data*
- *To reduce false positives of a fall event a barometric pressure sensor is used to track distance*
- *The device should allow the user to create a personal profile with a baseline calibration, so the system can track accurately*
- *The device handles continuous low-latency telemetry streaming over Bluetooth Low Energy (BLE)*
- *The system alerts the app in real time when an event such as a fall or hard impact happens*

**Non-Functional Requirements**
- *A detected fall must be logged on the mobile application within 5 seconds of detection*
- *User profile data stored on the phone must be encrypted*
- *Pairing between the device and mobile application must take no longer than 10 seconds*
- *Pairing must also use some sort of secure handshake protocol (e.g. BLE passkey pairing or an app-specific token)*
- *The wearable device should be comfortable and lightweight enough to be worn for at least half a day*

### Project Limitations & Final Status

- The project was planned to include hardware, but due to delays in BTH's purchasing process there was not enough time. Therefore, the fall detection app Twitchy is reliant on mock data.
- Because of the mock data being the main and only source of data, the fall detection system has not been tested and calibrated for the real world, as it needs real sensor values to be tuned.
- In firmware/BLE_twitchy.ino there is basic code from a BLE example from Arduino. It was meant to be worked on further, but it was not possible due to hardware limitations.  
  The firmware works and can send values to a connected mobile device, but the app does not connect to it. You can flash the firmware to a powered XIAO ESP32-C3 and connect it through a BLE app such as LightBlue (app on Google Play Store) and see values being sent.
- I have created the interface "SensorDataSource" which would make the switch from mock data to real sensor values easier: the screens, fall detection and storage would not need to be changed.

### User Manual

- **Installing**
  - Android Studio.
  - Android SDK 37 (Android Studio may offer you to install this when you install Android Studio).
  - JDK 17 or higher.
  - Android emulator running Android 12 (API 31) or higher, or a physical device with the same specs.

- **Running**
  - Unpack the ZIP-folder received.
  - Open the inner folder which directly includes app/ in Android Studio.
  - Wait for Gradle to sync, internet connection is required. (this could take a few minutes)
  - Select the preferred device in Android Studio on the left side under the "Device manager" tab.
  - Click the green "play" button in the top bar or press "Shift + F10".
  - The app then builds and launches itself on the device.
  
- **General information about the app**
  - The app has three different screens, Dashboard, History and Profile.
    - Dashboard is the screen you are currently on when first starting, it shows the current status, as well as simulated sensor readings.
    - History is where you can view the fall history, delete falls and clear the history. The falls are automatically added and sorted by severity.
    - Profile is where you can view and edit your profile, such as recording a baseline calibration or updating your name.
  - The data (fall history and profile data) is securely stored on the device with the help of AES-GCM encryption using Android Keystore.
  - The app is currently fed mock data, see Project Limitations & Final Status for more information.

---