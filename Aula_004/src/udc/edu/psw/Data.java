package udc.edu.psw;

public class Data {
	private byte dia;
	private byte mes;
	private short ano;

	public Data() {
		dia = 1;
		mes = 1;
		ano = 2000;
	}
	
	public Data(byte dia, byte mes, short ano) {
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;	
	}
	
	public String toString() {
		return String.format("%02d/%02d/%04d", dia,mes,ano);
	}
}

