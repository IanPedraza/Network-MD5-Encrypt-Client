package view;

import network.ClienteEco;

public class Main {

    public static void main(String[] args) {
        ClienteEco clienteEco = new ClienteEco();
        clienteEco.run(new String[]{"localhost", "9999"});        
    }
    
}
