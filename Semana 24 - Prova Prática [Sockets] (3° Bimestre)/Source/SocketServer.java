package Source;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import Source.Util.Message;

public class SocketServer {
  public SocketServer() {
    try {
      ServerSocket server = new ServerSocket(12345);
      System.out.println("Servidor rodando na porta: " + server.getLocalPort());

      Socket connection = server.accept();

      System.out.println("Cliente conectado na porta: " + connection.getInetAddress().getHostAddress());

      ObjectInputStream input = new ObjectInputStream(connection.getInputStream());
      ObjectOutputStream output = new ObjectOutputStream(connection.getOutputStream());

      Message message = (Message) input.readObject();

      if (message.getOperation().equals("EXIT")) {
        output.writeObject("Fechando servidor");
        server.close();
      }

      int resultado = makeOperation(message);

      System.out.println("Client: " + message.getOperation());
      output.writeObject(resultado);

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public int makeOperation(Message message) {
    switch (message.getOperation()) {
      case "SOMA":
        return message.getX() + message.getY();
      case "SUBTRACAO":
        return message.getX() - message.getY();
      case "MULTIPLICACAO":
        return message.getX() * message.getY();
      case "DIVISAO":
        return (int) message.getX() / message.getY(); // Casting para evitar que a função retorne um float
      default:
        break;
    }

    return 0;
  }

  public static void main(String[] args) {
    new SocketServer();
  }
}
