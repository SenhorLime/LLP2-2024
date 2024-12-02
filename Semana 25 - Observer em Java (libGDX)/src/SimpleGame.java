
import java.util.ArrayList;
import java.util.Iterator;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class SimpleGame extends ApplicationAdapter {
	private Texture dropImage;
	private Texture bucketImage;
	private Texture nuvemImage;

	private BitmapFont font;

	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Publisher publisher;

	static public Balde bucket;
	private Nuvem nuvem;
	Chuva chuva;

	@Override
	public void create() {
		Texture.setEnforcePotImages(false);
		// load the images for the droplet and the bucket, 64x64 pixels each
		dropImage = new Texture(Gdx.files.internal("assets/droplet.png"));

		font = new BitmapFont();

		// create the camera and the SpriteBatch
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();

		// create a Rectangle to logically represent the bucket
		Rectangle rect = new Rectangle();
		rect.x = 800 / 2 - 64 / 2; // center the bucket horizontally
		rect.y = 20; // bottom left corner of the bucket is 20 pixels above the bottom screen edge
		rect.width = 64;
		rect.height = 64;

		bucketImage = new Texture(Gdx.files.internal("assets/bucket.png"));
		bucket = new Balde(bucketImage, rect);

		Rectangle rectNuvem = new Rectangle(rect);
		rectNuvem.y = 400;
		nuvemImage = new Texture(Gdx.files.internal("assets/cloud.png"));
		nuvem = new Nuvem(nuvemImage, rectNuvem);

		publisher = new Publisher();
		publisher.subscribe(bucket);
		publisher.subscribe(nuvem);

		chuva = new Chuva(publisher);

	}

	@Override
	public void render() {
		// clear the screen with a dark blue color. The
		// arguments to glClearColor are the red, green
		// blue and alpha component in the range [0,1]
		// of the color to be used to clear the screen.
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// tell the camera to update its matrices.
		camera.update();

		// tell the SpriteBatch to render in the
		// coordinate system specified by the camera.
		batch.setProjectionMatrix(camera.combined);

		// begin a new batch and draw the bucket and
		// all drops
		batch.begin();
		{
			bucket.draw(batch);
			chuva.draw(batch);
			nuvem.draw(batch);
			font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 740, 470);
		}
		batch.end();

		if (Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
		}

		bucket.update();
		nuvem.update();
		// check if we need to create a new raindrop
		chuva.update(TimeUtils.nanoTime());
		chuva.TrataColisao(bucket);

	}

	@Override
	public void dispose() {
		// dispose of all the native resources
		dropImage.dispose();
		bucketImage.dispose();
		chuva.dispose();
		batch.dispose();
	}
}

class Movel {
	protected Texture image;
	protected Sound sound;
	protected Music music;

	protected Rectangle rect;

	Movel(Texture novoImage, Rectangle novoRect) {
		this(novoImage, null, null, novoRect);
	}

	Movel(Texture novoImage, Sound novoSound, Music novoMusic, Rectangle novoRect) {
		image = novoImage;// new Texture(Gdx.files.internal("assets/droplet.png"));
		sound = novoSound;// Gdx.audio.newSound(Gdx.files.internal("assets/drop.wav"));
		music = novoMusic;// Gdx.audio.newMusic(Gdx.files.internal("assets/rain.mp3"));

		// create the camera and the SpriteBatch
		rect = novoRect;
	}

	public void playMusic() {
		if (music == null)
			return;
		// start the playback of the background music immediately
		music.setLooping(true);
		music.play();

	}

	public void playSound() {
		if (sound == null)
			return;
		sound.play();
	}

	public void draw(SpriteBatch batch) {
		if ((image == null) || (rect == null))
			return;
		batch.draw(image, rect.x, rect.y);
	}

	public void dispose() {
		image.dispose();
		sound.dispose();
		music.dispose();
	}

	public float getX() {
		return rect.x;
	}

	public void setX(float x) {
		this.rect.x = x;
	}

	public float getY() {
		return rect.y;
	}

	public void setY(float y) {
		this.rect.y = y;
	}

	public Rectangle getRect() {
		return rect;
	}

	public void setRect(Rectangle rect) {
		this.rect = rect;
	}
}

class Balde extends Movel implements EventListener {
	Movel objetivo = null;

	Balde(Texture novoImage, Rectangle novoRect) {
		super(novoImage, novoRect);
	}

	public void update() {
		// process user input
		if (objetivo == null) {
			if (Gdx.input.isTouched()) {
				Vector3 touchPos = new Vector3();
				touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
				rect.setX(touchPos.x - 64 / 2);
				System.out.println("Mouse");
			}
			if (Gdx.input.isKeyPressed(Keys.LEFT))
				rect.setX(rect.getX() - 200 * Gdx.graphics.getDeltaTime());
			if (Gdx.input.isKeyPressed(Keys.RIGHT))
				rect.setX(rect.getX() + 200 * Gdx.graphics.getDeltaTime());
		} else {
			float x = objetivo.getX() - rect.getX();
			if (x > 0) {
				rect.setX(rect.getX() + 200 * Gdx.graphics.getDeltaTime());
			} else {
				rect.setX(rect.getX() - 200 * Gdx.graphics.getDeltaTime());
			}
			if (Math.abs(x) < 10) {
				objetivo = null;
			}
		}

		// make sure the bucket stays within the screen bounds
		if (rect.getX() < 0)
			rect.setX(0);
		if (rect.getX() > 800 - 64)
			rect.setX(800 - 64);
	}

	@Override
	public void update(Movel movel) {
		this.objetivo = movel;

	}
}

class Pingo extends Movel {
	Pingo(Texture novoImage, Sound novoSound, Music novoMusic, Rectangle novoRect) {
		super(novoImage, novoSound, novoMusic, novoRect);
	}

}

class Nuvem extends Movel implements EventListener {
	public Movel objetivo;

	Nuvem(Texture novoImage, Rectangle novoRect) {// , Sound novoSound, Music novoMusic, Rectangle novoRect) {
		super(novoImage, null, null, novoRect);
		objetivo = null;
	}

	public void update() {
		if (objetivo == null) {
			rect.setX(rect.getX() + 200 * Gdx.graphics.getDeltaTime());
			if (rect.getX() > 800) {
				rect.setX(-100);
			}
		} else {
			float x = objetivo.getX() - rect.getX();
			if (x > 0) {
				rect.setX(rect.getX() + 200 * Gdx.graphics.getDeltaTime());
			} else {
				rect.setX(rect.getX() - 200 * Gdx.graphics.getDeltaTime());
			}

			if (Math.abs(x) < 10) {
				objetivo = null;
			}
		}
	}

	@Override
	public void update(Movel movel) {
		this.objetivo = movel;
	}
}

class Chuva {
	private Array<Movel> pingos;
	private long lastDropTime;
	private static Texture dropImage = new Texture(Gdx.files.internal("assets/droplet.png"));
	private static Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("assets/drop.wav"));
	private Music rainMusic = Gdx.audio.newMusic(Gdx.files.internal("assets/rain.mp3"));
	Publisher publisher;

	Chuva(Publisher publisher) {
		// start the playback of the background music immediately
		rainMusic.setLooping(true);
		rainMusic.play();
		this.publisher = publisher;
		// create the raindrops array and spawn the first raindrop
		pingos = new Array<Movel>();
		spawnRaindrop();
	}

	private void spawnRaindrop() {
		Rectangle rect = new Rectangle();
		rect.x = MathUtils.random(0, 800 - 64);
		rect.y = 480;
		rect.width = 64;
		rect.height = 64;
		Movel pingo = new Pingo(dropImage, dropSound, null, rect);
		pingos.add(pingo);
		// SimpleGame.bucket.objetivo = pingo;
		publisher.moveCharacther(pingo);
		lastDropTime = TimeUtils.nanoTime();
	}

	public void draw(SpriteBatch batch) {
		for (Movel movel : pingos) {
			movel.draw(batch);
		}
	}

	public void update(long time) {
		if (time - lastDropTime > 3000000000f)
			this.spawnRaindrop();

		// move the raindrops, remove any that are beneath the bottom edge of
		// the screen or that hit the bucket. In the later case we play back
		// a sound effect as well.
		Iterator<Movel> iter = this.pingos.iterator();
		while (iter.hasNext()) {
			Movel pingo = iter.next();
			pingo.setY(pingo.getY() - 200 * Gdx.graphics.getDeltaTime());
			if (pingo.getY() + 64 < 0)
				iter.remove();
		}
	}

	public void dispose() {
		dropImage.dispose();
		dropSound.dispose();
		rainMusic.dispose();
	}

	public void TrataColisao(Balde bucket) {
		Iterator<Movel> iter = this.pingos.iterator();
		while (iter.hasNext()) {
			Movel pingo = iter.next();
			if (pingo.getRect().overlaps(bucket.getRect())) {
				dropSound.play();
				iter.remove();
			}
		}
	}

}

interface EventListener {
	void update(Movel movel);
}

class Publisher {
	// public EventManager events;
	private ArrayList<EventListener> subscribers = new ArrayList<>();
	private Movel movel;

	public Publisher() {
		this.movel = null;
	}

	public void moveCharacther(Movel movel) {
		this.movel = movel;
		notifySubs();
	}

	public void subscribe(EventListener listener) {
		// List<EventListener> users = listeners.get(eventType);
		subscribers.add(listener);
	}

	public void unsubscribe(EventListener listener) {
		// List<EventListener> users = listeners.get(eventType);
		subscribers.remove(listener);
	}

	public void notifySubs() {
		// List<EventListener> users = listeners.get(eventType);
		for (EventListener listener : subscribers) {
			listener.update(movel);
		}
	}
}
