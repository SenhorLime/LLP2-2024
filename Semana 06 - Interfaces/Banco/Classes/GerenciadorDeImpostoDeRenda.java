package Banco.Classes;

import Banco.Types.Tributavel;

public class GerenciadorDeImpostoDeRenda {
    private double total;

    public void adiciona(Tributavel t) {
        System.out.println("Adicionando tributavel: " + t);
        this.total = this.total + t.calculaTributos();
    }

    public double getTotal() {
        return total;
    }
}
