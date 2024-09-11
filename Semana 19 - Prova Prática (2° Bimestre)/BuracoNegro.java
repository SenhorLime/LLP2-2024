public class BuracoNegro extends Astro {
  public int massasDoSol;

  public BuracoNegro(String nome, int massasDoSol) {
    super(nome);
    this.massasDoSol = massasDoSol;
  }
  
  @Override
  public String toString() {
    return super.toString() + "BuracoNegro [massasDoSol=" + massasDoSol + "]";
  }
}
