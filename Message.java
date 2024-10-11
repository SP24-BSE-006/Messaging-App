import java.time.LocalDateTime;

public class Message {
    private static int idCounter = 1;
    private String sender;
    private String receiver;
    private String content;
    private String messageId;
    private boolean status; // true if seen, false if unread
    private LocalDateTime timestamp;

    public Message(String sender, String receiver, String content, boolean status) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.status = status;
        this.timestamp = LocalDateTime.now();
        this.messageId = generateSimpleId();
    }

    private String generateSimpleId() {
        return String.valueOf(idCounter++); // Increment the counter for each new message
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getContent() {
        return content;
    }

    public String getMessageId() {
        return messageId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("Message ID: %s, Sender: %s, Receiver: %s, Content: %s, Status: %s, Timestamp: %s",
                messageId, sender, receiver, content, (status ? "Seen" : "Unread"), timestamp);
    }
}
