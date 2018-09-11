package mdmatrakas.compGrafica.model.util;

import mdmatrakas.compGrafica.model.Bezier;
import mdmatrakas.compGrafica.model.Ponto2D;

public class ManipuladorBezier implements Manipulador {

	private Bezier bezier;
	private int click;
	private boolean finalizado = false;
	private boolean cancelado = false;

	public ManipuladorBezier(Bezier bezier) {
		this.bezier = bezier;
		this.click = 0;
	}

	public void click(float x, float y) {
		if (finalizado || cancelado)
			return;
		switch(click){
		case 0:
			bezier.setA(new Ponto2D(x, y));
			bezier.setB(new Ponto2D(x, y));
			bezier.setC(new Ponto2D(x, y));
			bezier.setD(new Ponto2D(x, y));
			click++;
			break;
		case 1:
			bezier.setB(new Ponto2D(x, y));
			bezier.setC(new Ponto2D(x, y));
			bezier.setD(new Ponto2D(x, y));
			click++;
			break;
		case 2:
			bezier.setC(new Ponto2D(x, y));
			bezier.setD(new Ponto2D(x, y));
			click++;
			break;
		case 3:
			bezier.setD(new Ponto2D(x, y));
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
			bezier.setB(new Ponto2D(x, y));
			bezier.setC(new Ponto2D(x, y));
			bezier.setD(new Ponto2D(x, y));
			break;
		case 2:
			bezier.setC(new Ponto2D(x, y));
			bezier.setD(new Ponto2D(x, y));
			break;
		case 3:
			bezier.setD(new Ponto2D(x, y));
		}  
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public boolean isCancelado() {
		return cancelado;
	}

}
