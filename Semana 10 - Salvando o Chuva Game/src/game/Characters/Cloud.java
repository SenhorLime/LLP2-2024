package game.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Cloud extends GameObject {
  private Vector2 velocity = new Vector2(200, 200);

  public Cloud(String textureName, Rectangle rect) {
    super(textureName, rect);
  }

  @Override
  public void moveObject() {
    if (rectangle.x < 0) {
      velocity.x = +200;
    } else if (rectangle.x > 800 - 96) {
      velocity.x = -200;
    }

    rectangle.x += velocity.x * Gdx.graphics.getDeltaTime();
  }
}
