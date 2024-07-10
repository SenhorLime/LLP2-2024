package game.Utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class InformationSaver {
  private static Properties gameProperties = getProps();

  private static Properties getProps() {
    Properties props = new Properties();
    try {
      FileInputStream file = new FileInputStream("assets/gameData.properties");
      props.load(file);
    } catch (IOException e) {
      e.printStackTrace();
      System.err.println("Falha ao abrir o arquivo: " + e.getMessage());
    }
    return props;
  }

  private static void setProps() {
    try {
      gameProperties.store(new FileOutputStream("assets/gameData.properties"), "");
    } catch (IOException e) {
      e.printStackTrace();
      System.err.println("Falha ao salvar informações: " + e.getMessage());
    }
  }

  public static String getProps(String key) {
    return gameProperties.getProperty(key);
  }

  public static void saveProps(String key, String value) {
    gameProperties.setProperty(key, value);
    setProps();
  }
}