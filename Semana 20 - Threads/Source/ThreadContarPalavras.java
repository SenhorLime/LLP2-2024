package Source;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class ThreadContarPalavras extends Thread {

    public int total = 0;
    String caminho = ".";
    String palavra = "";

    // Construtor
    public ThreadContarPalavras(String caminho, String palavra) {
        this.caminho = caminho;
        this.palavra = palavra;
    }

    @Override
    public synchronized void start() {
        System.out.println("Início de verificação de [" + caminho + "]");
        super.start();
    }

    // Execução da thread
    @Override
    public void run() { // Função executada após chamada de start.
        total = contarOcorrencias();
        // Finalizou execução e mostra resultado dessa contagem.
        System.out.println("Contagem de [" + palavra + "] em [" + caminho + "] = " + total);
    }

    public int contarOcorrencias() {
        int count = 0;

        // Abre o arquivo.
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {

            // Verifica linha por linha
            String line;
            while ((line = br.readLine()) != null) {

                // Quebre linha em palavras.
                // \w - caracteres de palavras e ^\w - não caracters de palavras.
                String[] words = line.split("[^\\w]+"); //

                for (String word : words) {

                    // Verifica se a palavra é igual à palavra-alvo, ignorando maiúsculas e
                    // minúsculas
                    if (word.equalsIgnoreCase(palavra)) {
                        count++;
                    }

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return count;
    }

}