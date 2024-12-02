import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Client {
  public static void main(String[] args) {
    try {
      Socket cliente = new Socket("localhost", 12345);

      while (true) {
        ObjectOutputStream entrada = new ObjectOutputStream(cliente.getOutputStream());
        Scanner input = new Scanner(System.in);
        
        entrada.write
        JOptionPane.showMessageDialog(null, "Data recebida do servidor:" + data_atual.toString());
        entrada.close();
      }

      // System.out.println("Conexão encerrada");
    } catch (Exception e) {
      System.out.println("Erro: " + e.getMessage());
    }

  }

}
