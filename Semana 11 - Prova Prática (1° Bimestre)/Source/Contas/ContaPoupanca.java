package Source.Contas;

public class ContaPoupanca extends Conta {
  private float rendimento;

  public ContaPoupanca(String id, String nome, String saldo, String rendimento) {
    super(id, nome, saldo);
    this.rendimento = Float.parseFloat(rendimento);
  }

  public float calculaRendimento() {
    return verificaSaldo() * rendimento / 100;
  }

  @Override
  public String toString() {
    return getID() + "," + getNome() + "," + verificaSaldo() + "," + "," + rendimento;
  }
}
