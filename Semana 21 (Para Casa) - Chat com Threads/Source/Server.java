import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
  private ServerSocket server;
  private Socket client;

  class ReceiveThread extends Thread {
    private Socket client;

    public ReceiveThread(Socket client) {
      this.client = client;
    }

    @Override
    public void run() {
      try (ObjectInputStream input = new ObjectInputStream(client.getInputStream())) {
        while (!client.isClosed()) {
          String message = (String) input.readObject();
          System.out.println("Cliente: " + message);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

  }

  public Server(int port) throws IOException {
    server = new ServerSocket(port);
    System.out.println("Servidor rodando na porta: " + port);
  }

  public void start() throws IOException {
    client = server.accept();
    System.out.println("Cliente conectador: " + client.getInetAddress().getHostAddress());

    new ReceiveThread(client).start();
  }

  public void sendMessage(String message) {
    try (ObjectOutputStream output = new ObjectOutputStream(client.getOutputStream())) {
      output.writeObject(message);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    try {
      Server server = new Server(12345);
      server.start();

      while (true) {
        System.out.print("Mensagem para o cliente: ");
        String message = new Scanner(System.in).nextLine();
        server.sendMessage(message);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
