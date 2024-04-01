package CalcArea.Classes;

import CalcArea.Types.AreaCalculavel;

public class Retangulo implements AreaCalculavel{
    private int largura, altura;

    public Retangulo(int largura, int altura) {
        this.largura = largura;
        this.altura = altura;
    }

    @Override
    public double calculaArea() {
        return this.largura * this.altura;
    }
}
