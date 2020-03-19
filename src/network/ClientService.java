package network;

import interfaces.IClientService;
import interfaces.OnRequestListener;
import java.net.*;
import java.io.*;
import java.util.concurrent.TimeUnit;
import model.RequestManager;
import static model.Encrypt.*;

public class ClientService implements IClientService {

    private Socket socket;
    private BufferedReader entrada;
    private PrintWriter salida;
    private String server;
    private int port;
    private RequestManager requestManager;

    public ClientService(String server, int port) {
        this.port = port;
        this.server = server;
        this.requestManager = new RequestManager();
        startReading();
    }

    @Override
    public boolean conect() {
        try {
            //String servidor = "localhost";
            //int puerto = 9999;

            socket = new Socket(server, port);
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            salida = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        } catch (Exception ex) {
            System.out.println("error al conectar al servidor: " + ex);
            return false;
        }

        return true;
    }

    @Override
    public void close() {
        if (!socket.isClosed()) {
            try {
                socket.close();
            } catch (IOException ex) {
                System.out.println("error al cerrar conexión: " + ex);
            }
        }
    }

    @Override
    public void startReading() {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        if (socket != null && socket.isConnected() && !socket.isClosed()) {

                            String read = entrada.readLine();
                            close();

                            requestManager.processRequest(read, new OnRequestListener() {
                                @Override
                                public void onRequest(String type, String request) {
                                    request(type, request);
                                }
                            });
                        }

                        TimeUnit.SECONDS.sleep(1);
                    } catch (IOException | InterruptedException ex) {
                        System.out.println("Error al obtener datos: " + ex);
                    }
                }
            }
        }.start();
    }

    @Override
    public void request(String type, String data) {
        String encrypt = encrypt(type + data);

        if (conect()) {
            salida.println(encrypt);
        }
    }

}
