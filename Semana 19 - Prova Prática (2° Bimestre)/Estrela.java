public class Estrela extends Astro {
  public String sistema;
  
  public Estrela(String nome, String sistema) {
    super(nome);
    this.sistema = sistema;
  }

  @Override
  public String toString() {
    return super.toString() + "Estrela [planeta=" + sistema + "]";
  }
}
