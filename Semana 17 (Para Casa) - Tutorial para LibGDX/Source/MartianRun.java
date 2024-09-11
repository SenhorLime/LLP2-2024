import com.badlogic.gdx.Game;

import Screens.GameScreen;

public class MartianRun extends Game {

  @Override
  public void create() {
    setScreen(new GameScreen());
  }

}
