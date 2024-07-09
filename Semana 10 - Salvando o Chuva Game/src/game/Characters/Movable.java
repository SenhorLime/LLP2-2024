package game.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Movable {
  public Texture image;
  public Rectangle rect;

  public Movable(String path, Rectangle rect) {
    this.image = new Texture(Gdx.files.internal(path));
    this.rect = rect;
  }

  public Movable(Texture texture) {
    this.image = texture;
    this.rect = new Rectangle(MathUtils.random(0, 800 - 64), 500, 64, 64);
  }
}
