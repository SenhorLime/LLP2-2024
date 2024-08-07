package Source.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLAccess {
  // Variaveis para conexao com o banco de dados
  private static final String URL = "jdbc:mysql://localhost/discdb";
  private static final String TIMEZONE = "?useTimezone=true&serverTimezone=UTC";
  private static final String USER = "root";
  private static final String PASSWORD = "";

  public static Connection getConection() throws SQLException {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");

      return DriverManager.getConnection(URL+TIMEZONE, USER, PASSWORD);
    } catch (ClassNotFoundException | SQLException exception) {
      throw new SQLException("Erro ao conectar ao banco de dados", exception);
    }
  }

  public static void closeConnection(Connection connection) {
    if (connection != null) {
      try {
        connection.close();
      } catch (SQLException exception) {
        System.err.println("Erro ao fechar conexao " + exception);
      }
    }
  }
}
