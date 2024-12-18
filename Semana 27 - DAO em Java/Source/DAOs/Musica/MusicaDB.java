package Source.DAOs.Musica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Source.Database.MySQLAccess;

public class MusicaDB implements MusicaDAO {
  private Connection connection;

  public MusicaDB() {
    try {
      connection = MySQLAccess.getConection();
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
  }

  @Override
  public List<Musica> getAllMusicas() {
    List<Musica> musicas = new ArrayList<Musica>();

    try {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT * FROM vw_MusicaArtista");

      while (resultSet.next()) {
        Musica musica = new Musica();

        musica.setCodigo(resultSet.getInt("codMus"));
        musica.setTitulo(resultSet.getString("titulo"));
        musica.setArtista(resultSet.getString("artista"));

        musicas.add(musica);
      }
    } catch (SQLException exception) {
      exception.printStackTrace();
    }

    return musicas;
  }

  @Override
  public Musica getMusica(int codigo) {
    Musica musica = new Musica();

    try {
      PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM vw_MusicaArtista WHERE id=?");
      preparedStatement.setInt(1, codigo);

      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        musica.setCodigo(resultSet.getInt("codArt"));
        musica.setTitulo(resultSet.getString("titulo"));
        musica.setArtista(resultSet.getString("artista"));
      }
    } catch (SQLException exception) {
      exception.printStackTrace();
    }

    return musica;
  }
}
