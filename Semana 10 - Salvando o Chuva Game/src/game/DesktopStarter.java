package game;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class DesktopStarter{
	public static void main (String[] args) {
        new LwjglApplication(new SimpleGame2(), "Tutorial06", 800, 600, false);
				System.setProperty("org.lwjgl.opengl.Display.allowSoftwareOpenGL", "true");
	}
}