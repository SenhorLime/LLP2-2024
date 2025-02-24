import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import Utils.Constants;

public class DesktopLauncher {
  public static void main(String[] args) {
    LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
    config.width = Constants.APP_WIDTH;
    config.height = Constants.APP_HEIGHT;
    new LwjglApplication(new MartianRun(), config);
  }
}
