import java.io.IOException;
import java.util.Arrays;
public class ReadThreadClient implements Runnable {
    private NetworkInformation networkInformation;
    public ReadThreadClient(NetworkInformation networkInformation) {
        this.networkInformation = networkInformation;
    }
    @Override
    public void run() {
        while (true) {
            try {
                Message message = (Message) networkInformation.getNetworkUtil().read();
                if (message == null) {
                    continue;
                }
                if (message.getFrom().equalsIgnoreCase("server")) {
                    String[] inboxMessages = message.getText().split("~");
                    System.out.println("Your Inbox:");
                    Arrays.stream(inboxMessages).forEach(System.out::println);
                } else {
                    System.out.println("From: " + message.getFrom() + " Message: " + message.getText());
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
                break;
            }
        }
    }
}