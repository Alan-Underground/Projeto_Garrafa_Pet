package mdmatrakas.compGrafica.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "PONTO2D")
@XmlType(name = "PONTO2D", factoryMethod = "getNewInstance")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Ponto2D {
	protected float[] vals = new float[3];
	
	public Ponto2D(Ponto2D p) {
		vals[0] = p.vals[0];
		vals[1] = p.vals[1];
		vals[2] = p.vals[2];
	}

	public Ponto2D(float x, float y) {
		vals[0] = x;
		vals[1] = y;
		vals[2] = 1;
	}

	public Ponto2D(float x, float y, float n) {
		vals[0] = x;
		vals[1] = y;
		vals[2] = n;
	}

	public Ponto2D() {
		vals[0] = 0;
		vals[1] = 0;
		vals[2] = 1;
	}

	@Override
	public String toString() {
		return "(" + vals[0] + "; " + vals[1] + "; " + vals[2] + ")";
	}

	public static Ponto2D getNewInstance() {
        return new Ponto2D();
    }

	public void setX(float x) {
		this.vals[0] = x;
	}

	@XmlElement(name="X")
	public float getX() {
		return vals[0];
	}

	public void setY(float y) {
		this.vals[1] = y;
	}

	@XmlElement(name="Y")
	public float getY() {
		return vals[1];
	}
	
	public void setN(float n) {
		this.vals[2] = n;
	}

	@XmlElement(name="N")
	public float getN() {
		return vals[2];
	}
	

	@XmlTransient
	public float[] getCoord2D() {
		float[] v = new float[2];
		v[0] = vals[0];
		v[1] = vals[1];
		return v;
	}

	@XmlTransient
	public float[] getCoordHom() {
		float[] v = new float[3];
		v[0] = vals[0];
		v[1] = vals[1];
		v[2] = vals[2];
		return v;
	}

	public void setCoord2D(float[] v) {
		if(v.length != 2)
			throw new IllegalArgumentException("Número de coordenadas inválido");
		vals[0] = v[0];
		vals[1] = v[1];
		vals[2] = 1;
	}

	public void setCoordHom(float[] v) {
		if(v.length != 3)
			throw new IllegalArgumentException("Número de coordenadas inválido");
		vals[0] = v[0];
		vals[1] = v[1];
		vals[2] = v[2];
	}


}
