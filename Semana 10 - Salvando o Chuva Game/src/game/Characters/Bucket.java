package game.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;

public class Bucket extends GameObject {
  private int pontos = 0;
  private int vidas = 3;

  public Bucket(String textureName) {
    super(textureName);
    this.rectangle.set((800 / 2 - 64 / 2), 20, 64, 64);

    if (Boolean.parseBoolean(gameInformation.getProps("game.isValid"))) {
      String[] playerPosition = gameInformation.getProps("player.position").split(";");
      this.rectangle.set(Float.parseFloat(playerPosition[0]), Float.parseFloat(playerPosition[1]), 64, 64);
      this.vidas = Integer.parseInt(gameInformation.getProps("player.life"));
      this.pontos = Integer.parseInt(gameInformation.getProps("player.score"));
    }
  }

  public void setVidas(int vidas) {
    this.vidas = vidas;
  }

  public int getVidas() {
    return this.vidas;
  }

  public void ganharVidas() {
    this.vidas++;
  }

  public void perderVidas() {
    this.vidas--;
  }

  public void setPontos(int pontos) {
    this.pontos = pontos;
  }

  public void ganharPontos() {
    this.pontos++;
  }

  public void perderPontos() {
    this.pontos--;
  }

  public int getPontos() {
    return this.pontos;
  }

  @Override
  public void moveObject() {
    // Move the bucket based on mouse input
    if (Gdx.input.isTouched()) {
      Vector3 touchPosition = new Vector3();
      touchPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
      rectangle.x = touchPosition.x - 64 / 2;
    }

    // Move the bucket based on keyboard input
    if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
      rectangle.x -= 400 * Gdx.graphics.getDeltaTime();
    }

    if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
      rectangle.x += 400 * Gdx.graphics.getDeltaTime();
    }

    // Make the bucket pass across the screen
    if (rectangle.x < 0 - 64) {
      rectangle.x = 800 + 64;
    }
    if (rectangle.x > 800 + 64) {
      rectangle.x = 0 - 64;
    }
  }

  @Override
  public void dispose() {
    super.dispose();

    // Salva as novas posições caso o jogador continue jogando
    gameInformation.saveProps("player.life", String.valueOf(getVidas()));
    gameInformation.saveProps("player.score", String.valueOf(getPontos()));
    gameInformation.saveProps("player.position", toString());
  }
}
