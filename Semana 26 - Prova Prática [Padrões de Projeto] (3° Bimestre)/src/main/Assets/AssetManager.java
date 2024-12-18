package main.Assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

public class AssetManager implements IAssetManager {
    private Map<String, Texture> textures;
    private Map<String, Sound> sounds;
    private Map<String, Music> musics;
    private static AssetManager instance;

    public static AssetManager getInstance() {
        if (instance == null) {
            instance = new AssetManager();
        }

        return instance;
    }

    private AssetManager() {
        textures = new HashMap<>();
        sounds = new HashMap<>();
        musics = new HashMap<>();
    }

    // Load Texture
    public void loadTexture(String label, String path) {
        try {
            textures.put(label, new Texture(Gdx.files.internal(path)));
        } catch (Exception e) {
            textures.put(label, null);
            System.err.println("AssetManager: Falha ao ler a textura: " + path);
        }
    }

    // Load Sound
    public void loadSound(String label, String path) {
        try {
            sounds.put(label, Gdx.audio.newSound(Gdx.files.internal(path)));
        } catch (Exception e) {
            sounds.put(label, null);
            System.err.println("Failed to load sound: " + path);
        }
    }

    // Load Music
    public void loadMusic(String label, String path) {
        try {
            musics.put(label, Gdx.audio.newMusic(Gdx.files.internal(path)));
        } catch (Exception e) {
            musics.put(label, null);
            System.err.println("Failed to load music: " + path);
        }
    }

    // Get Texture
    @Override
    public Texture getTexture(String label) {

        return textures.get(label);
    }

    // Get Sound
    @Override
    public Sound getSound(String label) {

        return sounds.get(label);
    }

    // Get Music
    @Override
    public Music getMusic(String label) {

        return musics.get(label);
    }

    // Remove Texture
    @Override
    public void removeTexture(String label) {
        Texture texture = textures.remove(label);
        if (texture != null) {
            texture.dispose();
        }
    }

    // Remove Sound
    @Override
    public void removeSound(String label) {
        Sound sound = sounds.remove(label);
        if (sound != null) {
            sound.dispose();
        }
    }

    // Remove Music
    @Override
    public void removeMusic(String label) {
        Music music = musics.remove(label);
        if (music != null) {
            music.dispose();
        }
    }

    // Dispose all assets
    public void dispose() {
        for (Texture texture : textures.values()) {
            if (texture != null) {
                texture.dispose();
            }
        }
        textures.clear();

        for (Sound sound : sounds.values()) {
            if (sound != null) {
                sound.dispose();
            }
        }
        sounds.clear();

        for (Music music : musics.values()) {
            if (music != null) {
                music.dispose();
            }
        }
        musics.clear();
    }
}
