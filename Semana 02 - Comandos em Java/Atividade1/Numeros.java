import java.util.Scanner;

class Numeros {
    public static void main(String[] args) {
        int userInput;
        Scanner input = new Scanner(System.in);

        System.out.println("Digite algum numero");
        userInput = input.nextInt();
        input.nextLine();

        String numerosDiv = String.valueOf(userInput);

        for (char numero : numerosDiv.toCharArray()) {
            System.out.print(numero + " ");
        }
    }
}