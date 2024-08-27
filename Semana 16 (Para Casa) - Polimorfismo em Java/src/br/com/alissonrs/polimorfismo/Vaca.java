package br.com.alissonrs.polimorfismo;
public class Vaca extends Mamifero {
	@Override
	public void emitirSom(){
		System.out.println("MUUUUUUUUUU!!");
	}

	@Override
	public void mamar() {
		System.out.println("Buuuu!!! Chuck, chuck");
	}
	
}
