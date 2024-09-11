public class Lua extends Astro {
  private String planeta;

  public Lua(String nome, String planeta) {
    super(nome);
    this.planeta = planeta;
  }

  @Override
  public String toString() {
    return super.toString() + "Lua [planeta=" + planeta + "]";
  }
}
