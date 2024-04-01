package CalcArea;

import CalcArea.Classes.*;
import CalcArea.Types.AreaCalculavel;

public class Main {
    public static void main(String[] args) {
        AreaCalculavel a = new Retangulo(3, 2);
        System.out.println(a.calculaArea());
    }
}
