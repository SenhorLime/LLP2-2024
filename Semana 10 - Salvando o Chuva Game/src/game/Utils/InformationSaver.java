package game.Utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class InformationSaver {
  private static InformationSaver INSTANCE;
  private Properties gameProperties = new Properties();

  private InformationSaver() {
    // Abrindo o arquivo no modo de leitura e armazendo o buffer
    try {
      FileInputStream fileInputStream = new FileInputStream("assets/data/gameData.properties");
      gameProperties.load(fileInputStream);
    } catch (IOException e) {
      e.printStackTrace();
      System.err.println("Falha ao abrir o arquivo no modo de leitura: " + e.getMessage());
    }
  }

  public static InformationSaver getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new InformationSaver();
    }

    return INSTANCE;
  }
  
  public void saveProps(String key, String value) {
    // Salvando as properties dentro da variavel
    gameProperties.setProperty(key, value);

    // Escrevendo os valores recebidos pela função dentro do arquivo de properties
    try {
      FileOutputStream fileOutputStream = new FileOutputStream("assets/data/gameData.properties");
      gameProperties.store(fileOutputStream, "");
    } catch (IOException e) {
      e.printStackTrace();
      System.err.println("Falha ao salvar informações: " + e.getMessage());
    }
  }

  public String getProps(String key) {
    return gameProperties.getProperty(key);
  }
}