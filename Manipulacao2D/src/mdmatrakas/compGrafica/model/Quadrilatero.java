package mdmatrakas.compGrafica.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import mdmatrakas.compGrafica.model.util.Manipulador;
import mdmatrakas.compGrafica.model.util.ManipuladorQuadrilatero;
import mdmatrakas.compGrafica.transformacao.Transformacao;
import mdmatrakas.compGrafica.util.DrawUtil;

@XmlRootElement(name = "QUADRILATERO")
@XmlType(name = "QUADRILATERO", factoryMethod = "getNewInstance")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Quadrilatero implements Modelo {

	protected Ponto2D a;
	protected Ponto2D b;
	protected Ponto2D c;
	protected Ponto2D d;

	protected Ponto2D aT;
	protected Ponto2D bT;
	protected Ponto2D cT;
	protected Ponto2D dT;

	protected Transformacao t = Transformacao.getIdentidade();

	public Quadrilatero(Ponto2D a, Ponto2D b, Ponto2D c, Ponto2D d) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.aT = new Ponto2D(a);
		this.bT = new Ponto2D(b);
		this.cT = new Ponto2D(c);
		this.dT = new Ponto2D(d);
	}

	public Quadrilatero() {
		a = new Ponto2D();
		b = new Ponto2D();
		c = new Ponto2D();
		d = new Ponto2D();
		this.aT = new Ponto2D(a);
		this.bT = new Ponto2D(b);
		this.cT = new Ponto2D(c);
		this.dT = new Ponto2D(d);
	}

	@Override
	public String toString() {
		return "Retangulo [a=" + a + ", b=" + b + ", c=" + c + ", d=" + d + " transformação=" + t + "]";
	}

	public static Quadrilatero getNewInstance() {
		return new Quadrilatero();
	}

	@XmlElement(name="TRANSFORMACAO", type=Transformacao.class)
	public Transformacao getTransformacao() {
		return t;
	}

	public void setTransformacao(Transformacao t) {
		this.t = t;
		aplicarTransformacao();
	}

	public void comporTransformacao(Transformacao t) {
		if(this.t== null) {
			this.t= Transformacao.getIdentidade();
		}
		this.t.compor(t);
		aplicarTransformacao();
	}
	
	private void aplicarTransformacao() {
		float[][] p = t.aplicar(getPontosCoordHom());
		aT.setCoordHom(p[0]);
		bT.setCoordHom(p[1]);
		cT.setCoordHom(p[2]);
		dT.setCoordHom(p[3]);
	}

	@XmlElement(name = "A", type = Ponto2D.class)
	public Ponto2D getA() {
		return a;
	}

	public void setA(Ponto2D a) {
		this.a = a;
		aplicarTransformacao();
	}

	@XmlElement(name = "B", type = Ponto2D.class)
	public Ponto2D getB() {
		return b;
	}

	public void setB(Ponto2D b) {
		this.b = b;
		aplicarTransformacao();
	}

	@XmlElement(name = "C", type = Ponto2D.class)
	public Ponto2D getC() {
		return c;
	}

	public void setC(Ponto2D c) {
		this.c = c;
		aplicarTransformacao();
	}

	@XmlElement(name = "D", type = Ponto2D.class)
	public Ponto2D getD() {
		return d;
	}

	public void setD(Ponto2D d) {
		this.d = d;
		aplicarTransformacao();
	}

	@XmlTransient
	public Ponto2D getCentro() {
		return new Ponto2D((aT.getX() + bT.getX() + cT.getX() + dT.getX()) / 4,
				(aT.getY() + bT.getY() + cT.getY() + dT.getY()) / 4);
	}

	@XmlTransient
	public float getMaxX() {
		return Math.max(Math.max(aT.getX(), bT.getX()), Math.max(cT.getX(), dT.getX()));
	}

	@XmlTransient
	public float getMinX() {
		return Math.min(Math.min(aT.getX(), bT.getX()), Math.min(cT.getX(), dT.getX()));
	}

	@XmlTransient
	public float getMaxY() {
		return Math.max(Math.max(aT.getY(), bT.getY()), Math.max(cT.getY(), dT.getY()));
	}

	@XmlTransient
	public float getMinY() {
		return Math.min(Math.min(aT.getY(), bT.getY()), Math.min(cT.getY(), dT.getY()));
	}

	@XmlTransient
	public float getLargura() {
		return Math.abs(getMaxX() - getMinX());
	}

	@XmlTransient
	public float getAltura() {
		return Math.abs(getMaxY() - getMinY());
	}

	@XmlTransient
	public List<Ponto2D> getListPontos() {
		List<Ponto2D> l = new ArrayList<Ponto2D>();
		l.add(a);
		l.add(b);
		l.add(c);
		l.add(d);
		return l;
	}

	public void setListPontos(List<Ponto2D> pontos) throws IllegalArgumentException {
		if (pontos.size() != 4)
			throw new IllegalArgumentException("Número do pontos inválido");

		a = pontos.get(0);
		b = pontos.get(1);
		c = pontos.get(2);
		d = pontos.get(3);
		aplicarTransformacao();
	}

	@XmlTransient
	public float[][] getPontos2D() {
		float[][] v = new float[4][2];
		v[0] = a.getCoord2D();
		v[1] = b.getCoord2D();
		v[2] = c.getCoord2D();
		v[3] = d.getCoord2D();
		return v;
	}

	@XmlTransient
	public float[][] getPontosCoordHom() {
		float[][] v = new float[4][3];
		v[0] = a.getCoordHom();
		v[1] = b.getCoordHom();
		v[2] = c.getCoordHom();
		v[3] = d.getCoordHom();
		return v;
	}

	public void setPontos2D(float[][] v)  throws IllegalArgumentException {
		if(v.length != 4 && v[0].length != 2)
			throw new IllegalArgumentException("Número de coordenadas inválido");
		
		a.setCoord2D(v[0]);
		b.setCoord2D(v[1]);
		c.setCoord2D(v[2]);
		d.setCoord2D(v[3]);
		aplicarTransformacao();
	}

	public void setPontosCoordHom(float[][] v)  throws IllegalArgumentException {
		if(v.length != 4 && v[0].length != 3)
			throw new IllegalArgumentException("Número de coordenadas inválido");
		
		a.setCoordHom(v[0]);
		b.setCoordHom(v[1]);
		c.setCoordHom(v[2]);
		d.setCoordHom(v[3]);
		aplicarTransformacao();
	}

	public void desenhar(DrawUtil du) {
		du.drawLine(aT, bT);
		du.drawLine(bT, cT);
		du.drawLine(cT, dT);
		du.drawLine(dT, aT);
	}

	public Manipulador criarManipulador() {
		return new ManipuladorQuadrilatero(this);
	}


}
