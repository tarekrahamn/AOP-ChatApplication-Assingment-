import util.NetworkUtil;

import java.util.ArrayList;
public class NetworkInformation {
    private NetworkUtil networkUtil;
    private ArrayList<String> inbox;
    public NetworkInformation(NetworkUtil networkUtil) {
        this.networkUtil = networkUtil;
        this.inbox = new ArrayList<>();
    }
    public NetworkUtil getNetworkUtil() {
        return networkUtil;
    }
    public ArrayList<String> getInbox() {
        return inbox;
    }
}