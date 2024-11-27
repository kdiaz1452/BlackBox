package blackbox;

import java.net.*;
import java.io.*;
import java.util.*;

public class Server {
    protected static HashSet<String> currentUsers = new HashSet<>(); // Stores online users
    protected static Set<String> allUsers = new HashSet<>();  // Stores all users from passwd.txt
    final protected static HashMap<String, PrintWriter> clientWriters = new HashMap<>(); // Maps usernames to their output streams
    protected static HashMap<String, NewClient> userThreads = new HashMap<>();

    public static void main(String[] args) {
        int port;
        ServerSocket serverSocket;

        // Set default port if not provided
        try {
            port = Integer.parseInt(args[0]);
        } catch (Exception e) {
            System.out.println("port = 9001 (default)");
            port = 9001;
        }

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on port: " + port);

            // Load all users from passwd.txt into the allUsers set
            loadAllUsers();

            // Continuously accept new client connections
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New connection accepted " + socket.getInetAddress() + ":" + socket.getPort());

                // Create a new thread for each connected client
                new NewClient(socket).start();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    // Sends the user list with their online/offline status to all connected clients
    static void broadcastUserList() {
        StringBuilder userList = new StringBuilder("USER_LIST_UPDATE");

        // Build the user list string with status for each user
        synchronized (allUsers) {
            for (String user : allUsers) {
                userList.append(",").append(user);
                if (currentUsers.contains(user)) {
                    userList.append(": Online");
                } else {
                    userList.append(": Offline");
                }
            }
        }

        // Send the constructed user list to all clients
        broadcastMessage(userList.toString());
    }

    // Method to read all users from passwd.txt file and populate allUsers set
    private static void loadAllUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:/Users/kdiaz/OneDrive/Documents/School/ESU/Fall 2024/CPSC 445/passwd.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");  // Assume comma-separated values with username first
                if (parts.length > 0) {
                    allUsers.add(parts[0].trim());  // Add the username to allUsers
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Sends a message to all currently connected clients
    private static void broadcastMessage(String message) {
        synchronized (clientWriters) {
            for (PrintWriter writer : clientWriters.values()) {
                writer.println(message);
            }
        }
    }

    private static void sendMessageToClients(String sender, String recipient, String message) {
        // Get the recipient's thread
        NewClient recipientThread = userThreads.get(recipient);
        System.out.println(sender + " " + recipient + " " + message);
    
        // If the recipient thread exists, send the message to that user
        if (recipientThread != null) {
            System.out.println(recipient + "'s thread is not null. Calling sendMessageToRecipient");
            recipientThread.sendMessageToRecipient(sender, recipient, message);
        }
    }
    

    // Inner class to handle each new client connection
    static class NewClient extends Thread {
        private final Socket socket;
        private String username;
        private BufferedReader input;
        private PrintWriter output;

        NewClient(Socket socket) {
            this.socket = socket;
            this.username = null;
        }

        @Override
        public void run() {
            try {
                try (socket) {
                    input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    output = new PrintWriter(socket.getOutputStream(), true);

                    // Attempt to authenticate user
                    if (authenticateUser(input, output)) {
                        synchronized (currentUsers) {
                            if (currentUsers.contains(username)) {
                                output.println("User already logged in.");
                                return;
                            } else {
                                currentUsers.add(username);
                                clientWriters.put(username, output); // Map username to this client's output stream
                                synchronized (userThreads) {
                                    userThreads.put(username, this);  // Add the current thread to the map using the username as the key
                                }
                                // Update all clients with the new user list
                                broadcastUserList();
                            }
                        }

                        output.println("Welcome to BlackBox.");

                        // Read messages from client
                        String message = "";
                        
                        while ((message = input.readLine()) != null) {
                            System.out.println("Message: " + message);
                            if (message.equals("Disconnecting")) break;
                            String messageRecipient = message.split(" ")[0];
                            String[] messageArray = message.split(" ");
                            String messageContents = message.substring(messageArray[0].length() + 1);
                            sendMessageToClients(username, messageRecipient, messageContents); // Route message to the correct recipient
                        }

                        synchronized (currentUsers) {
                            currentUsers.remove(username);
                            clientWriters.remove(username);
                            userThreads.remove(username); // Remove user thread on logout
                            broadcastUserList(); // Update user list
                        }
                    } else {
                        output.println("Too many failed attempts.\n\nOnly for BlackBox Users.");
                    }
                }
                System.out.println("Connection closed by client");
            } catch (IOException e) {
                System.out.println(e);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println("Error closing socket");
                }
            }
        }

        // Authenticate the user by checking username and password in passwd.txt
        private boolean authenticateUser(BufferedReader input, PrintWriter output) throws IOException {
            int failedLoginAttempts = 0;
            final int MAX_FAILED_LOGIN_ATTEMPTS = 3;
            boolean usernameFound = false;
        
            while (failedLoginAttempts < MAX_FAILED_LOGIN_ATTEMPTS) {
                //output.println("Enter username:");
                String enteredUsername = input.readLine();
                System.out.println("Entered Username " + enteredUsername);
        
                if (enteredUsername == null || enteredUsername.length() > 8) {
                    output.println("Username must be 1 to 8 characters long");
                    failedLoginAttempts++;
                    continue;
                }
        
                try {
                    File userAccounts = new File("C:/Users/kdiaz/OneDrive/Documents/School/ESU/Fall 2024/CPSC 445/passwd.txt");
                    System.out.println("File found");
                    try (Scanner textFile = new Scanner(userAccounts)) {
                        while (textFile.hasNextLine()) {
                            String line = textFile.nextLine();
                            String[] userData = line.split(",");
                            String registeredUsername = userData[0];
                            System.out.println("Registered Username: " + registeredUsername);
        
                            if (enteredUsername.equals(registeredUsername)) {
                                usernameFound = true;
                                this.username = enteredUsername;
                                output.println("Usernames Match");
                                System.out.println("Usernames Match");
        
                                while (failedLoginAttempts < MAX_FAILED_LOGIN_ATTEMPTS) {
                                    //output.println("Password:");
                                    String password = input.readLine();
                                    System.out.println("Entered Password: " + password);
                                    String registeredPassword = userData[1];
                                    System.out.println("Registered Password: " + registeredPassword);
        
                                    if (password.equals(registeredPassword)){
                                        System.out.println("Login successful");
                                        output.println("Login successful");
                                        return true;
                                    } else {
                                        output.println("Invalid password");
                                        System.out.println("Invalid password");
                                        failedLoginAttempts++;
                                        if (failedLoginAttempts == MAX_FAILED_LOGIN_ATTEMPTS) return false;
                                    }
                                }
                            }
                        }
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("File Not Found");
                    return false;
                }
        
                if (!usernameFound) {
                    output.println("Invalid username");
                    failedLoginAttempts++;
                }
            }
            return false;
        }

        private void sendMessageToRecipient(String sender, String recipient, String message) {
            synchronized (output) {
                System.out.println(sender + " " + recipient + " " + message);
                output.println(sender + " " + recipient + " " + message);  // Send the message to the recipient
            }
        }
    }
}
