package Banco;

import Banco.Classes.ContaCorrente;
import Banco.Types.Tributavel;

public class Main {
    public static void main(String[] args) {
        ContaCorrente cc = new ContaCorrente();
        cc.deposita(100);
        Tributavel t = cc;
        System.out.println(t.calculaTributos());
    }
}
