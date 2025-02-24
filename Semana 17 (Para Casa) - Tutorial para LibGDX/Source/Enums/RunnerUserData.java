package Enums;

import com.badlogic.gdx.math.Vector2;

import Utils.Constants;

public class RunnerUserData extends UserData {
  private Vector2 jumpingLinearImpulse;
  private Vector2 runningPostion = new Vector2(Constants.RUNNER_X, Constants.RUNNER_Y);
  private Vector2 dodgePosition = new Vector2(Constants.RUNNER_DODGE_X, Constants.RUNNER_DODGE_X);

  public RunnerUserData() {
    super();
    jumpingLinearImpulse = Constants.RUNNER_JUMPING_LINEAR_IMPULSE;
    userDataType = UserDataType.RUNNER;
  }

  public RunnerUserData(float width, float height) {
    super(width, height);
    jumpingLinearImpulse = Constants.RUNNER_JUMPING_LINEAR_IMPULSE;
    userDataType = UserDataType.RUNNER;
  }

  public Vector2 getJumpingLinearImpulse() {
    return jumpingLinearImpulse;
  }

  public void setJumpingLinearImpulse(Vector2 jumpingLinearImpulse) {
    this.jumpingLinearImpulse = jumpingLinearImpulse;
  }

  public float getDodgeAngle() {
    return (float) (-90f * (Math.PI / 180f));
  }

  public Vector2 getRunningPosition() {
    return runningPostion;
  }

  public Vector2 getDodgePosition() {
    return dodgePosition;
  }

  public float getHitAngulaImpulse() {
    return Constants.RUNNER_HIT_ANGULAR_IMPULSE;
  }
}
