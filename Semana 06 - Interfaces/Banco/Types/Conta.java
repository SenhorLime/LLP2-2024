package Banco.Types;

public interface Conta {
    double getSaldo();
    void deposita(double valor);
    void retira(double valor);
    void atualiza(double taxaSelic);
}
