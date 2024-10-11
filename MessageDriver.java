import java.util.Scanner;
public class MessageDriver {
    public static void main(String[] args) {
        System.out.println("                 ");
        System.out.println("                 ");
        System.out.println("Welcome To This Messaging App.");
        System.out.println("                 ");
        MessagingApp messagingApp = new MessagingApp();
        Scanner scanner = new Scanner(System.in);
        System.out.println("                 ");
        while (true) {
            messagingApp.showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println("                 ");// Consume newline
            switch (choice) {
                case 1:
                    System.out.println("Enter receiver name:");
                    String receiver = scanner.nextLine();
                    System.out.println("Enter message content:");
                    String content = scanner.nextLine();
                    messagingApp.sendMessage(receiver, content);
                    System.out.println("                 ");
                    break;
                case 2:
                    System.out.println("Enter receiver name to display messages:");
                    String displayReceiver = scanner.nextLine();
                    messagingApp.displayMessages(displayReceiver);
                    System.out.println("                 ");
                    break;
                case 3:
                    System.out.println("Find messages for (enter contact name or type 'all' for all contacts):");
                    String findReceiver = scanner.nextLine();
                    System.out.println("Do you want to see messages that are seen or unread? (type 'seen' or 'unread'):");
                    String statusChoice = scanner.nextLine();
                    boolean showSeenMessages = statusChoice.equalsIgnoreCase("seen");
                    messagingApp.findMessages(findReceiver, showSeenMessages);
                    System.out.println("                 ");
                    break;
                case 4:
                    System.out.println("Enter receiver name to delete messages:");
                    String deleteReceiver = scanner.nextLine();
                    messagingApp.deleteMessages(deleteReceiver);
                    System.out.println("                 ");
                    break;
                case 5:
                    System.out.println("Enter new contact name:");
                    String newContact = scanner.nextLine();
                    messagingApp.addContact(newContact);
                    System.out.println("Contact added successfully!");
                    System.out.println("                 ");
                    break;
                case 6:
                    messagingApp.exit();
                default:
                    System.out.println("Invalid choice. Please try again.");
                    System.out.println("                 ");
                    break;
            }
        }
    }
}
