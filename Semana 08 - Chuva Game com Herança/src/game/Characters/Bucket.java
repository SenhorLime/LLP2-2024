package game.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Bucket {
  public Texture image;
  public Rectangle rect;

  public Bucket(String path) {
    this.image = new Texture(Gdx.files.internal("assets/bucket.png"));
    this.rect = new Rectangle((800 / 2 - 64 / 2), 20, 64, 64);
  } 
}
