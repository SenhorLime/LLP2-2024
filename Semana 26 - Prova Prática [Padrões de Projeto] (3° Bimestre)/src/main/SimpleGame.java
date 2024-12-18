package main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import gameobjects.Balde;
import gameobjects.Chuva;
import main.Assets.ProxyAssetManager;
import gameobjects.BolasManager;

public class SimpleGame extends ApplicationAdapter {

	private BitmapFont font;

	private SpriteBatch batch;
	private OrthographicCamera camera;

	/**
	 * Gerenciador de Assets.
	 * Carrega e torna dispon�vel
	 * a partir de um nome.
	 */
	// public IAssetManager assetManager;
	private ProxyAssetManager proxyAssetManager;
	// private Publisher publisher;

	public Balde bucket;

	Chuva chuva;

	public static BolasManager bolasManager;

	@Override
	public void create() {
		Texture.setEnforcePotImages(false); // Permite imagens de qualquer tamanho

		// Carrega a fonte
		font = new BitmapFont();

		// assetManager = AssetManager.getInstance();
		proxyAssetManager = ProxyAssetManager.getInstance();

		proxyAssetManager.loadTexture("droplet", "assets/droplet.png");
		proxyAssetManager.loadTexture("bucketImage", "assets/bucket.png");
		proxyAssetManager.loadSound("dropSound", "assets/drop.wav");
		proxyAssetManager.loadMusic("rainMusic", "assets/rain.mp3");
		proxyAssetManager.loadTexture("nuvemImage", "assets/cloud1.png");
		proxyAssetManager.loadTexture("bollImage1", "assets/boll_effect1.png");
		proxyAssetManager.loadTexture("bollImage2", "assets/boll_effect2.png");

		// criar a c�mera e o SpriteBatch
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Constantes.LARGURA, Constantes.ALTURA);
		batch = new SpriteBatch();

		bucket = Balde.getInstance();
		chuva = new Chuva();
		bolasManager = new BolasManager();

		// publisher = Publisher.getInstance();

	}

	@Override
	public void render() {
		// limpa a tela com uma cor azul escuro. Os
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Atualiza camera
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		// Desenha
		batch.begin();
		{
			bucket.draw(batch);
			chuva.draw(batch);
			bolasManager.draw(batch);
			font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), Constantes.LARGURA - 100,
					Constantes.ALTURA - 15);
			font.draw(batch, "Pontos: " + bucket.pontos, Constantes.LARGURA - 100,
					Constantes.ALTURA - 45);
		}
		batch.end();

		// Atualiza
		bucket.update();
		chuva.update();
		bolasManager.update();

	}

	@Override
	public void dispose() {
		// descarte todos os recursos nativos
		proxyAssetManager.dispose();
		chuva.dispose();
		batch.dispose();
	}
}
