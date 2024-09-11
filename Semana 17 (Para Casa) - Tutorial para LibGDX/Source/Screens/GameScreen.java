package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import Stages.GameStage;

public class GameScreen implements Screen {
  private GameStage stage;

  public GameScreen() {
    stage = new GameStage();
  }

  @Override
  public void dispose() {

  }

  @Override
  public void hide() {

  }

  @Override
  public void pause() {

  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    stage.draw();
    stage.act(delta);
  }

  @Override
  public void resize(int arg0, int arg1) {

  }

  @Override
  public void resume() {

  }

  @Override
  public void show() {

  }

}
