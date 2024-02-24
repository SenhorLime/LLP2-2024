public class SquareCubesTables {
    public static void main(String[] args) {
        System.out.println("Numero \tQuadrado \tCubo");

        for (int i = 1; i <= 10; i++) {
            System.out.printf("%d \t%.0f \t\t%.0f \n", i, Math.pow(i, 2), Math.pow(i, 3));
        }
    }
}