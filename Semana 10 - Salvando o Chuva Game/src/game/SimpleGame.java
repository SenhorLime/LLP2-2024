package game;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;

import game.Characters.Bucket;
import game.Characters.Cloud;
import game.Characters.Dropable;
import game.Characters.Anvil;
import game.Characters.RainDrop;
import game.Managers.ResourceManager;
import game.Utils.InformationSaver;

public class SimpleGame extends ApplicationAdapter {
  // Elementos do jogo (jogador, npc e inimigo)
  private Bucket bucket;
  private Cloud cloud;
  private ArrayList<Dropable> dropableObjects = new ArrayList<Dropable>();

  // Variaveis de controle para criacao de novos inimigos
  private long lastRaindropTime;
  private long lastAnvilTime;

  // Classes singleton que acessam os mesmo elementos em qualquer parte do jogo
  private ResourceManager resources = ResourceManager.getInstance();
  private InformationSaver gameInformation = InformationSaver.getInstance();

  // Variaveis exclusivas do SimpleGame
  private BitmapFont font;
  private SpriteBatch batch;
  private OrthographicCamera camera;

  // Variavel que armazena a maior pontução do SimpleGame
  private int gameHighScore = Integer.parseInt(gameInformation.getProps("game.highScore"));

  private void loadResources() {
    // Loading all used textures to Resource Manager
    resources.addTexture("bucket", "assets/images/bucket.png");
    resources.addTexture("cloud", "assets/images/cloud.png");
    resources.addTexture("raindrop", "assets/images/droplet.png");
    resources.addTexture("anvil", "assets/images/anvil.png");

    // Loading all used sounds to Resounce Manager
    resources.addSound("drop", "assets/sounds/drop.wav");
    resources.addSound("anvil", "assets/sounds/anvilSound.wav");

    // Loading all used music to Resource Manager
    resources.addMusic("rain", "assets/sounds/rain.mp3");
  }

  private void loadCharacthers() {
    font = new BitmapFont();

    bucket = new Bucket("bucket");
    cloud = new Cloud("cloud");

    createDropableObject();
  }

  private void createDropableObject() {
    if (Boolean.parseBoolean(gameInformation.getProps("game.isValid"))) {
      for (String raindropInfo : gameInformation.getProps("object.raindrops.position").split("/")) {
        ArrayList<Float> raindropPosition = new ArrayList<Float>();

        for (String stringToFloat : raindropInfo.split(";")) {
          raindropPosition.add(Float.parseFloat(stringToFloat));
        }

        dropableObjects.add(new RainDrop("raindrop", raindropPosition.get(0), raindropPosition.get(1)));
        lastRaindropTime = TimeUtils.nanoTime();
      }

      for (String anvilInfo : gameInformation.getProps("object.anvils.position").split("/")) {
        ArrayList<Float> anvilPosition = new ArrayList<Float>();

        for (String stringToFloat : anvilInfo.split(";")) {
          anvilPosition.add(Float.parseFloat(stringToFloat));
        }

        dropableObjects.add(new Anvil("anvil", anvilPosition.get(0), anvilPosition.get(1)));
        lastAnvilTime = TimeUtils.nanoTime();
      }
    }
  }

  private void spawnDropableObject() {
    if (TimeUtils.nanoTime() - lastRaindropTime > 1000000000) {
      dropableObjects.add(new RainDrop("raindrop"));
      lastRaindropTime = TimeUtils.nanoTime();
    }

    if (TimeUtils.nanoTime() - lastAnvilTime > MathUtils.random(1.0f, 2.0f) * 1000000000) {
      dropableObjects.add(new Anvil("anvil"));
      lastAnvilTime = TimeUtils.nanoTime();
    }
  }

  private void drawObjects() {
    // Desenha o balde e a nuvem na tela
    bucket.renderObject(batch);
    cloud.renderObject(batch);

    // Percorre todos os elementos dropaveis e os desenha na tela
    for (Dropable dropable : dropableObjects) {
      dropable.renderObject(batch);
    }

    // Desenha os texto de informações na tela
    font.draw(batch, "Vidas: " + bucket.getVidas(), 10, 465);
    font.draw(batch, "Pontuacao: " + bucket.getPontos(), 10, 450);
    font.draw(batch, "Maior pontuação: " + gameHighScore, 10, 435);
  }

  private void moveObjects() {
    bucket.moveObject();
    cloud.moveObject();

    for (Dropable dropable : dropableObjects) {
      dropable.moveObject();
    }
  }

  @Override
  public void create() {
    // carregando os texturas
    loadResources();

    // carregando os personagens
    loadCharacthers();

    // create the camera and the SpriteBatch
    camera = new OrthographicCamera();
    camera.setToOrtho(false, 800, 480);
    batch = new SpriteBatch();

    // Toca a musica de fundo em loop depois de ser carregada
    resources.getMusic("rain").setLooping(true);
    resources.getMusic("rain").play();
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

    // chamando a função que move os objetos na tela
    moveObjects();

    // chamando a função que gera novos objetos na tela
    spawnDropableObject();

    // O for abaixo percorre o vetor polimorfico que contem todos os elementos
    // dropaveis
    for (Iterator<Dropable> iter = dropableObjects.iterator(); iter.hasNext();) {
      Dropable dropable = iter.next();

      // Remove o objeto do vetor caso ele saia para fora da tela
      if (dropable.isOutOfScreen()) {
        iter.remove();
      }

      // Verifica se o objeto colidiu com o balde
      if (dropable.isColliding(bucket.getRect())) {

        // Verifica se o objeto colidido é uma gota e chama as devidas funções se
        // verdadeiro
        if (dropable instanceof RainDrop) {
          resources.getSound("drop").play();

          bucket.ganharPontos();
          iter.remove();

          if (bucket.getPontos() % 20 == 0) {
            bucket.ganharVidas();
          }

          if (bucket.getPontos() > gameHighScore) {
            gameHighScore = bucket.getPontos();
            gameInformation.saveProps("game.highScore", String.valueOf(bucket.getPontos()));
          }
        }

        // Verifica se o objeto colidido é uma bigorna e chama as devidas funções se
        // verdadeiro
        if (dropable instanceof Anvil) {
          resources.getSound("anvil").play();

          bucket.perderVidas();
          iter.remove();

          if (bucket.getVidas() <= 0) {
            Gdx.app.exit();

            dispose();
          }
        }
      }
    }
  }

  @Override
  public void dispose() {
    if (bucket.getVidas() > 0) {
      gameInformation.saveProps("game.isValid", "true");
    } else {
      gameInformation.saveProps("game.isValid", "false");
    }

    bucket.dispose();
    cloud.dispose();

    StringBuilder raindropsPositions = new StringBuilder();
    StringBuilder anvilsPositions = new StringBuilder();
    for (Dropable dropable : dropableObjects) {
      if (dropable instanceof RainDrop) {
        raindropsPositions.append(dropable.toString() + "/");
      }

      if (dropable instanceof Anvil) {
        anvilsPositions.append(dropable.toString() + "/");
      }
    }
    gameInformation.saveProps("object.raindrops.position", raindropsPositions.toString());
    gameInformation.saveProps("object.anvils.position", anvilsPositions.toString());

    font.dispose();
    batch.dispose();
  }
}
