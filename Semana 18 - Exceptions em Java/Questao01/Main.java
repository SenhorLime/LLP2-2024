package Questao01;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Digite o primeiro numero: ");
    int num1 = scanner.nextInt();

    System.out.print("Digite o segundo numero: ");
    int num2 = scanner.nextInt();

    System.out.println("\n---- Escolha qual operação você deseja realizar ----\n");
    System.out.println("1. Soma \n2. Subtração \n3. Multiplicação \n4. Divisão");
    System.out.print("\nDigite qual operação deseja: ");
    int escolha = scanner.nextInt();
    switch (escolha) {
      case 1:
        try {
          System.out.println("O resultado da operação é: " + CalculadoraDePares.somar(num1, num2));
        } catch (NaoParException exception) {
          System.out.println(exception.getMessage());
        }
        break;
      case 2:
        try {
          System.out.print("O resultado da operação é: " + CalculadoraDePares.subtrair(num1, num2));
        } catch (NaoParException exception) {
          System.out.println(exception.getMessage());
        }
        break;
      case 3:
        try {
          System.out.print("O resultado da operação é: " + CalculadoraDePares.multiplicar(num1, num2));
        } catch (NaoParException exception) {
          System.out.println(exception.getMessage());
        }
        break;
      case 4:
        try {
          System.out.print("O resultado da operação é: " + CalculadoraDePares.dividir(num1, num2));
        } catch (NaoParException exception) {
          System.out.println(exception.getMessage());
        }
        break;
      default:
        System.out.println("Nenhuma opção valida foi digitada");
        break;
    }
  }
}