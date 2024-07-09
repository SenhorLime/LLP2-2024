package game.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Cloud extends Movable {
  private Vector2 velocity = new Vector2(200, 200);

  public Cloud(String path, Rectangle rect) {
    super(path, rect);
  }

  public void mover() {
    if (rect.x < 0) {
      velocity.x = +200;
    } else if (rect.x > 800 - 96) {
      velocity.x = -200;
    }

    rect.x += velocity.x * Gdx.graphics.getDeltaTime();
  }
}
