package gpjecc.blogspot.com;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopStarter {

	public static void main(String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.resizable = false;
		config.width = 640;
		config.height = 480;
		config.useGL20 = false;
		config.title = "Atividade LLP2";

		new LwjglApplication(new SimpleGame(), config);
		System.setProperty("org.lwjgl.opengl.Display.allowSoftwareOpenGL", "true");

	}
}