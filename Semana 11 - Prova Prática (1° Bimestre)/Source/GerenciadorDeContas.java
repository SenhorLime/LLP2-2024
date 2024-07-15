package Source;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import Source.Contas.Conta;
import Source.Contas.ContaEspecial;
import Source.Contas.ContaPoupanca;

public class GerenciadorDeContas {
  public ArrayList<Conta> listaDeContas = new ArrayList<Conta>();

  private ArrayList<String> carregaDados() {
    ArrayList<String> linhasDoArquivo = new ArrayList<String>();
    String linha;

    try (BufferedReader reader = new BufferedReader(new FileReader("./Source/data/contas.csv"))) {
      while((linha = reader.readLine()) != null) {
        linhasDoArquivo.add(linha);
      }
    } catch (Exception e) {
      System.err.println("Erro ao abrir o arquivo: " + e.getMessage());
    }

    return linhasDoArquivo;
  }


  public void colocaNoArray() {
    ArrayList<String> linhasDeContas = carregaDados();

    for (String dadosDaConta : linhasDeContas) {
      String[] dadosSeparados = dadosDaConta.split(",", -1);

      if (dadosSeparados[3].isEmpty() && (dadosSeparados[4].isEmpty())) {
        listaDeContas.add(new Conta(dadosSeparados[0], dadosSeparados[1], dadosSeparados[2]));
      }

      if (!dadosSeparados[3].isEmpty() && dadosSeparados[4].isEmpty()) {
        listaDeContas.add(new ContaEspecial(dadosSeparados[0], dadosSeparados[1], dadosSeparados[2], dadosSeparados[3]));
      }

      if (dadosSeparados[3].isEmpty() && !dadosSeparados[4].isEmpty()) {
        listaDeContas.add(new ContaPoupanca(dadosSeparados[0], dadosSeparados[1], dadosSeparados[2], dadosSeparados[4]));
      }
    }
  }

}
