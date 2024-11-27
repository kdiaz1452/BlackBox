package blackbox;

import java.net.*;
import java.io.*;
import java.util.Arrays;

public class Client {
    private Socket socket;
    private PrintWriter output;
    private BufferedReader serverInput;
    private String username; // Global username for the client
    private Conversations conversations;

    // Constructor to connect to the server
    public Client(String server, int port) {
        try {
            socket = new Socket(server, port);
            System.out.println("Connected to server: " + socket.getInetAddress() + ":" + socket.getPort());
            output = new PrintWriter(socket.getOutputStream(), true);
            serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Login method to authenticate with the server
    public boolean login(String username, String password) {
        output.println(username); // Send username
        String serverMessage = readServerResponse(); // Get server response

        // Debugging output
        System.out.println("Server response to username: " + serverMessage);

        // Check for username errors
        if (serverMessage.equalsIgnoreCase("Username must be 1 to 8 characters long") ||
            serverMessage.equalsIgnoreCase("Invalid username")) {
            return false;
        }

        // Send password after receiving the prompt
        output.println(password);
        serverMessage = readServerResponse(); // Get server response
        System.out.println("Server response to password: " + serverMessage);

        // Check for password errors
        if (serverMessage.equalsIgnoreCase("Invalid password") ||
            serverMessage.equalsIgnoreCase("Too many failed attempts")) {
            return false;
        } else if (serverMessage.equalsIgnoreCase("Login successful")) {
            this.username = username; // Save username globally upon successful login
            return true;
        }

        return false; // Default for other errors
    }

    // Method to send messages in the required format
    public void sendMessage(String intendedRecipient, String message) {
        // Format message as [intendedRecipient message]
        String formattedMessage = intendedRecipient + " " + message;
        System.out.println("formatted Message = " + formattedMessage);
        output.println(formattedMessage);

        // Display sent message in the active conversation, if applicable
        if (conversations != null && conversations.activeMessagePanel != null) {
            conversations.activeMessagePanel.displaySentMessage(message);
        }
    }

    // Listen for messages and server updates continuously
    public void listenForMessages() {
        String message;
        try {
            while ((message = serverInput.readLine()) != null) {
                if (message.startsWith("USER_LIST_UPDATE")) {
                    String[] activeUsers = message.substring(16).split(",");
                    System.out.println("\nActive users received: " + Arrays.toString(activeUsers) + "\n");
                    if (conversations != null) {
                        conversations.updateConversationsPage(activeUsers);
                    }
                } else {
                    // Extract sender, recipient, and message contents
                    String[] parts = message.split(" ", 3); // Split into sender, recipient, and message
                    if (parts.length < 2) {
                        System.err.println("Invalid message format: " + message);
                        continue;
                    }

                    String sender = parts[0];
                    String recipient = parts[1];
                    String messageContents = parts.length == 3 ? parts[2] : "";

                    if (messageContents.equals("OPEN_CHAT") && recipient.equals(this.username)) {
                        // Trigger opening a chat window with the sender
                        System.out.println("Open chat request received from " + sender);

                        if (conversations != null) {
                            conversations.openMessagingWindow(sender, this.username);
                        }
                    } if (recipient.equals(this.username)) {
                        conversations.openMessagingWindow(sender, this.username); // Dynamically create or activate panel
                        MessagePanel messagePanel = conversations.activeChats.get(sender);
                        messagePanel.displayReceivedMessage(messageContents); // Display the incoming message
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Disconnect from the server and close the socket
    public void disconnect() {
        try {
            output.println("Disconnecting");
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper method to read server responses
    private String readServerResponse() {
        try {
            return serverInput.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    // Getter for username
    public String getUsername() {
        return username;
    }

    // Setter for conversations
    public void setConversations(Conversations conversations) {
        this.conversations = conversations;
    }
    
    public void sendOpenChatRequest(String recipient) {
        String specialMessage = recipient + " OPEN_CHAT";
        output.println(specialMessage);
    }

}
