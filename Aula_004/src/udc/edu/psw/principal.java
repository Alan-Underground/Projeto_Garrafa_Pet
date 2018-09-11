package udc.edu.psw;

public class principal {
	public static void main (String[] args) {
		Hora hora = new Hora((byte)21,(byte)40);
		
		System.out.println(hora.toString());
		hora.incrementaHora();
		hora.incrementaMinuto();
		System.out.println(hora.toString());
		
		hora.setHora ((byte)100);
		System.out.println(hora.toString());
		
		Hora horaZero = new Hora();
		System.out.println(horaZero.toString());
		
		
		Data data = new Data((byte)1, (byte)8, (short)2018);
		System.out.println(data);
	}
}
//composição seus componentes deixam de existir  //forte
//agregação qdo destroi continua existindo. //fraco