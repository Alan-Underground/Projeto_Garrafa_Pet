package pacote;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import mdmatrakas.compGrafica.model.util.Manipulador;
import mdmatrakas.compGrafica.model.util.ManipuladorBezier;
import mdmatrakas.compGrafica.transformacao.Transformacao;
import mdmatrakas.compGrafica.util.DrawUtil;

@XmlRootElement(name = "BEZIER")
@XmlType(name = "BEZIER", factoryMethod = "getNewInstance")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Bezier implements Modelo {
	protected Ponto2D a;
	protected Ponto2D b;
	protected Ponto2D c;
	protected Ponto2D d;

	protected Ponto2D aT;
	protected Ponto2D bT;
	protected Ponto2D cT;
	protected Ponto2D dT;

	protected Transformacao t = Transformacao.getIdentidade();

	public Bezier(Ponto2D a, Ponto2D b, Ponto2D c, Ponto2D d) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.aT = new Ponto2D(a);
		this.bT = new Ponto2D(b);
		this.cT = new Ponto2D(c);
		this.dT = new Ponto2D(d);
	}

	public Bezier() {
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
		return "Bezier [a=" + a + ", b=" + b + ", c=" + c + ", d=" + d + " transformação=" + t + "]";
	}

	public static Bezier getNewInstance() {
		return new Bezier();
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
		bezierRec(du, aT, bT, cT, dT);
	}

	public Manipulador criarManipulador() {
		return new ManipuladorBezier(this);
	}

	private Ponto2D middle(Ponto2D p, Ponto2D q) {
		return new Ponto2D((p.getX() + q.getX()) / 2, (p.getY() + q.getY()) / 2);
	}

	private void bezierRec(DrawUtil du, Ponto2D p0, Ponto2D p1, Ponto2D p2, Ponto2D p3) {
		if (Math.abs(p0.getX() - p3.getX()) <= du.getPixelSize()
				&& Math.abs(p0.getY() - p3.getY()) <= du.getPixelSize())
			du.putPixel(p0);
		else {
			Ponto2D a = middle(p0, p1);
			Ponto2D b = middle(p3, p2);
			Ponto2D c = middle(p1, p2);
			Ponto2D a1 = middle(a, c);
			Ponto2D b1 = middle(b, c);
			Ponto2D c1 = middle(a1, b1);

			bezierRec(du, p0, a, a1, c1);
			bezierRec(du, c1, b1, b, p3);
		}
	}

	// void bezierNRec(Graphics g, Point2D[] p)
	// {
	// int n = 200;
	// float dt = 1F / n;
	// float x = p[0].getX();
	// float y = p[0].getY();
	// float x0, y0;
	//
	// for(int i = 1; i <= n; i++)
	// {
	// float t = i * dt;
	// float u = 1 - t;
	// float tuTriple = 3 * t * u;
	// float c0 = u * u * u;
	// float c1 = tuTriple * u;
	// float c2 = tuTriple * t;
	// float c3 = t * t * t;
	//
	// x0 = x;
	// y0 = y;
	//
	// x = c0 * p[0].getX() + c1 * p[1].getX() + c2 * p[2].getX() + c3 *
	// p[3].getX();
	// y = c0 * p[0].getY() + c1 * p[1].getY() + c2 * p[2].getY() + c3 *
	// p[3].getY();
	//
	// g.drawLine(iX(x0), iY(y0), iX(x), iY(y));
	// }
	// }
}
