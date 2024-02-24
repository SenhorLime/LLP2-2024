import java.util.Scanner;

public class CalculadoraIMC {
  private static float calculaIMC(float peso, float altura) {
    return (peso / (altura * altura));
  }

  private static String mostreNivel(float imc) {
    if (imc < 18.5) {
      return "MAGREZA";
    } else if (imc < 24.9) {
      return "NORMAL";
    } else if (imc < 29.9) {
      return "SOBREPESO";
    } else {
      return "OBESIDADE";
    }
  }

  public static void main(String[] args) {
    float peso, altura;
    Scanner entrada = new Scanner(System.in);

    System.out.println("Digite seu peso: ");
    peso = entrada.nextFloat();
    entrada.nextLine();

    System.out.println("Digite sua altura: ");
    altura = entrada.nextFloat();
    entrada.nextLine();

    float imc = calculaIMC(peso, altura);
    System.out.printf("Seu IMC é: %.2f - %s", imc, mostreNivel(imc));

  }
}