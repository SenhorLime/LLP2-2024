package game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import game.Characters.Bucket;
import game.Characters.Cloud;
import game.Characters.Dropable;
import game.Managers.ResourceManager;

public class SimpleGame2 extends ApplicationAdapter {
  // Elementos do jogo (jogador, npc e inimigo)
  private Bucket bucket;
  private Cloud cloud;
  private Array<Dropable> dropableObjects = new Array<Dropable>();

  // Variaveis de controle para criacao de novos inimigos
  private long lastDropTime;
  private long lastAnvilTime;

  // Classe responsavel por gerenciar todos os assets do jogo
  private ResourceManager resources = ResourceManager.getInstance();

  private SpriteBatch batch;
  private OrthographicCamera camera;

  private void loadResources() {
    // Loading all used textures to Resource Manager
     resources.addTexture("bucket", "assets/bucket.png");
    resources.addTexture("cloud", "assets/cloud.png");
    resources.addTexture("raindrop", "assets/droplet.png");
    resources.addTexture("anvil", "assets/anvil.png");

    // Loading all used sounds to Resounce Manager
    resources.addSound("drop", "assets/drop.wav");
    resources.addSound("anvil", "assets/anvilSound.wav");
  }

  private void loadCharacthers() {
    bucket = new Bucket("bucket", new Rectangle((800 / 2 - 64 / 2), 20, 64, 64));
    cloud = new Cloud("cloud", new Rectangle((800 / 2 - 64 / 2), 350, 64, 64));
  }

  private void drawObjects() {
    bucket.renderObject(batch);
    cloud.renderObject(batch);
  }

  @Override
  public void create() {
    loadResources();
    loadCharacthers();

    // create the camera and the SpriteBatch
    camera = new OrthographicCamera();
    camera.setToOrtho(false, 800, 480);
    batch = new SpriteBatch();
  }

  @Override
  public void render() {
    Gdx.gl.glClearColor(0, 0.3f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    // tell the camera to update its matrices
    camera.update();

    // tell the SpriteBatch to render in the
		// coordinate system specified by the camera.
		batch.setProjectionMatrix(camera.combined);

    // begin the batch and draw all elements
    batch.begin();
    drawObjects();
    batch.end();

    bucket.moveObject();
    cloud.moveObject();
  }

  @Override
  public void dispose() {
    bucket.dispose();
    cloud.dispose();

    batch.dispose();
  }
}
