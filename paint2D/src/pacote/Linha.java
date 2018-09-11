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
import mdmatrakas.compGrafica.model.util.ManipuladorLinha;
import mdmatrakas.compGrafica.transformacao.Transformacao;
import mdmatrakas.compGrafica.util.DrawUtil;

@XmlRootElement(name = "LINHA")
@XmlType(name = "LINHA", factoryMethod = "getNewInstance")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Linha implements Modelo {
	protected Ponto2D inicio;
	protected Ponto2D fim;

	protected Ponto2D inicioT;
	protected Ponto2D fimT;

	protected Transformacao t = Transformacao.getIdentidade();

	public Linha() {
		inicio = new Ponto2D(0, 0);
		fim = new Ponto2D(0, 0);
		inicioT = new Ponto2D(0, 0);
		fimT = new Ponto2D(0, 0);
	}

	public Linha(Ponto2D inicio, Ponto2D fim) {
		this.inicio = inicio;
		this.fim = fim;
		this.inicioT = new Ponto2D(inicio);
		this.fimT = new Ponto2D(fim);
	}

	@Override
	public String toString() {
		return "Linha [" + inicio + ", " + fim + " transformação=" + t + "]";
	}

	public static Linha getNewInstance() {
		return new Linha();
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
		inicioT.setCoordHom(p[0]);
		fimT.setCoordHom(p[1]);
	}

	@XmlElement(name="INICIO", type=Ponto2D.class)
	public Ponto2D getInicio() {
		return inicio;
	}
	
	public void setInicio(Ponto2D inicio){
		this.inicio = inicio;
		aplicarTransformacao();
	}
	
	@XmlElement(name="FIM", type=Ponto2D.class)
	public Ponto2D getFim() {
		return fim;
	}

	public void setFim(Ponto2D fim){
		this.fim = fim;
		aplicarTransformacao();
	}

	@XmlTransient
	public Ponto2D getCentro() {
		return new Ponto2D((inicioT.getX() + fimT.getX()) / 2, (inicioT.getY() + fimT.getY()) / 2);
	}

	@XmlTransient
	public float getMaxX() {
		return Math.max(inicioT.getX(), fimT.getX());
	}

	@XmlTransient
	public float getMinX() {
		return Math.min(inicioT.getX(), fimT.getX());
	}

	@XmlTransient
	public float getMaxY() {
		return Math.max(inicioT.getY(), fimT.getY());
	}

	@XmlTransient
	public float getMinY() {
		return Math.min(inicioT.getY(), fimT.getY());
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
		l.add(inicio);
		l.add(fim);
		return l;
	}

	public void setListPontos(List<Ponto2D> pontos) throws IllegalArgumentException {
		if (pontos.size() != 2)
			throw new IllegalArgumentException("Número do pontos inválido");

		inicio = pontos.get(0);
		fim = pontos.get(1);
		
		aplicarTransformacao();
	}

	@XmlTransient
	public float[][] getPontos2D() {
		float[][] v = new float[2][2];
		v[0] = inicio.getCoord2D();
		v[1] = fim.getCoord2D();
		return v;
	}

	@XmlTransient
	public float[][] getPontosCoordHom() {
		float[][] v = new float[2][3];
		v[0] = inicio.getCoordHom();
		v[1] = fim.getCoordHom();
		return v;
	}

	public void setPontos2D(float[][] v)  throws IllegalArgumentException {
		if(v.length != 2 && v[0].length != 2)
			throw new IllegalArgumentException("Número de coordenadas inválido");
		
		inicio.setCoord2D(v[0]);
		fim.setCoord2D(v[1]);
		aplicarTransformacao();
	}

	public void setPontosCoordHom(float[][] v)  throws IllegalArgumentException {
		if(v.length != 2 && v[0].length != 3)
			throw new IllegalArgumentException("Número de coordenadas inválido");
		
		inicio.setCoordHom(v[0]);
		fim.setCoordHom(v[1]);
		aplicarTransformacao();
	}
	public void desenhar(DrawUtil du) {
		du.drawLine(inicioT, fimT);
	}
	public Manipulador criarManipulador() {
		return new ManipuladorLinha(this);
	}

}
