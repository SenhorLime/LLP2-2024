package main.Assets;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public interface IAssetManager {
  Texture getTexture(String label);

  Sound getSound(String label);

  Music getMusic(String label);

  void removeTexture(String label);

  void removeSound(String label);

  void removeMusic(String label);

  void loadMusic(String string, String string2);

  void loadSound(String string, String string2);

  void loadTexture(String string, String string2);

  void dispose();
}
