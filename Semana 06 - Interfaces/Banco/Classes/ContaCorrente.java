package Banco.Classes;

import Banco.Types.Tributavel;

public class ContaCorrente extends Conta implements Tributavel, Banco.Types.Conta{
    @Override
    public void atualiza(double taxaSelic) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deposita(double valor) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void retira(double valor) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public double getSaldo() {
        // TODO Auto-generated method stub
        return 0;
    }
    
    @Override
    public double calculaTributos() {
        return this.saldo * 0.01;
    }
}
