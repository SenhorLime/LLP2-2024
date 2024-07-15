package game.Characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import game.Managers.ResourceManager;

public class GameObject {
  protected ResourceManager resourcesLoader = ResourceManager.getInstance();
  protected Texture texture;
  protected Rectangle rectangle;

  public GameObject(String textureName) {
    this.texture = resourcesLoader.getTexture(textureName);
  }

  public GameObject(String textureName, Rectangle rectangle) {
    this.rectangle = rectangle;
    this.texture = resourcesLoader.getTexture(textureName);
  }

  public void moveObject() {
  }

  public Rectangle getRect() {
    return this.rectangle;
  }

  public void renderObject(SpriteBatch screen) {
    screen.draw(texture, rectangle.x, rectangle.y);
  }

  public void setPosition(float x, float y) {
    this.rectangle.x = x;
    this.rectangle.y = y;
  }

  public void dispose() {
    this.texture.dispose();
  }
  
  @Override
  public String toString() {
    return rectangle.x + ";" + rectangle.y;
  }
}
