package gameobjects;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import main.Constantes;

public class Nuvem extends Movel {	

	public Nuvem(Texture novoImage, Rectangle novoRect) {// , Sound novoSound, Music novoMusic, Rectangle novoRect) {
		super(novoImage, null, null, novoRect);	
		this.setVelocidade(200,0);
	}

	public void update() {		
			rect.setX(rect.getX() + velocidade.x * Gdx.graphics.getDeltaTime());
			float posx  = rect.getX() + velocidade.x * Gdx.graphics.getDeltaTime();
			//Se saiu da tela por algum lado ou pelo outro, volta.
			if ((posx + this.getRect().width> Constantes.LARGURA) || (posx < 0)){
				velocidade.x = -velocidade.x;
			}
			rect.setX(posx);
			mantemNuvemNaTela();
		}
	
	private void mantemNuvemNaTela() {
		// Verificar e ajustar a posição no eixo x
		if (rect.getX() < 0) {
			rect.setX(0);
		} else if (rect.getX() > Constantes.LARGURA - Constantes.GAMEOBJECT_DIM) {
			rect.setX(Constantes.LARGURA - Constantes.GAMEOBJECT_DIM);
		}

		// Verificar e ajustar a posição no eixo y
		if (rect.getY() < 0) {
			rect.setY(0);
		} else if (rect.getY() > Constantes.ALTURA - Constantes.GAMEOBJECT_DIM) {
			rect.setY(Constantes.ALTURA - Constantes.GAMEOBJECT_DIM);
		}
	}
}