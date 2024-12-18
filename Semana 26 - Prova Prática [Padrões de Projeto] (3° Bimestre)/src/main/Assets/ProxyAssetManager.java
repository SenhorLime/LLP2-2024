package main.Assets;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class ProxyAssetManager implements IAssetManager {
  private AssetManager assetManagerInstance;
  private static ProxyAssetManager instance;
  // private Map<String, Texture> textures;
  // private Map<String, Sound> sounds;
  // private Map<String, Music> musics;

  private ProxyAssetManager() {
    this.assetManagerInstance = AssetManager.getInstance();

    assetManagerInstance.loadMusic("null_music", "assets/null.mp3");
    assetManagerInstance.loadSound("null_sound", "assets/null.wav");
    assetManagerInstance.loadTexture("null_image", "assets/null.png");

    // textures = new HashMap<String, Texture>();
    // textures.put("null_image", new
    // Texture(Gdx.files.internal("assets/null.png")));

    // sounds = new HashMap<String, Sound>();
    // sounds.put("null_sound",
    // Gdx.audio.newSound(Gdx.files.internal("assets/null.wav")));

    // musics = new HashMap<String, Music>();
    // musics.put("null_music",
    // Gdx.audio.newMusic(Gdx.files.internal("assets/null.mp3")));
  }

  public static ProxyAssetManager getInstance() {
    if (instance == null) {
      instance = new ProxyAssetManager();
    }

    return instance;
  }

  @Override
  public Texture getTexture(String label) {
    if (assetManagerInstance.getTexture(label) == null) {
      return assetManagerInstance.getTexture("null_image");

    }

    return assetManagerInstance.getTexture(label);
  }

  @Override
  public Sound getSound(String label) {
    if (assetManagerInstance.getSound(label) == null) {
      return assetManagerInstance.getSound("null_sound");
    }

    return assetManagerInstance.getSound(label);

  }

  @Override
  public Music getMusic(String label) {
    if (assetManagerInstance.getSound(label) == null) {
      return assetManagerInstance.getMusic("null_music");
    }

    return assetManagerInstance.getMusic(label);

  }

  @Override
  public void removeTexture(String label) {
    assetManagerInstance.removeTexture(label);
  }

  @Override
  public void removeSound(String label) {
    assetManagerInstance.removeSound(label);
  }

  @Override
  public void removeMusic(String label) {
    assetManagerInstance.removeMusic(label);
  }

  @Override
  public void loadMusic(String string, String string2) {
    assetManagerInstance.loadMusic(string, string2);
  }

  @Override
  public void loadSound(String string, String string2) {
    assetManagerInstance.loadSound(string, string2);
  }

  @Override
  public void loadTexture(String string, String string2) {
    assetManagerInstance.loadTexture(string, string2);
  }

  @Override
  public void dispose() {
    assetManagerInstance.dispose();
  }
}
