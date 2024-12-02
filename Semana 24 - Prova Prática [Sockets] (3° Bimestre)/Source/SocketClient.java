package Source;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import Source.Util.Message;

public class SocketClient {
  public static void main(String[] args) {
    try {
      // Cria um socket para se conectar ao servidor
      Socket socket = new Socket("localhost", 12345); // Substitua "localhost" pelo IP do servidor, se necessário
      System.out.println("Conectado ao servidor!");

      // Cria streams para enviar e receber objetos
      ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
      ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

      // Cria um scanner para ler a entrada do usuário
      Scanner scanner = new Scanner(System.in);

      System.out.print("Digite o primeiro numero: ");
      int x = scanner.nextInt();
      scanner.nextLine();

      System.out.print("Digite o segundo numero: ");
      int y = scanner.nextInt();
      scanner.nextLine();

      System.out.print("Digite a operacao (SOMA, SUBTRACAO, PRODUTO, DIVISAO, EXIT): ");
      String operation = scanner.nextLine();

      Message message = new Message(x, y, operation);
      output.writeObject(message);

      if (message.getOperation().equals("EXIT")) {
        String resposta = (String) input.readObject();
        System.out.println("Servidor: " + resposta);
      }

      int resposta = (int) input.readObject();
      System.out.println("Servidor: " + resposta);

      // Loop para enviar e receber mensagens
      // while (true) {
      // // Lê a mensagem do usuário
      // String mensagem = scanner.nextLine();

      // // Cria um objeto Message e envia para o servidor
      // output.writeObject(mensagem);

      // // Verifica se o usuário digitou "!exit"
      // if (mensagem.equals("!exit")) {
      // break;
      // }

      // // Recebe a resposta do servidor
      // String resposta = (String) input.readObject();
      // System.out.println("Servidor: " + resposta);
      // }

      // Fecha os streams e o socket
      output.close();
      input.close();
      socket.close();
      scanner.close();

    } catch (Exception e) {
      System.out.println("Erro: " + e.getMessage());
    }
  }
}