package game.Characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import game.Managers.ResourceManager;
import game.Utils.InformationSaver;

public class GameObject {
  protected ResourceManager resourcesLoader = ResourceManager.getInstance();
  protected InformationSaver gameInformation = InformationSaver.getInstance();
  protected Texture texture;
  protected Rectangle rectangle = new Rectangle();

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
    screen.draw(this.texture, this.rectangle.x, this.rectangle.y);
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
    return this.rectangle.x + ";" + this.rectangle.y;
  }
}
