package Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

import Utils.Constants;

public class Background extends Actor {
  private final TextureRegion textureRegion;
  private Rectangle textureRegionBounds1;
  private Rectangle textureRegionBounds2;
  private int speed = 100;

  public Background() {
    textureRegion = new TextureRegion(new Texture(Gdx.files.internal(Constants.BACKGROUND_IMAGE_PATH)));
    textureRegionBounds1 = new Rectangle(0 - Constants.APP_WIDTH / 2, 0, Constants.APP_WIDTH, Constants.APP_HEIGHT);
    textureRegionBounds2 = new Rectangle(Constants.APP_WIDTH / 2, 0, Constants.APP_WIDTH, Constants.APP_HEIGHT);
  }

  @Override
  public void act(float delta) {
    if (leftBoundsReached(delta)) {
      resetBounds();
    } else {
      updateXBounds(-delta);
    }
  }

  @Override
  public void draw(SpriteBatch batch, float parentAlpha) {
    super.draw(batch, parentAlpha);
    batch.draw(textureRegion, textureRegionBounds1.x, textureRegionBounds1.x, Constants.APP_WIDTH,
        Constants.APP_HEIGHT);
    batch.draw(textureRegion, textureRegionBounds2.x, textureRegionBounds2.x, Constants.APP_WIDTH,
        Constants.APP_HEIGHT);

  }

  private boolean leftBoundsReached(float delta) {
    return (textureRegionBounds2.x - (delta * speed)) <= 0;
  }

  private void updateXBounds(float delta) {
    textureRegionBounds1.x += delta * speed;
    textureRegionBounds2.x += delta * speed;
  }

  private void resetBounds() {
    textureRegionBounds1 = textureRegionBounds2;
    textureRegionBounds2 = new Rectangle(Constants.APP_WIDTH, 0, Constants.APP_WIDTH, Constants.APP_HEIGHT);
  }
}
