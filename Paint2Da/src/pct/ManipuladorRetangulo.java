package mdmatrakas.compGrafica.model.util;

import mdmatrakas.compGrafica.model.Ponto2D;
import mdmatrakas.compGrafica.model.Retangulo;

public class ManipuladorRetangulo implements Manipulador {
	private Retangulo retangulo;
	private int click;
	private boolean finalizado = false;
	private boolean cancelado = false;

	public ManipuladorRetangulo(Retangulo retangulo) {
		this.retangulo = retangulo;
		this.click = 0;
	}

	public void click(float x, float y) {
		if (finalizado || cancelado)
			return;
		switch(click){
		case 0:
			retangulo.setA(new Ponto2D(x, y));
			retangulo.setB(new Ponto2D(x, y));
			retangulo.setC(new Ponto2D(x, y));
			retangulo.setD(new Ponto2D(x, y));
			click++;
			break;
		case 1:
			retangulo.setB(new Ponto2D(x, y));
			retangulo.setC(new Ponto2D(x, y));
			click++;
			break;
		case 2:
			Ponto2D c = new Ponto2D();
			Ponto2D d = new Ponto2D();
			
			calcPontosCeD(x, y, c, d);
			
			retangulo.setC(c);
			retangulo.setD(d);

			click++;
			finalizado = true;
		}  
	}
	
	private void calcPontosCeD(float x, float y, Ponto2D c, Ponto2D d) {
		float m = (retangulo.getB().getY() - retangulo.getA().getY()) / (retangulo.getB().getX() - retangulo.getA().getX());
		float h = y - m * x;
		
		float xc = (m / (m*m + 1) ) * (retangulo.getB().getX() / m + retangulo.getB().getY() - h);
		float xd = (m / (m*m + 1) ) * (retangulo.getA().getX() / m + retangulo.getA().getY() - h);
		
		float yc = (-1 / m) * (xc - retangulo.getB().getX()) + retangulo.getB().getY();
		float yd = (-1 / m) * (xd - retangulo.getA().getX()) + retangulo.getA().getY();
		c.setX(xc);
		c.setY(yc);
		d.setX(xd);
		d.setY(yd);
	}

	public void press(float x, float y) { }

	public void release(float x, float y) { }

	public void drag(float x, float y) { }

	public void move(float x, float y) {
		if (finalizado || cancelado)
			return;
		switch(click){
		case 1:
			retangulo.setB(new Ponto2D(x, y));
			retangulo.setC(new Ponto2D(x, y));
			break;
		case 2:
			Ponto2D c = new Ponto2D();
			Ponto2D d = new Ponto2D();
			
			calcPontosCeD(x, y, c, d);
			
			retangulo.setC(c);
			retangulo.setD(d);
		}  
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public boolean isCancelado() {
		return cancelado;
	}
}
