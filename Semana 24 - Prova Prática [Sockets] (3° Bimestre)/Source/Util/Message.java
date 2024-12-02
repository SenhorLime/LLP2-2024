package Source.Util;

import java.io.Serializable;

public class Message implements Serializable {
  private int x, y;
  private String operation;

  public Message(int x, int y, String operation) {
    this.x = x;
    this.y = y;
    this.operation = operation;
  }

  public String getOperation() {
    return operation;
  }

  public void setOperation(String operation) {
    this.operation = operation;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

}
