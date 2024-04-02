import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

public class PesquisaArquivo {
  private static HashMap<String, Integer> countApparition(String pathToFile) {
    HashMap<String, Integer> wordLineMap = new HashMap<String, Integer>();

    try (BufferedReader reader = new BufferedReader(new FileReader(pathToFile))) {
      String fileLine;

      while ((fileLine = reader.readLine()) != null) {
        for (String wordInLine : fileLine.strip().split(" ")) {
          if (wordLineMap.get(wordInLine) == null) {
            wordLineMap.put(wordInLine, 1);
          } else {
            wordLineMap.put(wordInLine, wordLineMap.get(wordInLine) + 1);
          }
        }
      }
    } catch (Exception e) {
      System.err.println("Could not open file: " + e.getMessage());
    }

    return wordLineMap;
  }

  public static void main(String[] args) {
    HashMap<String, Integer> wordsApparitionsCount = countApparition(args[0]);

    Scanner scanner = new Scanner(System.in);
    System.out.print("Digite a palavra que deseja ver a frequencia: ");
    String word = scanner.nextLine();

    if (wordsApparitionsCount.get(word) != null) {
      System.out.println("A palavra " + word + " aparece " +
          wordsApparitionsCount.get(word) + " vez(es)");
    } else {
      System.out.println("A palavra " + word + " nao foi encontrada");
    }

    scanner.close();
  }
}
