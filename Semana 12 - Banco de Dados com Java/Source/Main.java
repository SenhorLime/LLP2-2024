package Source;

import java.util.List;

import Source.DAOs.Musica.Musica;
import Source.DAOs.Musica.MusicaDAO;
import Source.DAOs.Musica.MusicaDB;
import Source.Writer.FileManager;

public class Main {
  public static void main(String[] args) {
    MusicaDAO musicaDAO = new MusicaDB();
    List<Musica> musicas = musicaDAO.getAllMusicas();
    
    FileManager.writeLineInFile("musica.csv", "codMus, titulo, artista", false);
    for (Musica musica : musicas) {
      FileManager.writeLineInFile("musica.csv", musica.toString(), true);
    }
  }
}
