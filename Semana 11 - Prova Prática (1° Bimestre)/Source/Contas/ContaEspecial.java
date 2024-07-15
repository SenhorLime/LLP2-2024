package Source.Contas;

public class ContaEspecial extends Conta {
  private float limite;

  public ContaEspecial(String id, String nome, String saldo, String limite) {
    super(id, nome, saldo);
    this.limite = Float.parseFloat(limite);
  }

  public float getLimite() {
    return limite;
  }

  @Override
  public boolean retira(float valor) {
    if (valor < verificaSaldo()) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public float verificaSaldo() {
    return super.verificaSaldo() + limite;
  }

  @Override
  public String toString() {
    return getID() + "," + getNome() + "," + verificaSaldo() + "," + limite + ",";
  }
}