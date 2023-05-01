import java.io.IOException;
import java.util.Scanner;
public class WriteThreadClient implements Runnable {
    private NetworkInformation networkInformation;
    private String clientName;
    public WriteThreadClient(NetworkInformation networkInformation, String clientName)
    {
        this.networkInformation = networkInformation;
        this.clientName = clientName;
    }
    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("To , text!!): ");
                String input = scanner.nextLine();
                if (input.contains(",")) {
                    String[] parts = input.split(",", 2);
                    String receiverName = parts[0].trim();
                    String messageBody = parts[1].trim();
                    Message message = new Message();
                    message.setFrom(clientName);
                    message.setTo(receiverName);
                    message.setText(messageBody);
                    networkInformation.getNetworkUtil().write(message);
                } else {
                    System.out.println("Error: Invalid input format.");
                }
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
                break;
            }
        }
    }
}