package game.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Dropable extends GameObject {
  public Dropable(String textureName) {
    super(textureName);
    this.rectangle.set(MathUtils.random(0, 800 - 64), 500, 64, 64);
  }

  public Dropable(String textureName, float x, float y) {
    super(textureName);
    this.rectangle.set(x, y, 64, 64);
  }
  
  public Boolean isColliding(Rectangle playerRectangle) {
    if (this.rectangle.overlaps(playerRectangle)) {
      return true;
    } else {
      return false;
    }
  }

  public boolean isOutOfScreen() {
    if (this.rectangle.y + 64 < 0) {
      return true;
    }

    return false;
  }
  
  @Override
  public void moveObject() {
    this.rectangle.y -= 200 * Gdx.graphics.getDeltaTime();
  }
}

