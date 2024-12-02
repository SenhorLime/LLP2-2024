package Source;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ChatServer extends UnicastRemoteObject implements IChat {
  public ChatServer() throws RemoteException {
  }

  @Override
  public String getMessage(String message) throws RemoteException {
    String returnMessage = "Client: " + message;

    System.out.println(returnMessage);
    return returnMessage;
  }

  @Override
  public String sendMessage(String message) throws RemoteException {
    System.out.println("Chegou do cliente: " + message);
    System.out.println("Digite algo para manda po4 cliente: ");
    String sendMessage = new Scanner(System.in).nextLine();

    return sendMessage;
  }

  public static void main(String[] args) {
    ChatServer chatServer;

    try {
      chatServer = new ChatServer();
      LocateRegistry.createRegistry(1099);
      Naming.bind("rmi:///ChatServer", chatServer);
      System.out.println("Server on port: 1099");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
