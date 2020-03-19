package interfaces;

public interface IClientService {
    public boolean conect();
    public void close();
    public void request(String type, String data);
    public void startReading();
}
