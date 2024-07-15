package game.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Cloud extends GameObject {
  private Vector2 velocity = new Vector2(200, 200);

  public Cloud(String textureName) {
    super(textureName);
    this.rectangle.set((800 / 2 - 64 / 2), 350, 64, 64);

    if (Boolean.parseBoolean(gameInformation.getProps("game.isValid"))) {
      String[] cloudPosition = gameInformation.getProps("object.cloud.position").split(";");
      this.rectangle.set(Float.parseFloat(cloudPosition[0]), Float.parseFloat(cloudPosition[1]), 64, 64);
    }
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

  @Override
  public void dispose() {
    super.dispose();

    if (Boolean.parseBoolean(gameInformation.getProps("game.isValid"))) {
      gameInformation.saveProps("object.cloud.position", toString());
    }
  }
}
