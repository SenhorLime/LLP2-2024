package game.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Bucket extends Movable {

  public Bucket(String path, Rectangle rect) {
    super(path, rect);
  }

  public void mover() {
    // Move the bucket based on mouse input
    if (Gdx.input.isTouched()) {
      Vector3 touchPosition = new Vector3();
      touchPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
      rect.x = touchPosition.x - 64 / 2;
    }

    // Move the bucket based on keyboard input
    if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
      rect.x -= 400 * Gdx.graphics.getDeltaTime();
    }

    if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
      rect.x += 400 * Gdx.graphics.getDeltaTime();
    }

    // Make the bucket pass across the screen
    if (rect.x < 0 - 64) {
      rect.x = 800 + 64;
    }
    if (rect.x > 800 + 64) {
      rect.x = 0 - 64;
    }
  }
}
