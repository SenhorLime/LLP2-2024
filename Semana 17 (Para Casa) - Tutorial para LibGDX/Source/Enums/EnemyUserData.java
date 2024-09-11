package Enums;

import com.badlogic.gdx.math.Vector2;

import Utils.Constants;

public class EnemyUserData extends UserData {
  private Vector2 linearVelocity;

  public EnemyUserData(float width, float height) {
    super(width, height);
    userDataType = UserDataType.ENEMY;
    linearVelocity = Constants.ENEMY_LINEAR_VELOCITY;
  }

  public void setLinearVelocity(Vector2 linearVelocity) {
    this.linearVelocity = linearVelocity;
  }

  public Vector2 getLinearVelocity() {
    return linearVelocity;
  }
}
