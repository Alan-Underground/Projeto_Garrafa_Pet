package udc.edu.psw;

public class Hora {
	private byte hora; //atributo ou variavel de instancia.
	private byte minuto; //atributo ou variavel de instancia.
	
	public Hora(byte hora, byte minuto) {//construtor o escopo das variaveis hora e minuto aqui desse metodo tem escopo diferente
		//se quiser referenciar essa hora e essa data desse metodo, tem que usar this
	
	
	this.hora = validaHora(hora); 
	this.minuto = minuto;
		
	}
	//Polimorfismo
	public Hora() { //Construtor sobrecarregado (sobrecarga tem mudança de assinatura)
		if(hora> 23)
			hora=0;
		
		hora = 0;
		minuto =0;
	}
	
	
	private byte validaHora (byte hora) {
	if (hora>23)
		return 0;
	return hora;
	}
	
	 //metodo para acessar os valores ja que é privado
	public void setHora(byte hora) {
		this.hora = hora;
	}
	
	public byte getHora( byte hora) {
		return hora;
	}

	
public byte incrementaHora() {
	hora++;
	return  hora;
}
public byte incrementaMinuto() {
	minuto++;
	return minuto;
}
public void zerar () {
	hora =0;
	minuto = 0;
}
public String toSting() {
return	String.format("%02d:%02d",hora,minuto); //nao exibe na tela, so cria a string na memoria. 02 sao duas casas e completa com zero antes se for um digito

	}

}//objeto tem quer ser valido, atributos tem que ser valido
//no caso da hora a hora tem que estar entre 00 e 23  e o minuto entra 00 e 59
//na hora de escrever os métodos tem que assumir valores validos
//exemplo se escrever uma hora acima de 23  considerar 23
//exercicio 8 criar classe data/hora objeto que é composto por uma data e uma hora.


