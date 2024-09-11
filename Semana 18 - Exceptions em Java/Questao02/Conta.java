package Questao02;

public class Conta {
  private int limite;

  public int getLimite() {
    return limite;
  }

  public void setLimite(int limite) {
    this.limite = limite;
  }

  public void deposita(int valorDeposito) {
    this.limite += valorDeposito;
  }

  public void saca(int valorSaque) throws ContaException {
    if (this.limite < valorSaque) {
      throw new ContaException("Saldo insuficiente na conta para realizar o saque.");
    }

    this.limite -= valorSaque;
  }
}
