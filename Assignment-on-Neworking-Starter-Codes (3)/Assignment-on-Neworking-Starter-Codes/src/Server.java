import util.NetworkUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
public class Server {
    private ServerSocket serverSocket;
    private HashMap<String, NetworkInformation> clientNetworkInformationMap;
    Server() {
        try {
            serverSocket = new ServerSocket(33333);
            clientNetworkInformationMap = new HashMap<>();
            System.out.println("Server has started...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Server has accepted a connection...");
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
    }
    public void serve(Socket clientSocket) throws IOException, ClassNotFoundException {
        NetworkUtil networkUtil = new NetworkUtil(clientSocket);
        String clientName = (String) networkUtil.read();
        NetworkInformation networkInformation = new NetworkInformation(networkUtil);
        clientNetworkInformationMap.put(clientName, networkInformation);
        Thread readThreadServer = new Thread(new
                ReadThreadServer(clientNetworkInformationMap, networkInformation));
        readThreadServer.start();
    }
    public static void main(String args[]) {
        Server server = new Server();
    }
}

