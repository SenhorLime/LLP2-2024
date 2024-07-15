package game.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class Dropable extends GameObject {
  public Dropable(String textureName) {
    super(textureName);
  }

  public Boolean isColliding(Rectangle playerRectangle) {
    if (this.rectangle.overlaps(playerRectangle)) {
      return true;
    } else {
      return false;
    }
  }
  
  @Override
  public void moveObject() {
    this.rectangle.y -= 200 * Gdx.graphics.getDeltaTime();
  }
}

