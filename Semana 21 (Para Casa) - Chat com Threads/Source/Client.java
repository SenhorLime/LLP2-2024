import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Socket socket;
    private String host;
    private int port;

    public Client(String host, int port) throws IOException {
        this.host = host;
        this.port = port;
        socket = new Socket(host, port);
    }

    private class EnviarThread extends Thread {
        @Override
        public void run() {
            try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    String mensagem = scanner.nextLine();
                    out.writeObject(mensagem);
                }
            } catch (IOException e) {
                System.err.println("Erro ao enviar mensagem: " + e.getMessage());
            }
        }
    }

    private class ReceberThread extends Thread {
        @Override
        public void run() {
            try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
                while (true) {
                    String mensagem = (String) in.readObject();
                    System.out.println("Servidor: " + mensagem);
                }
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Erro ao receber mensagem: " + e.getMessage());
            }
        }
    }

    public void iniciar() {
        try {
            new EnviarThread().start();
            new ReceberThread().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            Client client = new Client("localhost", 12345); // Substitua por host e porta do servidor
            client.iniciar();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}