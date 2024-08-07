package Source.DAOs.Artista;

import java.util.List;

public interface ArtistaDAO {
  public List<Artista> getAllArtistas();

  public Artista getArtista(int codigo);

  // public void updateArtista(Artista artista);

  // public void deleteArtista(Artista artista);
}
