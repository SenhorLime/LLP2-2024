package game;

import java.util.Iterator;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import game.Characters.*;
import game.Utils.*;

public class SimpleGame extends ApplicationAdapter {
	private Bucket balde;
	private Cloud nuvem;
	private Array<Anvil> bigornas = new Array<Anvil>();
	private Array<RainDrop> rainDrops = new Array<RainDrop>();

	private Texture dropImage;
	private Texture anvilImage;
	// private Texture bucketImage;
	private Sound dropSound;
	private Sound anvilSound;
	private Music rainMusic;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	// private Rectangle bucket;
	// private Array<Rectangle> raindrops;
	private long lastDropTime;
	private long lastAnvilTime;

	private BitmapFont font;
	int pontuacao = 0;
	private int highScore;

	@Override
	public void create() {
		// Inicia highscore
		highScore = InformationSaver.getHighScore();

		balde = new Bucket("assets/bucket.png", new Rectangle((800 / 2 - 64 / 2), 20, 64, 64));
		nuvem = new Cloud("assets/cloud.png", new Rectangle((800 / 2 - 64 / 2), 390, 64, 64));

		// load the images for the droplet and the bucket, 64x64 pixels each
		dropImage = new Texture((Gdx.files.internal("assets/droplet.png")));
		anvilImage = new Texture((Gdx.files.internal("assets/anvil.png")));

		font = new BitmapFont(); // use libGDX's default Arial font

		// load the drop sound effect and the rain background "music"
		dropSound = Gdx.audio.newSound(Gdx.files.internal("assets/drop.wav"));
		anvilSound = Gdx.audio.newSound(Gdx.files.internal("assets/anvilSound.wav"));
		rainMusic = Gdx.audio.newMusic(Gdx.files.internal("assets/rain.mp3"));

		// start the playback of the background music immediately
		rainMusic.setLooping(true);
		rainMusic.play();

		// create the camera and the SpriteBatch
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();

		// // create a Rectangle to logically represent the bucket
		// bucket = new Rectangle();
		// bucket.x = 800 / 2 - 64 / 2; // center the bucket horizontally
		// bucket.y = 20; // bottom left corner of the bucket is 20 pixels above the
		// bottom screen edge
		// bucket.width = 64;
		// bucket.height = 64;

		// create the raindrops array and spawn the first raindrop
		rainDrops = new Array<RainDrop>();
		spawnRaindrop();

		bigornas = new Array<Anvil>();
		spawnAnvil();
	}

	@Override
	public void render() {
		// clear the screen with a dark blue color. The
		// arguments to clear are the red, green
		// blue and alpha component in the range [0,1]
		// of the color to be used to clear the screen.
		// ScreenUtils.clear(0, 0, 0.2f, 1);
		Gdx.gl.glClearColor(0, 0.3f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// tell the camera to update its matrices.
		camera.update();

		// tell the SpriteBatch to render in the
		// coordinate system specified by the camera.
		batch.setProjectionMatrix(camera.combined);

		// begin a new batch and draw the bucket and
		// all drops
		batch.begin();
		for (RainDrop rainDrop : rainDrops) {
			batch.draw(rainDrop.image, rainDrop.rect.x, rainDrop.rect.y);
		}
		for (Anvil bigorna : bigornas) {
			batch.draw(bigorna.image, bigorna.rect.x, bigorna.rect.y);
		}
		batch.draw(balde.image, balde.rect.x, balde.rect.y);
		batch.draw(nuvem.image, nuvem.rect.x, nuvem.rect.y, 96, 96);
		font.draw(batch, "Pontuacao: " + pontuacao, 10, 460);
		font.draw(batch, "Maior Pontuacao: " + highScore, 10, 445);
		batch.end();

		// process user input

		nuvem.mover();
		balde.mover();

		// check if we need to create a new raindrop
		if (TimeUtils.nanoTime() - lastDropTime > 1000000000)
			spawnRaindrop();

		if (TimeUtils.nanoTime() - lastAnvilTime > MathUtils.random(1.0f, 2.0f) * 1000000000)
			spawnAnvil();

		// move the raindrops, remove any that are beneath the bottom edge of
		// the screen or that hit the bucket. In the latter case we play back
		// a sound effect as well.
		for (Iterator<RainDrop> iter = rainDrops.iterator(); iter.hasNext();) {
			RainDrop raindrop = iter.next();
			raindrop.rect.y -= 200 * Gdx.graphics.getDeltaTime();

			if (raindrop.rect.y + 64 < 0) {
				iter.remove();
				pontuacao--;
			}

			if (raindrop.rect.overlaps(balde.rect)) {
				dropSound.play();
				iter.remove();
				pontuacao++;
			}

			for (Anvil bigorna : bigornas) {
				if (raindrop.rect.overlaps(bigorna.rect)) {
					bigornas.removeValue(bigorna, false);
					spawnAnvil();
				}
			}
		}

		for (Iterator<Anvil> iter = bigornas.iterator(); iter.hasNext();) {
			Anvil anvil = iter.next();
			anvil.rect.y -= 200 * Gdx.graphics.getDeltaTime();
			if (anvil.rect.y + 64 < 0) {
				iter.remove();
			}
			if (anvil.rect.overlaps(balde.rect)) {
				if (pontuacao > highScore) {
					InformationSaver.saveHighScore(pontuacao);
				}
				highScore = InformationSaver.getHighScore();
				anvilSound.play();
				iter.remove();
				pontuacao = 0;
			}
		}
	}

	@Override
	public void dispose() {
		if (pontuacao > highScore) {
			InformationSaver.saveHighScore(pontuacao);
		}
		
		// dispose of all the native resources
		for (RainDrop rainDrop : rainDrops) {
			rainDrop.image.dispose();
		}

		for (Anvil bigorna : bigornas) {
			bigorna.image.dispose();
		}

		balde.image.dispose();

		dropImage.dispose();
		dropSound.dispose();
		rainMusic.dispose();
		batch.dispose();
	}

	private void spawnAnvil() {
		Anvil anvil = new Anvil(anvilImage);
		bigornas.add(anvil);
		lastAnvilTime = TimeUtils.nanoTime();
	}

	private void spawnRaindrop() {
		RainDrop raindrop = new RainDrop(dropImage);
		rainDrops.add(raindrop);
		lastDropTime = TimeUtils.nanoTime();
	}

}
