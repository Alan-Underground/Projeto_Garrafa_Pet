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
import mdmatrakas.compGrafica.model.util.ManipuladorTriangulo;
import mdmatrakas.compGrafica.transformacao.Transformacao;
import mdmatrakas.compGrafica.util.DrawUtil;

/* CGDemo is a companion of the textbook

L. Ammeraal and K. Zhang, Computer Graphics for Java Programmers, 
2nd Edition, Wiley, 2006.

Copyright (C) 2006  Janis Schubert, Kang Zhang, Leen Ammeraal 

This program is free software; you can redistribute it and/or 
modify it under the terms of the GNU General Public License as 
published by the Free Software Foundation; either version 2 of 
the License, or (at your option) any later version. 

This program is distributed in the hope that it will be useful, 
but WITHOUT ANY WARRANTY; without even the implied warranty of 
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  
See the GNU General Public License for more details.  

You should have received a copy of the GNU General Public 
License along with this program; if not, write to 
the Free Software Foundation, Inc., 51 Franklin Street, 
Fifth Floor, Boston, MA  02110-1301, USA. 
*/

/**
 * Class to store a Triangulo; vertices in logical coordinates.
 * 
 * Uses: Ponto2D.
 * 
 * @author L. Ammeraal and K. Zhang; Adapted by MDMatrakas
 *
 *
 */
@XmlRootElement(name = "TRIANGULO")
@XmlType(name = "TRIANGULO", factoryMethod = "getNewInstance")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Triangulo implements Modelo {
	protected Ponto2D a;
	protected Ponto2D b;
	protected Ponto2D c;
	
	protected Ponto2D aT;
	protected Ponto2D bT;
	protected Ponto2D cT;
	
	protected Transformacao t = Transformacao.getIdentidade();

	public Triangulo() {
		a = new Ponto2D(0, 0);
		b = new Ponto2D(0, 0);
		c = new Ponto2D(0, 0);
		this.aT = new Ponto2D(a);
		this.bT = new Ponto2D(b);
		this.cT = new Ponto2D(c);
	}

	public Triangulo(Ponto2D A, Ponto2D B, Ponto2D C) {
		this.setA(A);
		this.setB(B);
		this.setC(C);
		this.aT = new Ponto2D(a);
		this.bT = new Ponto2D(b);
		this.cT = new Ponto2D(c);
	}

	@Override
	public String toString() {
		return "Triangulo [A=" + a + ", B=" + b + ", C=" + c + " transformação=" + t + "]";
	}

	public static Triangulo getNewInstance() {
        return new Triangulo();
    }
	
	public float getArea() {
		return Math.abs(
				((aT.getX() - cT.getX()) * (bT.getY() - cT.getY()) - (aT.getY() - cT.getY()) * (bT.getX() - cT.getX())) / 2);
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

	@XmlElement(name="TRANSFORMACAO", type=Transformacao.class)
	public Transformacao getTransformacao() {
		return t;
	}

	private void aplicarTransformacao() {
		float[][] p = t.aplicar(getPontosCoordHom());
		aT.setCoordHom(p[0]);
		bT.setCoordHom(p[1]);
		cT.setCoordHom(p[2]);
	}

	public void setA(Ponto2D a) {
		this.a = a;
		aplicarTransformacao();
	}

	@XmlElement(name="A", type=Ponto2D.class)
	public Ponto2D getA() {
		return a;
	}

	public void setB(Ponto2D b) {
		this.b = b;
		aplicarTransformacao();
	}

	@XmlElement(name="B", type=Ponto2D.class)
	public Ponto2D getB() {
		return b;
	}

	public void setC(Ponto2D c) {
		this.c = c;
		aplicarTransformacao();
	}

	@XmlElement(name="C", type=Ponto2D.class)
	public Ponto2D getC() {
		return c;
	}

	@XmlTransient
	public Ponto2D getCentro() {
		return new Ponto2D((aT.getX()+bT.getX()+cT.getX())/3, (aT.getY()+bT.getY()+cT.getY())/3);
	}

	@XmlTransient
	public float getMaxX() {
		return Math.max(Math.max(aT.getX(), bT.getX()), cT.getX());
	}

	@XmlTransient
	public float getMinX() {
		return Math.min(Math.min(aT.getX(), bT.getX()), cT.getX());
	}

	@XmlTransient
	public float getMaxY() {
		return Math.max(Math.max(aT.getY(), bT.getY()), cT.getY());
	}

	@XmlTransient
	public float getMinY() {
		return Math.min(Math.min(aT.getY(), bT.getY()), cT.getY());
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
		return l;
	}

	public void setListPontos(List<Ponto2D> pontos) throws IllegalArgumentException {
		if(pontos.size() != 3)
			throw new IllegalArgumentException("Número do pontos inválido");
		
		a = pontos.get(0);
		b = pontos.get(1);
		c = pontos.get(2);
		aplicarTransformacao();
	}
	
	@XmlTransient
	public float[][] getPontos2D() {
		float[][] v = new float[3][2];
		v[0] = a.getCoord2D();
		v[1] = b.getCoord2D();
		v[2] = c.getCoord2D();
		return v;
	}

	@XmlTransient
	public float[][] getPontosCoordHom() {
		float[][] v = new float[3][3];
		v[0] = a.getCoordHom();
		v[1] = b.getCoordHom();
		v[2] = c.getCoordHom();
		return v;
	}

	public void setPontos2D(float[][] v) {
		if(v.length != 3 && v[0].length != 2)
			throw new IllegalArgumentException("Número de coordenadas inválido");
		
		a.setCoord2D(v[0]);
		b.setCoord2D(v[1]);
		c.setCoord2D(v[2]);
		aplicarTransformacao();
	}

	public void setPontosCoordHom(float[][] v) {
		if(v.length != 3 && v[0].length != 3)
			throw new IllegalArgumentException("Número de coordenadas inválido");
		
		a.setCoordHom(v[0]);
		b.setCoordHom(v[1]);
		c.setCoordHom(v[2]);
		aplicarTransformacao();
	}

	public void desenhar(DrawUtil du) {
		du.drawLine(aT, bT);
		du.drawLine(bT, cT);
		du.drawLine(aT, cT);
	}
	public Manipulador criarManipulador() {
		return new ManipuladorTriangulo(this);
	}

}