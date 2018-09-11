package udc.edu.psw;

public class RestauranteCaseiro {
	private int totalMesas; //Quantidade de mesas totais no restaurante
	private int qtdClientePorMesa[]; //Quantidade de cliente por mesa
	private int qtdBebidas[]; //Quantidade de bebidas por mesa
	private float precoPrato;
	private float precoBebida;
	private float totalVendasDia; //total que o restaurante recebeu em dinheiro no dia
	private int mesasOcupadas; //saber quantas mesas estão ocupadas para novos clientes
	
	public void custoBebida (float precoCusto) {
		precoBebida = precoCusto * 1.4F;
	}
	public void custoPrato (float precoCusto) {
		precoPrato = precoCusto * 1.25F;
	}

	
	
	public RestauranteCaseiro(int mesas) { //criando um objeto da classe, inicializando, não tem tipo nao tem return
		totalMesas = mesas;
	
		//Quando criamos vetores eles não existem.
		//tem qee inicializar os vetores em java para eles existirem.
		qtdClientePorMesa = new int[totalMesas];
		qtdBebidas = new int[totalMesas];
}
	
	
	//adiciona uma bebida na mesa    //esse metodo foi para outra classe
	//	public int adicionaBebida(int mesa) {
	//		qtdBebidas[mesa]++;
	//	return qtdBebidas[mesa];	
	//	}
	
	//adiciona um clicente  //foi para outra classe pq é coisa da mesa
	//public int adicionaCliente(int mesa) {
	//	if(qtdClientePorMesa[mesa] ==0 ) //para saber quantas mesmas estão ocupadas ou livres.
	//		mesasOcupadas++;
	//	qtdClientePorMesa[mesa]++;
	//	return qtdClientePorMesa[mesa];
	//	}

	
	//caclua o valor da conta de uma deeterminada mesa  //foi pra outra classe
	//public float calculaConta(int mesa) { //conta da mesa conforme numero de clientes e de bebidas.
	//	float total = qtdBebidas[mesa] * precoBebida;
	///	total += qtdClientePorMesa[mesa] * precoPrato;
		//parte 3 do exercicio da pra fazer de duas formas a primeira esta na linha abaixo
		//float totalDivido = qtdClientePorMesa[mesa] / total;
//		return total;	
	//	}
	//ou da pra fazer a parte 3 do exericio com um método novo
	//	public float calculaContaPorCliente(int mesa) {
	//	return calculaConta(mesa) / qtdClientePorMesa[mesa];
	//}
	
	//Fechar a conta de uma mesa
	public float fecharMesa(int mesa) {
		float valor = calculaConta(mesa);
		qtdBebidas[mesa] =0;
		qtdClientePorMesa[mesa] = 0;
		totalVendasDia += valor;
		mesasOcupadas--;
		return valor;
	}
	/// o 5 são atributos do restaurante, tem que ficar nessa classe.
	/// 6 a adiz q os atributos q estão na mesa, são da classe mesa, como atdBebidas[mesa] qtdClientePorMesa[mesa]
	/// 6 b tem que criar um vetor
	/// 6 c criar um novo metodo
	
}
