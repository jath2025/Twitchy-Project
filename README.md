# PA1414 Software Engineering Project HT26

## Project Description

**Project Name:** *Twitchy*

**Customer Name:** *Fredrik Gullbrandson*

**Student Full Name:** *Jacob Thörn*

### Project Aim and Objectives

*Twitchy is a small wearable device that with the help of a mobile application is designed to detect and track falls/hard impacts on the user. The device is fitted with an accelerometer, gyroscope and a barometric pressure sesor to get accurate readings with as little false positives as possible. The application features a section for viewing historical fall data and a real-time motion graph updating based on the incoming sensor data. To make the system reliable its mandetory to go through with a baseline calibration set up.*
*Twitchy is designed for people with an intrest in tracking their own falls or impacts, that could vary all the way from eldery, healthcare professinonals or young and fit individuals who can implement the tracking in sports. The main features are:* 
* A live motion graph that moves as you fall or get "close" to falling.
* Live-updating visual effects of falls in the application, as well as a alert.
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


### User Manual

- **Installing**
  - Android Studio
  - JDK 17 or higher
  - Android emulator running Android 8.0 (API 26) or higher, or a physical a physical device with the same specs.

- **Running**
  - Select the preferred device in Android Studio on the left side under the "Device manager" tab.
  - Click the green "play" button in the top bar or press "Shift + F10"
  - The app then builds and launches itself on the device.

---

## Your Backlog

Your backlog lives in two places, working together:

### 1. GitHub Issues (the content)

For every user story, feature, and task, create a GitHub Issue with a clear title and description. Use the issue template provided in this repository (see `.github/ISSUE_TEMPLATE/`).

Write them in this order:
1. All **user stories** first (format: *"As [a user], I want [action] so that [goal]"*)
2. Then all **features**
3. Then all **tasks**

### 2. Project Board (the backlog itself)

Add every Issue to a Project board. The board is your backlog, not the issue text.

Your repository does not come with a board already set up — see **[PROJECT_BOARD.md](PROJECT_BOARD.md)** for step-by-step instructions on creating your board and setting up the required fields (Priority, Estimated Effort, Sprint). 

You'll need a working board by the end of Sprint 1, as Status Report 3 requires it.


---

## Development

From Week 38 onward:
- Set up your development environment and start development.
- You're free to organise your code however works best for you — for example, using `src/` and `docs/` folders, or any other structure. There's no required folder layout; 
- At the end of each sprint, your work will be demonstrated to the customer at that sprint's Demo
- Keep this README as the one place that always explains how to install and run your project.


Your repository must remain **Private** for the entire course. This protects your customer's requirements and your own work.

---

## Documenting Your Project

Once Sprint 2 begins, add a **User Manual** section to this README, covering:
- **Installing** — what's needed to set up your project locally (dependencies, environment, etc.)
- **Running** — how to start/execute the application

