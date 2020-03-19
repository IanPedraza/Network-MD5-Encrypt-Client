package view;

import network.ClientService;
import static model.REQUEST_TYPE.*;

public class Main {

    public static void main(String[] args) {
        ClientService client = new ClientService("localhost", 9999);
        client.request(REQUEST_TYPE_USER,"ianpedraza");
    }
    
}
