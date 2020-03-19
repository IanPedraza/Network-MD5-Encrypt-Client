package model;

import interfaces.IRequestManager;
import interfaces.OnRequestListener;
import static model.REQUEST_TYPE.*;
import static model.Encrypt.*;

public class RequestManager implements IRequestManager {

    private OnRequestListener listener;

    @Override
    public void processRequest(String request, OnRequestListener listener) {
        this.listener = listener;
        
        String decrypt = decrypt(request);
        
        String type = decrypt.substring(0, 12);
        String data = decrypt.substring(12, decrypt.length());
        
        switch (type) {
            case REQUEST_TYPE_USER:
                //processUser(content);
                break;

            case REQUEST_TYPE_MD5:
                System.out.println("received md5");
                break;

            case REQUEST_TYPE_MESSAGE:
                System.out.println("received message");
                System.out.println(data);
                break;

            case REQUEST_TYPE_RESPONSE:
                System.out.println("received response");
                break;

            case REQUEST_TYPE_ERROR:
                System.out.println("error: " + data);
                break;

            default:
                System.out.println("NO_OPERATION_AVAILABLE");
        }
    }

}
