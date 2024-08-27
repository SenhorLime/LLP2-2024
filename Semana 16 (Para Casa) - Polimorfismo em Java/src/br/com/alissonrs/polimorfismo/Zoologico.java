package br.com.alissonrs.polimorfismo;


import java.util.ArrayList;

public class Zoologico {

    public static void main(String[] args) {
        Mamifero mama;
        Cachorro cachorro;
        Vaca vaca;
        Gato gato;

        mama = new Mamifero() {
            @Override
            public void mamar() {
                System.out.println("Xuc xuc");
            }
        };
        cachorro = new Cachorro("Snoopy");
        vaca = new Vaca();
        gato = new Gato();

        mama.mamar();
        cachorro.mamar();
        vaca.mamar();
		gato.mamar();

        mama.emitirSom();
        cachorro.emitirSom();
        vaca.emitirSom();
		gato.emitirSom();

        ArrayList<Mamifero> lista = new ArrayList<Mamifero>();
        lista.add(mama);
        lista.add(cachorro);
        lista.add(vaca);
        lista.add(gato);

        System.out.println("\nMetodos usando um array polimorfico: \n");
        for (Mamifero mamifero : lista) {
            mamifero.emitirSom();
            mamifero.mamar();
        }
    }
}