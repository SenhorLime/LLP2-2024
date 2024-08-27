package br.com.alissonrs.polimorfismo;

public abstract class Mamifero extends Animal {
    public abstract void mamar();

    public void correr() {
        System.out.println("correndo");
    }
}
