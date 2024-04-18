package game.Characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Anvil {
  public Texture image;
  public Rectangle rect;

  public Anvil(Texture anvilImage) {
    this.image = anvilImage;
    this.rect = new Rectangle(MathUtils.random(0, 800 - 64), 500, 64, 64);
  }
}
