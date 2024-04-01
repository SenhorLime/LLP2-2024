package CalcArea.Classes;

import CalcArea.Types.AreaCalculavel;

public class Circulo implements AreaCalculavel {
    private int raio;
    private float pi = 3.14f;

    public Circulo (int raio) {
        this.raio = raio;
    }

    @Override
    public double calculaArea() {
        return pi*(Math.pow(raio, 2));
    }
}
