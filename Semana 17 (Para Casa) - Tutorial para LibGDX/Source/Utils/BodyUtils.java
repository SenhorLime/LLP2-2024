package Utils;

import com.badlogic.gdx.physics.box2d.Body;

import Enums.UserData;
import Enums.UserDataType;

public class BodyUtils {
  public static boolean bodyIsRunner(Body body) {
    UserData userData = (UserData) body.getUserData();

    return userData != null && userData.getUserDataType() == UserDataType.RUNNER;
  }

  public static boolean bodyIsGround(Body body) {
    UserData userData = (UserData) body.getUserData();

    return userData != null && userData.getUserDataType() == UserDataType.GROUND;
  }

  public static boolean bodyInBounds(Body body) {
    UserData userData = (UserData) body.getUserData();

    switch (userData.getUserDataType()) {
      case RUNNER:
      case ENEMY:
        return body.getPosition().x + userData.getWidth() / 2 > 0;
    }

    return true;
  }

  public static boolean bodyIsEnemy(Body body) {
    UserData userData = (UserData) body.getUserData();

    return userData != null && userData.getUserDataType() == UserDataType.ENEMY;
  }

}