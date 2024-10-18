package Source;

import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    ListadorDeArquivos listadorDeArquivos = new ListadorDeArquivos();
    ArrayList<String> fileList = listadorDeArquivos.listarArquivosNoDiretorio("Dados", "srt");
    
    // Questão A
    System.out.println("Questão A");
    ThreadContarPalavras contarPalavras = new ThreadContarPalavras("Dados/" + fileList.get(0), "Bart");
    contarPalavras.start();

    // Questão B
    System.out.println("Questão B");
    for (String file : fileList) {
      ThreadContarPalavras threadContarPalavras = new ThreadContarPalavras("Dados/" + file, "Bart");
      threadContarPalavras.start();
    }
  }
}
