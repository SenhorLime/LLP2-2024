package Source;

import java.rmi.Naming;
import java.util.Scanner;

public class ChatClient {
  public ChatClient() {
  }

  public static void main(String[] args) {
    IChat chatInterface;
    String sentMessage, sendMessage;
    try {
      chatInterface = (IChat) Naming.lookup("rmi:///ChatServer");

      do {
        sentMessage = new Scanner(System.in).nextLine();
        sendMessage = chatInterface.sendMessage(sentMessage);
        System.out.println("Chegou do servid: " + sendMessage);

      } while (!sentMessage.equalsIgnoreCase("Exit"));

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
