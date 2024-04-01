import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Utils.FileManager;

public class Main {
    public static void main(String[] args) {
        boolean confirmarInserindo = true;
        Scanner scanner = new Scanner(System.in);
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();

        while (confirmarInserindo) {
            System.out.println("Selecione o dado que deseja inserir: ");
            System.out.println(" 1. Funcionario \n 2. Discente \n 3.Administrativo \n 4.Docente");
            System.out.print("Qual opção quer inserir: ");
            int escolha = scanner.nextInt();

            String nome, email, senha, cargo;
            scanner.nextLine();
            System.out.print("Digite o nome: ");
            nome = scanner.nextLine();
            System.out.print("Digite o email: ");
            email = scanner.nextLine();
            System.out.print("Digite a senha: ");
            senha = scanner.nextLine();
            System.out.print("Digite o cargo: ");
            cargo = scanner.nextLine();

            switch (escolha) {
                case 1:
                    Funcionario funcionario = new Funcionario(nome, email, senha, cargo);
                    funcionarios.add(funcionario);
                    break;
                case 2:
                    System.out.print("Digite o cargo: ");
                    String turma = scanner.nextLine();

                    Discente discente = new Discente(nome, email, senha, cargo, turma);
                    funcionarios.add(discente);
                    break;
                case 3:
                    System.out.print("Digite o cargo: ");
                    int setor = scanner.nextInt();

                    Administrativo administrativo = new Administrativo(nome, email, senha, cargo, setor);
                    funcionarios.add(administrativo);
                    break;
                case 4:
                    Docente docente = new Docente(nome, email, senha, cargo);
                    funcionarios.add(docente);
                    break;
                default:
                    break;
            }

            System.out.println("Deseja confirmar inserindo dados? (1 - Sim | 2 - Não) ");
            int confirmar = scanner.nextInt();
            if (confirmar == 1) {
                confirmarInserindo = true;
            } else if (confirmar == 2) {
                confirmarInserindo = false;
            }

            System.out.println("Deseja inserir dados do arquivo? (1 - Sim | 2 - Não) ");
            confirmar = scanner.nextInt();
            if (confirmar == 1) {
                List<String> dadosArquivo = FileManager.loadFile("pessoal.csv");

                for (String dado : dadosArquivo) {
                    String[] dadoSeparado = dado.split(";", -1);
                    
                    if (dadoSeparado[4].equals("Geral")) {
                        funcionarios.add(new Funcionario(dadoSeparado));
                    }

                }

            } else if (confirmar == 2) {
                confirmarInserindo = false;
            }
        }
    }
}
