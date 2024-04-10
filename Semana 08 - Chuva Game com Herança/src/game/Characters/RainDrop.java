package game.Characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class RainDrop {
  public Texture image;
  public Rectangle rect;

  public RainDrop(Texture dropImage) {
    this.image = dropImage;
    this.rect = new Rectangle(MathUtils.random(0, 800 - 64), 480, 64, 64);
  }
}
