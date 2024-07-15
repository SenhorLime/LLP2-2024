package Source.Contas;

public class Conta {
  private int id;
  private float saldo;
  private String nome;

  public Conta(String id, String nome, String saldo) {
    this.id = Integer.parseInt(id);
    this.nome = nome;
    this.saldo = Float.parseFloat(saldo);
  }

  public int getID() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public float verificaSaldo() {
    return saldo;
  }

  public boolean retira(float valor) {
    if (valor <= saldo) {
      saldo -= valor;
      return true;
    } else {
      return false;
    }
  }

  public void deposita(float valor) {
    saldo += valor;
  }

  @Override
  public String toString() {
    return this.id + "," + this.nome + "," + this.saldo + "," + ",";
  }
}
