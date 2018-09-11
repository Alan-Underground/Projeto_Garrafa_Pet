package mdmatrakas.compGrafica.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "PONTO3D", factoryMethod = "getNewInstance")
@XmlAccessorType(XmlAccessType.FIELD)
public class Ponto3D extends Ponto2D {
	@XmlElement(name="Z")
	private float z;

	public Ponto3D(float x, float y, float z) {
		super(x, y);
		this.setZ(z);
	}

	@Override
	public String toString() {
		return "Ponto3D [z=" + z + "]";
	}

	public Ponto3D() {
		super(0, 0);
		this.setZ(0);
	}

	public void setZ(float z) {
		this.z = z;
	}

	public float getZ() {
		return z;
	}
	
	public static Ponto3D getNewInstance() {
        return new Ponto3D();
    }

}
