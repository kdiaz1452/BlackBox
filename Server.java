import java.net.*;
import java.io.*;
import java.util.*;

public class Server {
    protected static HashSet<String> currentUsers = new HashSet<>(); // Online users
    protected static Set<String> allUsers = new HashSet<>();         // All users from passwd.txt
    protected static HashMap<String, PrintWriter> clientWriters = new HashMap<>(); // Username-to-writer map
    protected static HashMap<String, ClientHandler> userThreads = new HashMap<>(); // Username-to-thread map

    public static void main(String[] args) {
        int port = 9001; // Default port
        try {
            if (args.length > 0) {
                port = Integer.parseInt(args[0]);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid port number. Using default port 9001.");
        }

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port: " + port);

            // Load all registered users from passwd.txt
            loadAllUsers();

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New connection accepted: " + socket.getInetAddress() + ":" + socket.getPort());
                new ClientHandler(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load all usernames from passwd.txt into allUsers
    private static void loadAllUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:/Users/kdiaz/OneDrive/Documents/School/ESU/Fall 2024/CPSC 445/passwd.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 0) {
                    allUsers.add(parts[0].trim());
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading passwd.txt: " + e.getMessage());
        }
    }

    // Broadcast updated user list with statuses to all clients
    static void broadcastUserList() {
        StringBuilder userList = new StringBuilder("USER_LIST_UPDATE");

        synchronized (allUsers) {
            for (String user : allUsers) {
                userList.append(",").append(user);
                userList.append(currentUsers.contains(user) ? ":Online" : ":Offline");
            }
        }

        broadcastMessage(userList.toString());
    }

    // Send a message to all connected clients
    private static void broadcastMessage(String message) {
        synchronized (clientWriters) {
            for (PrintWriter writer : clientWriters.values()) {
                writer.println(message);
            }
        }
    }

    // Send a private message to a specific client
    private static void sendMessageToClient(String sender, String recipient, String message) {
        ClientHandler recipientHandler = userThreads.get(recipient);
        if (recipientHandler != null) {
            recipientHandler.sendMessage(sender, recipient, message);
        } else {
            System.out.println("Recipient " + recipient + " is not online.");
        }
    }

    // ClientHandler class to manage individual client connections
    static class ClientHandler extends Thread {
        private final Socket socket;
        private String username;
        private BufferedReader input;
        private PrintWriter output;

        ClientHandler(Socket socket) {
            this.socket = socket;
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
                                System.out.println(username);
                                currentUsers.add(username);
                                clientWriters.put(username, output); // Map username to this client's output stream
                                synchronized (userThreads) {
                                    userThreads.put(username, this);  // Add the current thread to the map using the username as the key
                                }
                                // Update all clients with the new user list
                                broadcastUserList();
                            }
                        }

                        broadcastUserList();
                        output.println("Welcome to BlackBox.");
        
                        String message;
                        while ((message = input.readLine()) != null) {
        
                            String[] parts = message.split(" ", 2);
                            if (parts.length < 2) {
                                output.println("Invalid message format. Use: [recipient message]");
                                continue;
                            }
                            
                            System.out.println(message);

                            String recipient = parts[0];
                            String messageContent = parts[1];
                            if(messageContent.equals("Disconnecting")) break;
                            
                            else if(messageContent.equals("OPEN_CHAT")) {
                                handleOpenChatRequest(recipient);
                            } else {
                                sendMessageToClient(username, recipient, messageContent);
                            }
                        }

                        cleanup();

                        
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

            // Handle the OPEN_CHAT request
        private void handleOpenChatRequest(String recipient) {
        ClientHandler recipientHandler = userThreads.get(recipient);

        if (recipientHandler != null) {
            // Notify the recipient about the open chat request
            recipientHandler.sendOpenChatNotification(username);
            output.println("OPEN_CHAT_REQUEST_SENT " + recipient);
        } else {
            // Notify sender that the recipient is offline
            output.println("RECIPIENT_OFFLINE " + recipient);
        }
    }

        // Notify this client about an open chat request
        void sendOpenChatNotification(String sender) {
            output.println("OPEN_CHAT_REQUEST " + sender);
        }

        // Send a message to this client
        void sendMessage(String sender, String recipient, String message) {
            output.println(sender + " " + recipient + " " + message);
        }

        // Clean up resources and remove user from active lists
        private void cleanup() {
            synchronized (currentUsers) {
                if (username != null) {
                    currentUsers.remove(username);
                    clientWriters.remove(username);
                    userThreads.remove(username);
                    broadcastUserList();
                }
            }
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Error closing socket: " + e.getMessage());
            }
        }
    }
}