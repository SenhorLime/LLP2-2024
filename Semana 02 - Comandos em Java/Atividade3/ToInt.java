public class ToInt {
  public static void main(String[] args) {
    char[] charactersArray = {'A', 'B', 'C', 'a', 'b', 'c', '0', '1', '2', '$', '*', '+', '/', ' '};

    for (char character : charactersArray) {
      System.out.printf( "O caractere %c tem o valor %d\n", character, ( (int) character ) );
    }
  }
}