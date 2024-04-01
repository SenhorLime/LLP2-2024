package Banco.Classes;

import Banco.Types.Tributavel;

public class SeguraDeVida implements Tributavel {
    @Override
    public double calculaTributos() {
        return 42;
    }
}
