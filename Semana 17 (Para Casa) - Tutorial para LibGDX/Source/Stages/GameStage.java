package Stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;

import Actors.Enemy;
import Actors.Ground;
import Actors.Runner;
import Utils.BodyUtils;
import Utils.Constants;
import Utils.WorldUtils;

public class GameStage extends Stage implements ContactListener {
  private static final int VIEWPORT_WIDTH = Constants.APP_WIDTH;
  private static final int VIEWPORT_HEIGHT = Constants.APP_HEIGHT;

  private World world;
  private Ground ground;
  private Runner runner;

  private final float TIME_STEP = 1 / 300f;
  private float accumulator = 0f;

  private OrthographicCamera camera;
  private Box2DDebugRenderer renderer;

  private Rectangle screenRightSide;
  private Rectangle screenLeftSide;

  private Vector3 touchPoint;

  public GameStage() {
    super(new ScallingViewPort())
    setUpWorld();
    setupCamera();
    setupTouchControlAreas();
    renderer = new Box2DDebugRenderer();
  }

  private void setUpWorld() {
    world = WorldUtils.createWorld();
    world.setContactListener(this);

    setUpGround();
    setUpRunner();
    createEnemy();
  }

  private void setUpGround() {
    ground = new Ground(WorldUtils.createGround(world));
    addActor(ground);
  }

  private void setUpRunner() {
    runner = new Runner(WorldUtils.createRunner(world));
    addActor(runner);
  }

  private void setupCamera() {
    camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
    camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
    camera.update();
  }

  private void setupTouchControlAreas() {
    touchPoint = new Vector3();
    screenLeftSide = new Rectangle(0, 0, getCamera().viewportWidth / 2, getCamera().viewportHeight);
    screenRightSide = new Rectangle(getCamera().viewportWidth / 2, 0, getCamera().viewportWidth / 2,
        getCamera().viewportHeight);
    Gdx.input.setInputProcessor(this);
  }

  @Override
  public void act(float delta) {
    super.act(delta);

    Array<Body> bodies = new Array<Body>(world.getBodyCount());
    world.getBodies();

    for (Body body : bodies) {
      update(body);
    }

    accumulator += delta;

    while (accumulator >= delta) {
      world.step(TIME_STEP, 6, 2);
      accumulator -= TIME_STEP;
    }
  }

  private void update(Body body) {
    if (!BodyUtils.bodyInBounds(body)) {
      if (BodyUtils.bodyIsEnemy(body) && !runner.isHit()) {
        createEnemy();
      }

      world.destroyBody(body);
    }
  }

  private void createEnemy() {
    Enemy enemy = new Enemy(WorldUtils.createEnemy(world));
    addActor(enemy);
  }

  @Override
  public void draw() {
    super.draw();
    renderer.render(world, camera.combined);
  }

  @Override
  public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    translateScreenToWorldCoordinates(screenX, screenY);

    if (rightSideTouched(touchPoint.x, touchPoint.y)) {
      runner.jump();
    } else if (leftSideTouched(touchPoint.x, touchPoint.y)) {
      runner.dodge();
    }

    return super.touchDown(screenX, screenY, pointer, button);
  }

  @Override
  public boolean touchUp(int screenX, int screenY, int pointer, int button) {
    if (runner.isDodging()) {
      runner.stopDodge();
    }

    return super.touchUp(screenX, screenY, pointer, button);
  }

  private boolean rightSideTouched(float x, float y) {
    return screenRightSide.contains(x, y);
  }

  private boolean leftSideTouched(float x, float y) {
    return screenLeftSide.contains(x, y);
  }

  private void translateScreenToWorldCoordinates(int x, int y) {
    getCamera().unproject(touchPoint.set(x, y, 0));
  }

  @Override
  public void beginContact(Contact contact) {
    Body a = contact.getFixtureA().getBody();
    Body b = contact.getFixtureB().getBody();

    if ((BodyUtils.bodyIsRunner(a) && BodyUtils.bodyIsEnemy(b))
        || (BodyUtils.bodyIsEnemy(a) && BodyUtils.bodyIsRunner(b))) {
      runner.hit();
    } else if ((BodyUtils.bodyIsRunner(a) && BodyUtils.bodyIsGround(b))
        || (BodyUtils.bodyIsGround(a) && BodyUtils.bodyIsRunner(b))) {
      runner.landed();
    }
  }

  @Override
  public void endContact(Contact contact) {
  }

  @Override
  public void postSolve(Contact contact, ContactImpulse impulse) {
  }

  @Override
  public void preSolve(Contact contact, Manifold oldManifold) {
  }
}
