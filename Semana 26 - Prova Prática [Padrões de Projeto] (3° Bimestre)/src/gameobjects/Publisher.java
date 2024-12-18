package gameobjects;

import java.util.ArrayList;

interface EventListener {
  void update(Movel movel);
}

public class Publisher {
  private ArrayList<EventListener> listeners = new ArrayList<EventListener>();
  private Movel movel;
  private static Publisher instance;

  private Publisher() {
    this.movel = null;
  }

  public static Publisher getInstance() {
    if (instance == null) {
      instance = new Publisher();
    }

    return instance;
  }
  public void moveCharacther(Movel movel) {
    this.movel = movel;
    notifySubs();
  }

  public void subscribe(EventListener listener) {
    listeners.add(listener);
  }

  public void unsubscribe(EventListener listener) {
    listeners.remove(listener);
  }

  public void notifySubs() {
    for (EventListener listener : listeners) {
      listener.update(movel);
    }
  }
}
