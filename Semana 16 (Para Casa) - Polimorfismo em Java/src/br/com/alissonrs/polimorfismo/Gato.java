package br.com.alissonrs.polimorfismo;

public class Gato extends Mamifero {
    @Override
    public void mamar() {
        System.out.println("Miau. Chuc chuc");
    }

    @Override
    public void emitirSom() {
        System.out.println("Miau Miau");
    }
}
