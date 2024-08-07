package Source.DAOs.Musica;

public class Musica {
  private int codigo;
  private String titulo;
  private String artista;

  public Musica() {

  }

  public String getArtista() {
    return this.artista;
  }

  public String getTitulo() {
    return this.titulo;
  }

  public int getCodigo() {
    return this.codigo;
  }

  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public void setArtista(String artista) {
    this.artista = artista;
  }

  @Override
  public String toString() {
    return codigo + "," + titulo + "," + artista;
  }
}
