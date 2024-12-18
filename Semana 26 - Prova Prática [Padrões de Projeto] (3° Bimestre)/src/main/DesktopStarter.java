package main;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class DesktopStarter {

	public static void main(String[] args) {
		new LwjglApplication(new SimpleGame(), "Padr�es s�o legais.", Constantes.LARGURA, Constantes.ALTURA, false);
	}
}