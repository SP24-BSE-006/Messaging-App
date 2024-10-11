import java.util.Scanner;

public class MessagingApp {
    private String[] contacts;
    private Message[][] messageArray; // 2D array for messages
    private int contactCount = 0;
    private final String sender = "MainSender";
    private static final int MAX_CONTACTS = 50; // Size of contacts array
    private static final int MAX_MESSAGES_PER_CONTACT = 50;

    public MessagingApp() {
        contacts = new String[MAX_CONTACTS]; // Initialize contacts array
        messageArray = new Message[MAX_CONTACTS][MAX_MESSAGES_PER_CONTACT]; // Initialize messages array

        // Pre-adding some contacts
        addContact("Laiba");
        addContact("Zunaira");
        addContact("Minahil");
        addContact("Ayesha");
        addContact("Aminah");
    }

    // Method to display the menu options for the user
    public void showMenu() {
        System.out.println("Select an option:");
        System.out.println("1. Send Message");
        System.out.println("2. Display Messages");
        System.out.println("3. Find Messages");
        System.out.println("4. Delete Messages");
        System.out.println("5. Add Contact");
        System.out.println("6. Exit");
    }

    // Method to add a new contact
    public void addContact(String newContact) {
        if (contactCount < contacts.length) {
            contacts[contactCount] = newContact;
            contactCount++;
            System.out.println("Contact added: " + newContact);
        } else {
            System.out.println("Contact list is full, cannot add more contacts.");
        }
    }

    // Method to send a message to a specific contact
    public void sendMessage(String receiver, String content) {
        // Check if the receiver exists
        int contactIndex = findContactIndex(receiver);
        if (contactIndex == -1) {
            System.out.println("Receiver not found. Please add the contact first.");
            return;
        }

        // Create and add the message to the messages array
        for (int i = 0; i < MAX_MESSAGES_PER_CONTACT; i++) {
            if (messageArray[contactIndex][i] == null) {
                Message newMessage = new Message(sender, receiver, content, false);
                messageArray[contactIndex][i] = newMessage; // Store message object
                System.out.println("Message sent successfully to " + receiver);
                askIfMessageRead(receiver, newMessage);
                break;
            }
        }
    }

    // Method to ask if the message has been read and provide feedback
    private void askIfMessageRead(String receiver, Message message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Has " + receiver + " read the message: \"" + message.getContent() + "\"? (yes/no):");
        String response = scanner.nextLine();
        message.setStatus(response.equalsIgnoreCase("yes"));
        System.out.println("Message status updated to '" + (message.isStatus() ? "Seen" : "Unread") + "'.");
    }

    // Method to display all messages for a specific receiver
    public void displayMessages(String receiver) {
        int contactIndex = findContactIndex(receiver);
        if (contactIndex == -1) {
            System.out.println("Contact not found.");
            return;
        }
        System.out.println("Messages for " + receiver + ":");
        for (Message msg : messageArray[contactIndex]) {
            if (msg != null) {
                System.out.println("- " + msg); // Use toString method of Message class
            }
        }
    }
    // Method to find messages based on read/unread status
    public void findMessages(String receiver, boolean showSeenMessages) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What type of messages do you want to find?");
        System.out.println("1. Seen messages");
        System.out.println("2. Unread messages");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        int contactIndex = findContactIndex(receiver);
        if (contactIndex == -1) {
            System.out.println("Contact not found.");
            return;
        }

        // Displaying messages based on user's choice
        System.out.println("Messages for " + receiver + ":");
        for (int i = 0; i < MAX_MESSAGES_PER_CONTACT; i++) {
            Message msg = messageArray[contactIndex][i];
            if (msg != null) {
                // Check if the message matches the user's choice
                if ((choice == 1 && msg.isStatus())) {
                    System.out.println("Seen: " + msg); // Use toString method of Message class
                } else if ((choice == 2 && !msg.isStatus())) {
                    System.out.println("Unread: " + msg); // Use toString method of Message class
                }
            }
        }
    }

    // Method to find a contact index by name
    private int findContactIndex(String contactName) {
        for (int i = 0; i < contactCount; i++) {
            if (contacts[i].equals(contactName)) {
                return i;
            }
        }
        return -1; // Contact not found
    }

    // Method to delete all messages for a specific contact
    public void deleteMessages(String receiver) {
        int contactIndex = findContactIndex(receiver);
        if (contactIndex == -1) {
            System.out.println("Contact not found.");
            return;
        }

        // Clear all messages for the contact
        for (int i = 0; i < MAX_MESSAGES_PER_CONTACT; i++) {
            messageArray[contactIndex][i] = null;
        }
        System.out.println("All messages for " + receiver + " have been deleted.");
    }

    // Method to exit the program
    public void exit() {
        System.out.println("Exiting the messaging app. Goodbye!");
        System.exit(0);
    }
}
