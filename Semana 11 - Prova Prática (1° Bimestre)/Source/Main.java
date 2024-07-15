package Source;

import java.util.ArrayList;

import Source.Contas.Conta;
import Source.Contas.ContaEspecial;
import Source.Contas.ContaPoupanca;

public class Main {
  public static void contadorDoTipoDeConta(ArrayList<Conta> listaDeContas) {
    int qtContaComum = 0;
    int qtContaEspecial = 0;
    int qtContaPoupanca = 0;

    for (Conta conta : listaDeContas) {
      if (conta instanceof ContaEspecial) {
        qtContaEspecial++;
      }

      if (conta instanceof ContaPoupanca) {
        qtContaPoupanca++;
      }
    }
    qtContaComum = listaDeContas.size() - (qtContaEspecial + qtContaPoupanca);

    System.out.println("Contas Comuns:" + qtContaComum);
    System.out.println("Contas Especiais:" + qtContaEspecial);
    System.out.println("Contas Poupanca:" + qtContaPoupanca);
  }

  public static void saldoMenorQueLimite(ArrayList<Conta> listaDeContas) {
    for (Conta conta : listaDeContas) {
      if (conta instanceof ContaEspecial) {
        ContaEspecial contaEspecial = (ContaEspecial) conta;

        if (contaEspecial.verificaSaldo() < contaEspecial.getLimite()) {
          System.out.println(contaEspecial);
        }
      } else {
        if (conta.verificaSaldo() < 0) {
          System.out.println(conta);
        }
      }
    }
  }

  public static void contasComRendimento(ArrayList<Conta> listaDeContas) {
    for (Conta conta : listaDeContas) {
      if (conta instanceof ContaPoupanca) {
        ContaPoupanca contaPoupanca = (ContaPoupanca) conta;

        System.out.println(conta + " - Rendimento: " + contaPoupanca.calculaRendimento());
      }
    }
  }

  public static void main(String[] args) {
    GerenciadorDeContas gerenciadorDeContas = new GerenciadorDeContas();
    gerenciadorDeContas.colocaNoArray();

    System.out.println("Quantidade de Contas Por Tipo: ");
    contadorDoTipoDeConta(gerenciadorDeContas.listaDeContas);

    System.out.println("\nContas com saldo menor o limite: ");
    saldoMenorQueLimite(gerenciadorDeContas.listaDeContas);

    System.out.println("\nContas com seus respectivos rendimentos: ");
    contasComRendimento(gerenciadorDeContas.listaDeContas);
  }
}