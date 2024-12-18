package gameobjects;

import java.util.Iterator;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import main.Constantes;
import main.Assets.ProxyAssetManager;

public class Chuva {

	// vari�veis de uso da chuva
	private long lastDropTime;

	/** Pingos e Nuvens **/
	private Array<Movel> chuvaObjects;

	Nuvem nuvem;
	private Publisher publisher;

	public Chuva() {
		// inicia a reprodu��o da m�sica de fundo imediatamente
		ProxyAssetManager.getInstance().getMusic("rainMusic").setLooping(true);
		ProxyAssetManager.getInstance().getMusic("rainMusic").play();

		// criar o array de gotas de chuva e gerar a primeira gota de chuva
		chuvaObjects = new Array<Movel>();
		spawnNuvem();
		publisher = Publisher.getInstance();
	}

	private void spawnPingo() {
		Rectangle rect = new Rectangle();
		Vector2 velocidade = new Vector2();

		rect.x = nuvem.getRect().x;
		// MathUtils.random(0, Constantes.LARGURA - Constantes.GAMEOBJECT_DIM);
		rect.y = nuvem.getRect().y - Constantes.GAMEOBJECT_DIM / 2;
		rect.width = Constantes.GAMEOBJECT_DIM;
		rect.height = Constantes.GAMEOBJECT_DIM;
		velocidade.x = 0;
		velocidade.y = MathUtils.random(100, Constantes.GAMEOBJECT_SPEED);
		Texture dropImage = ProxyAssetManager.getInstance().getTexture("droplet");
		Sound dropSound = ProxyAssetManager.getInstance().getSound("dropSound");
		Movel pingo = new Pingo(dropImage, dropSound, null, rect);
		pingo.setVelocidade(velocidade);
		chuvaObjects.add(pingo);

		// SimpleGame.bolasManager.setObjetivo(pingo);
		publisher.moveCharacther(pingo);

		lastDropTime = TimeUtils.nanoTime();
	}

	private void spawnNuvem() {
		Rectangle rect = new Rectangle();
		rect.x = Constantes.LARGURA / 2 - Constantes.GAMEOBJECT_DIM / 2; //
		rect.y = Constantes.ALTURA - Constantes.GAMEOBJECT_DIM - 20;
		rect.width = Constantes.GAMEOBJECT_DIM;
		rect.height = Constantes.GAMEOBJECT_DIM;
		Texture nuvemImage = ProxyAssetManager.getInstance().getTexture("nuvemImage");
		nuvem = new Nuvem(nuvemImage, rect);

	}

	public void draw(SpriteBatch batch) {
		for (Movel movel : chuvaObjects) {
			movel.draw(batch);
		}
		nuvem.draw(batch);
	}

	public void update() {
		// Adiciona uma nova gota de chuva quando o tempo passa de 1 segundo
		if (TimeUtils.nanoTime() - lastDropTime > Constantes.TIME_TO_RESPAWN) {
			this.spawnPingo();
		}

		// Percorre os pingos para atualiza��o.
		Iterator<Movel> iter = this.chuvaObjects.iterator();
		while (iter.hasNext()) {
			Movel pingo = iter.next();
			pingo.update();

			// remover gotas que sa�ram da tela
			// ou que bateram no balde ou morreram.
			if ((pingo.getRect().y < 0) || !pingo.isAlive()) { // saiu
				iter.remove();
				pingo.setAlive(false);
			} else if (pingo.getRect().overlaps(Balde.getInstance().getRect())) {
				// bateu no balde
				ProxyAssetManager.getInstance().getSound("dropSound").play();
				Balde.getInstance().pontos++;
				iter.remove();
				pingo.setAlive(false);
			}
		}

		// Atualiza a nuvem
		nuvem.update();

	}

	public void dispose() {
	}

}