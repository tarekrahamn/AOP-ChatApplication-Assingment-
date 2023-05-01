import util.NetworkUtil;

import java.util.Scanner;
public class Client {
    public Client(String serverAddress, int serverPort) {
        try {
            System.out.print("Enter name of the client: ");
            Scanner scanner = new Scanner(System.in);
            String clientName = scanner.nextLine();
            NetworkUtil networkUtil = new NetworkUtil(serverAddress, serverPort);
            networkUtil.write(clientName);
            NetworkInformation networkInformation = new NetworkInformation(networkUtil);
            Thread readThread = new Thread(new ReadThreadClient(networkInformation));
            Thread writeThread = new Thread(new WriteThreadClient(networkInformation,
                    clientName));
            readThread.start();
            writeThread.start();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void main(String args[]) {
        String serverAddress = "127.0.0.1";
        int serverPort = 33333;
        Client client = new Client(serverAddress, serverPort);
    }
}