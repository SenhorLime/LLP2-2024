package game.Managers;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class ResourceManager {
  private HashMap<String, Texture> textureMap = new HashMap<String, Texture>();
  private HashMap<String, Sound> soundMap = new HashMap<String, Sound>();
  private HashMap<String, Music> musicMap = new HashMap<String, Music>();

  private static ResourceManager INSTANCE;

  private ResourceManager() {
  }

  public static ResourceManager getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new ResourceManager();
    }

    return INSTANCE;
  }

  public void addMusic(String name, String path) {
    addMusic(name, Gdx.audio.newMusic(Gdx.files.internal(path)));
  }

  public void addMusic(String name, Music music) {
    this.musicMap.put(name, music);
  }

  public void addSound(String name, String path) {
    addSound(name, Gdx.audio.newSound(Gdx.files.internal(path)));
  }

  public void addSound(String name, Sound sound) {
    this.soundMap.put(name, sound);
  }

  public void addTexture(String name, String path) {
    addTexture(name, new Texture(Gdx.files.internal(path)));
  }

  public void addTexture(String name, Texture texture) {
    this.textureMap.put(name, texture);
  }

  public Texture getTexture(String name) {
    return this.textureMap.get(name);
  }

  public Sound getSound(String name) {
    return this.soundMap.get(name);
  }

  public Music getMusic(String name) {
    return this.musicMap.get(name);
  }
}
