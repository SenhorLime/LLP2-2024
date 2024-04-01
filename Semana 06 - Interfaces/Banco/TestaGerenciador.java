package Banco;

import Banco.Classes.Conta;
import Banco.Classes.ContaCorrente;
import Banco.Classes.GerenciadorDeImpostoDeRenda;
import Banco.Classes.SeguraDeVida;

public class TestaGerenciador {
    public static void main(String[] args) {
        GerenciadorDeImpostoDeRenda gerenciador = new GerenciadorDeImpostoDeRenda();

        SeguraDeVida sv = new SeguraDeVida();
        gerenciador.adiciona(sv);

        ContaCorrente cc = new ContaCorrente();
        cc.deposita(1000);
        gerenciador.adiciona(cc);

        System.out.println(gerenciador.getTotal());

        Conta conta = cc;
        System.out.printf("O saldo e: %.2f", conta.getSaldo());
    }
}
