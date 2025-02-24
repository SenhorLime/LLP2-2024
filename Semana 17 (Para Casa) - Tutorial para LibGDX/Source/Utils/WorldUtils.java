package Utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import Enums.EnemyType;
import Enums.EnemyUserData;
import Enums.GroundUserData;
import Enums.RunnerUserData;

public class WorldUtils {
  public static World createWorld() {
    return new World(Constants.WORLD_GRAVITY, true);
  }

  public static Body createGround(World world) {
    BodyDef bodyDef = new BodyDef();
    bodyDef.position.set(new Vector2(Constants.GROUND_X, Constants.GROUND_Y));

    PolygonShape shape = new PolygonShape();
    shape.setAsBox(Constants.GROUND_WIDTH / 2, Constants.GROUND_HEIGHT / 2);

    Body body = world.createBody(bodyDef);
    body.createFixture(shape, Constants.GROUND_DENSITY);
    body.setUserData(new GroundUserData());

    shape.dispose();
    return body;
  }

  public static Body createRunner(World world) {
    BodyDef bodyDef = new BodyDef();
    bodyDef.type = BodyDef.BodyType.DynamicBody;
    bodyDef.position.set(new Vector2(Constants.RUNNER_X, Constants.RUNNER_Y));

    PolygonShape shape = new PolygonShape();
    shape.setAsBox(Constants.RUNNER_WIDTH / 2, Constants.RUNNER_HEIGHT / 2);

    Body body = world.createBody(bodyDef);
    body.createFixture(shape, Constants.RUNNER_DENSITY);
    body.resetMassData();
    body.setUserData(new RunnerUserData(Constants.RUNNER_WIDTH, Constants.RUNNER_HEIGHT));

    shape.dispose();
    return body;
  }

  public static Body createEnemy(World world) {
    EnemyType enemyType = RandomUtils.getRandomEnemyType();

    BodyDef bodyDef = new BodyDef();
    bodyDef.type = BodyDef.BodyType.KinematicBody;
    bodyDef.position.set(new Vector2(enemyType.getX(), enemyType.getY()));

    PolygonShape shape = new PolygonShape();
    shape.setAsBox(enemyType.getWidth() / 2, enemyType.getHeight() / 2);

    Body body = world.createBody(bodyDef);
    body.createFixture(shape, enemyType.getDensity());
    body.resetMassData();

    EnemyUserData userData = new EnemyUserData(enemyType.getWidth(), enemyType.getHeight());
    body.setUserData(userData);

    shape.dispose();
    return body;
  }
}
