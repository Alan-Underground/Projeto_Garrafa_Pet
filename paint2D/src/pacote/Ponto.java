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
import mdmatrakas.compGrafica.model.util.ManipuladorPonto;
import mdmatrakas.compGrafica.transformacao.Transformacao;
import mdmatrakas.compGrafica.util.DrawUtil;

@XmlRootElement(name = "PONTO")
@XmlType(name = "PONTO", factoryMethod = "getNewInstance")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Ponto implements Modelo {

	protected Ponto2D p;

	protected Ponto2D pT;

	protected Transformacao t = Transformacao.getIdentidade();

	public Ponto() {
		p = new Ponto2D(0, 0);
		pT = new Ponto2D(0, 0);
	}

	public Ponto(Ponto2D p) {
		this.p = p;
		this.pT = new Ponto2D(p);
	}

	@Override
	public String toString() {
		return "Ponto [" + p + " transformação=" + t + "]";
	}

	public static Ponto getNewInstance() {
        return new Ponto();
    }

	@XmlElement(name = "TRANSFORMACAO", type = Transformacao.class)
	public Transformacao getTransformacao() {
		return t;
	}

	public void setTransformacao(Transformacao t) {
		this.t = t;
		aplicarTransformacao();
	}

	public void comporTransformacao(Transformacao t) {
		if (this.t == null) {
			this.t = Transformacao.getIdentidade();
		}
		this.t.compor(t);
		aplicarTransformacao();
	}

	private void aplicarTransformacao() {
		float[][] p = t.aplicar(getPontosCoordHom());
		pT.setCoordHom(p[0]);
	}

	@XmlElement(name = "COORDENADAS", type = Ponto2D.class)
	public Ponto2D getInicio() {
		return p;
	}

	public void setPonto(Ponto2D p) {
		this.p = p;
		aplicarTransformacao();
	}

	public void setPonto(float x, float y) {
		this.p.setX(x);
		this.p.setY(y);
		aplicarTransformacao();
	}

	public void setX(float x) {
		this.p.setX(x);
		aplicarTransformacao();
	}

	public void setY(float y) {
		this.p.setY(y);
		aplicarTransformacao();
	}

	@XmlTransient
	public Ponto2D getCentro() {
		return pT;
	}

	@XmlTransient
	public float getMaxX() {
		return pT.getX();
	}

	@XmlTransient
	public float getMinX() {
		return pT.getX();
	}

	@XmlTransient
	public float getMaxY() {
		return pT.getY();
	}

	@XmlTransient
	public float getMinY() {
		return pT.getY();
	}

	@XmlTransient
	public float getLargura() {
		return 0;
	}

	@XmlTransient
	public float getAltura() {
		return 0;
	}

	@XmlTransient
	public List<Ponto2D> getListPontos() {
		List<Ponto2D> l = new ArrayList<Ponto2D>();
		l.add(p);
		return l;
	}

	@XmlTransient
	public float[][] getPontos2D() {
		float[][] v = new float[1][2];
		v[0] = p.getCoord2D();
		return v;
	}

	@XmlTransient
	public float[][] getPontosCoordHom() {
		float[][] v = new float[1][3];
		v[0] = p.getCoordHom();
		return v;
	}

	public void setListPontos(List<Ponto2D> pontos) throws IllegalArgumentException {
		if (pontos.size() != 1)
			throw new IllegalArgumentException("Número do pontos inválido");

		p = pontos.get(0);

		aplicarTransformacao();
	}

	public void setPontos2D(float[][] v) throws IllegalArgumentException {
		if (v.length != 1 && v[0].length != 2)
			throw new IllegalArgumentException("Número de coordenadas inválido");

		p.setCoord2D(v[0]);
		aplicarTransformacao();
	}

	public void setPontosCoordHom(float[][] v) throws IllegalArgumentException {
		if (v.length != 1 && v[0].length != 3)
			throw new IllegalArgumentException("Número de coordenadas inválido");

		p.setCoordHom(v[0]);
		aplicarTransformacao();
	}

	public void desenhar(DrawUtil du) {
		du.putPixel(p);
	}

	public Manipulador criarManipulador() {
		return new ManipuladorPonto(this);
	}

}
