
public abstract class Astro {
  public String nome;

  public Astro(String nome) {
    this.nome = nome;
  }

  @Override
  public String toString() {
    return "Astro [nome=" + nome + "]";
  }
}
