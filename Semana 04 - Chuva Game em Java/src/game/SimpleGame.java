package game;

import java.util.Iterator;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import game.Characters.Bucket;
import game.Characters.RainDrop;

public class SimpleGame extends ApplicationAdapter {
	private Bucket baldinho;
	private Texture dropImage;
	// private Texture bucketImage;
	private Sound dropSound;
	private Music rainMusic;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	// private Rectangle bucket;
	private Array<RainDrop> raindrops;
	private long lastDropTime;

	@Override
	public void create() {
		// Creating a bucket class
		baldinho = new Bucket("assets/bucket.png");
		
		// load the images for the droplet and the bucket, 64x64 pixels each
		dropImage = new Texture((Gdx.files.internal("assets/droplet.png")));
		// bucketImage = new Texture(Gdx.files.internal("assets/bucket.png"));

		// load the drop sound effect and the rain background "music"
		dropSound = Gdx.audio.newSound(Gdx.files.internal("assets/drop.wav"));
		rainMusic = Gdx.audio.newMusic(Gdx.files.internal("assets/rain.mp3"));

		// start the playback of the background music immediately
		rainMusic.setLooping(true);
		rainMusic.play();

		// create the camera and the SpriteBatch
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();

		// create a Rectangle to logically represent the bucket
		// baldinho.bucket = new Rectangle();
		// bucket.x = 800 / 2 - 64 / 2; // center the bucket horizontally
		// bucket.y = 20; // bottom left corner of the bucket is 20 pixels above the bottom screen edge
		// bucket.width = 64;
		// bucket.height = 64;

		// create the raindrops array and spawn the first raindrop
		raindrops = new Array<RainDrop>();
		spawnRaindrop();
	}

	private void spawnRaindrop() {
		RainDrop raindrop = new RainDrop(dropImage);
		raindrops.add(raindrop);
		lastDropTime = TimeUtils.nanoTime();
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
		batch.draw(baldinho.image, baldinho.bucket.x, baldinho.bucket.y);
		for (RainDrop raindrop : raindrops) {
			batch.draw(raindrop.image, raindrop.drop.x, raindrop.drop.y);
		}
		batch.end();

		// process user input
		if (Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			baldinho.bucket.x = touchPos.x - 64 / 2;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
			baldinho.bucket.x -= 400 * Gdx.graphics.getDeltaTime();
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
			baldinho.bucket.x += 400 * Gdx.graphics.getDeltaTime();

		// make sure the bucket stays within the screen bounds
		if (baldinho.bucket.x < 0)
			baldinho.bucket.x = 0;
		if (baldinho.bucket.x > 800 - 64)
			baldinho.bucket.x = 800 - 64;

		// check if we need to create a new raindrop
		if (TimeUtils.nanoTime() - lastDropTime > 1000000000)
			spawnRaindrop();

		// move the raindrops, remove any that are beneath the bottom edge of
		// the screen or that hit the bucket. In the latter case we play back
		// a sound effect as well.
		for (Iterator<RainDrop> iter = raindrops.iterator(); iter.hasNext();) {
			RainDrop raindrop = iter.next();
			raindrop.drop.y -= 200 * Gdx.graphics.getDeltaTime();
			if (raindrop.drop.y + 64 < 0)
				iter.remove();
			if (raindrop.drop.overlaps(baldinho.bucket)) {
				dropSound.play();
				iter.remove();
			}
		}
	}

	@Override
	public void dispose() {
		// dispose of all the native resources
		for (RainDrop rainDrop : raindrops) {
			rainDrop.image.dispose();
		}
		
		baldinho.image.dispose();
		dropSound.dispose();
		rainMusic.dispose();
		batch.dispose();
	}
}
