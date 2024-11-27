# BlackBox Messaging Application

BlackBox is a secure, multithreaded messaging application designed for real-time, one-on-one communication. It includes authentication, profile management, and message relaying features. This guide explains how to set up, configure, and run BlackBox on a local machine for testing and demonstration purposes.

---

## Features
- **User Authentication**: Login system using hardcoded credentials stored in `passwd.txt`.
- **Real-Time Updates**: Logged-in user list updates dynamically.
- **One-on-One Messaging**: Send and receive messages in a chat-like interface.
- **Threaded Server**: Supports multiple clients simultaneously with seamless message relaying.

---

## Prerequisites
- **Java Development Kit (JDK)**: Version 8 or above.
- **NetBeans IDE** (Optional): For editing and running the GUI components.
- **Router Configuration**: Ensure port `9001` is open for communication.

---

## File Structure
- **Server.java**: Server-side logic handling authentication, message relay, and user management.
- **BlackBox.java**: Entry point for the client application.
- **Login.java**: GUI for user authentication.
- **Conversations.java**: Displays a real-time list of logged-in users.
- **Message.java**: Handles the chat interface for one-on-one communication.
- **passwd.txt**: Contains hardcoded user credentials.

---

## Setting Up the System

### 1. Preparing the Files
- Clone or download the project files into a directory on your machine.
- Ensure the following files are in place:
  - `Server.java`
  - `BlackBox.java`
  - `passwd.txt`

### 2. Configuring `passwd.txt`
- Open `passwd.txt` to review or modify user credentials. Each line currently follows the format:
  - `username,password,fullname,universityMajor,universityAdvisor`

### 3. Change absolute file paths for `passwd.txt`
- Currently, the system is set up to read absolute file paths. To ensure all instances of `passwd.txt` work properly, Edit the file paths used to reference `passwd.txt` in Server.java

---

## Running the System Locally
### Step 1: Start the Server
- Open a terminal or IDE and navigate to the directory containing Server.java.
- Compile the server program:
  - `javac Server.java`
- Start the server:
  - `java Server`
- The server will log messages indicating its status (e.g., "Server is running on port 9001").

### Step 2: Run the Client
- Open a new terminal or IDE instance and navigate to the inner directory BlackBox.
- Compile the client program:
  - `javac blackbox/*.java`
- Start a client instance:
  - `java blackbox.BlackBox`
- Repeat the above steps to start multiple instances of BlackBox.java for testing.

---


## Testing the Application
### Login: 
- Enter a valid username and password from passwd.txt. On successful login, you will be directed to the Conversations GUI.
- View Logged-In Users: The Conversations GUI will display all currently logged-in users. This list updates dynamically as users join or leave.
- Start a Conversation:
- Click on a username from the list.
- A new MessagePanel will open, allowing you to send and receive messages with the selected user.

---

## Future Development

### Features:
- **Profile Display**: View public user profiles by clicking on usernames.
- **Encrypted Messaging**: Utilize AES encryption with key encryption using RSA Encryption.
  - To implement dual AES-RSA encryption, the lines of `passwd.txt` must follow the format:
    - `username,password,fullname,publicKey,biography`
  - The encryption will take place on the client side, route through Server.java, and be decrypted by the recipient prior to being displayed.
