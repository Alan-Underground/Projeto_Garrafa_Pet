package mdmatrakas.compGrafica.model.util;

import mdmatrakas.compGrafica.model.Ponto2D;
import mdmatrakas.compGrafica.model.Quadrilatero;

public class ManipuladorQuadrilatero implements Manipulador {
	private Quadrilatero quadrilatero;
	private int click;
	private boolean finalizado = false;
	private boolean cancelado = false;

	public ManipuladorQuadrilatero(Quadrilatero quadrilatero) {
		this.quadrilatero = quadrilatero;
		this.click = 0;
	}

	public void click(float x, float y) {
		if (finalizado || cancelado)
			return;
		switch(click){
		case 0:
			quadrilatero.setA(new Ponto2D(x, y));
			quadrilatero.setB(new Ponto2D(x, y));
			quadrilatero.setC(new Ponto2D(x, y));
			quadrilatero.setD(new Ponto2D(x, y));
			click++;
			break;
		case 1:
			quadrilatero.setB(new Ponto2D(x, y));
			quadrilatero.setC(new Ponto2D(x, y));
			quadrilatero.setD(new Ponto2D(x, y));
			click++;
			break;
		case 2:
			quadrilatero.setC(new Ponto2D(x, y));
			quadrilatero.setD(new Ponto2D(x, y));
			click++;
			break;
		case 3:
			quadrilatero.setD(new Ponto2D(x, y));
			click++;
			finalizado = true;
		}  
	}

	public void press(float x, float y) { }

	public void release(float x, float y) { }

	public void drag(float x, float y) { }

	public void move(float x, float y) {
		if (finalizado || cancelado)
			return;
		switch(click){
		case 1:
			quadrilatero.setB(new Ponto2D(x, y));
			quadrilatero.setC(new Ponto2D(x, y));
			quadrilatero.setD(new Ponto2D(x, y));
			break;
		case 2:
			quadrilatero.setC(new Ponto2D(x, y));
			quadrilatero.setD(new Ponto2D(x, y));
			break;
		case 3:
			quadrilatero.setD(new Ponto2D(x, y));
		}  
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public boolean isCancelado() {
		return cancelado;
	}

}
