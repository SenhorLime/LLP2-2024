package Source.DAOs.Musica;

import java.util.List;

public interface MusicaDAO {
  public List<Musica> getAllMusicas();

  public Musica getMusica(int codigo);
}
