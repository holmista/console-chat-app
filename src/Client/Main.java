package Client;

public class Main {
    public static void main(String[] args) {
        Client client = new Client("localhost", 12345);
        client.launch();
    }
}
