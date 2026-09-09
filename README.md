# PA1414 Software Engineering Project HT26

## Project Description

**Project Name:** *Twitchy*

**Customer Name:** *Fredrik Gullbrandson*

**Student Full Name:** *Jacob Thörn*

### Project Aim and Objectives

*Twitchy is a small wearable device that with the help of a mobile application is designed to detect and track falls/impacts of the user. The main features are:* 
* Immediate alerts when a fall/impact is detected.
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
- *To reduce false positives of a fall event a barometric pressure sensor is used to track distance*
- *The device should allow the user to create a personal profile*
- *The device handles continuous low-latency telemetry streaming over Bluetooth Low Energy (BLE)*
- *The system alerts the app in real time when an event such as a fall or hard impact happens*

**Non-Functional Requirements**
- *Alert should reach the app within 5 seconds of detection*
- *User profile data stored on the phone must be encrypted*
- *Pairing between the device and mobile application must be seamless and secure*
- *The wearable device should be comfortable enough to be worn for long periods of time*

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

