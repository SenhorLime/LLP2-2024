package game.Utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class ScoreSaver {
  private static int highScore;

  ScoreSaver() {
    highScore = getHighScore();
  }

  public static int getHighScore() {
    String line = "";
    BufferedReader reader;
    try {
      reader = new BufferedReader(new FileReader("assets/data.csv"));
      line = reader.readLine();
      reader.close();
    } catch (Exception e) {
      System.err.println("Could not open file: " + e.getMessage());
    }

    return Integer.parseInt(line);
  }

  public static void saveHighScore(int score) {
    if (score > highScore) {
      BufferedWriter writer;

      try {
        writer = new BufferedWriter(new FileWriter("assets/data.csv"));
        writer.write(Integer.toString(score));
        writer.close();
      } catch  (Exception e) {
        System.err.println("Could not open file: " + e.getMessage());
      } 
      
    }
  }
}
