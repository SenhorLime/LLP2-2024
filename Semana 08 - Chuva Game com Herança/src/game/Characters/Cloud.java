package game.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Cloud {
  public Texture image;
  public Rectangle rect;
  private Vector2 velocity = new Vector2(200, 200);

  public Cloud(String path) {
    this.image = new Texture(Gdx.files.internal("assets/cloud.png"));
    this.rect = new Rectangle((800 / 2 - 64 / 2), 390, 64, 64);
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
