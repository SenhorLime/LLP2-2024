package Banco.Classes;

public class Conta {
    public int numero;
    public double saldo;
    public double limite;
    public String nome;

    public boolean saca(double valor) {
        return false;
    }

    public void deposita(double valor) {
        this.saldo += valor;
    }

    public void transfere(Conta destino, double valor) {
        this.saldo -= valor;
        destino.deposita(valor);
    }

    public double getSaldo() {
        return saldo;
    }
}