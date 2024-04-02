import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class SubstituiPalavra {
  private static void changeWordInFile(String pathToFile, String oldWord, String newWord) {
    try (BufferedReader reader = new BufferedReader(new FileReader(pathToFile))) {
      BufferedWriter writer = new BufferedWriter(new FileWriter("FaroesteTrocado.txt"));

      String fileLine;
      while ((fileLine = reader.readLine()) != null) {
        writer.write(fileLine.replace(oldWord, newWord));
        writer.newLine();
      }

      writer.close();
    } catch (Exception e) {
      System.err.println("Could not open file: " + e.getMessage());
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Digite a palavra que deseja trocar no arquivo: ");
    String oldWord = scanner.nextLine();

    System.out.print("Digite a nova palavra que sera trocada no arquivo: ");
    String newWord = scanner.nextLine();

    changeWordInFile(args[0], oldWord, newWord);

    scanner.close();
  }
}
