package Source;

import java.io.File;
import java.util.ArrayList;

public class ListadorDeArquivos {
    public ArrayList<String> listarArquivosNoDiretorio(String diretorio, String extensao) {

        ArrayList<String> srtFiles = new ArrayList<>();
        File folder = new File(diretorio);

        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();

            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(extensao)) {
                    srtFiles.add(file.getName());
                }
            }

        } else {
            System.out.println("O caminho especificado não é uma pasta válida.");
        }

        return srtFiles;
    }

}