package Source.DAOs.Artista;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Source.Database.MySQLAccess;

public class ArtistaDB implements ArtistaDAO {
  private Connection connection;

  public ArtistaDB() {
    try {
      connection = MySQLAccess.getConection();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<Artista> getAllArtistas() {
    List<Artista> artistas = new ArrayList<Artista>();

    try {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT * FROM artista");

      while (resultSet.next()) {
        Artista artista = new Artista();

        artista.setCodigo(resultSet.getInt("codArt"));
        artista.setNome(resultSet.getString("nome"));

        artistas.add(artista);
      }
    } catch (SQLException exception) {
      exception.printStackTrace();
    }

    return artistas;
  }

  @Override
  public Artista getArtista(int codigo) {
    Artista artista = new Artista();

    try {
      PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM artista WHERE id=?");
      preparedStatement.setInt(1, codigo);

      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        artista.setCodigo(resultSet.getInt("codArt"));
        artista.setNome(resultSet.getString("nome"));
      }
    } catch (SQLException exception) {
      exception.printStackTrace();
    }

    return artista;
  }
}
