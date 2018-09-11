

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
 
public class Cliente {

    private Cliente() {}

    public static void main(String[] args) {

        se host = null, pega o host local no getRegistry
	String host = (args.length < 1) ? null : args[0];
	try {
	    Registry registry = LocateRegistry.getRegistry(host);
	    InterfaceRemota proxy = (InterfaceRemota) registry.lookup("Alo");
        String refRemota = proxy.toString();
        System.out.println("* Proxy...: " +
            refRemota.substring(refRemota.indexOf("endpoint")));
	    String resposta = proxy.digaAlo();
	    System.out.println("* Resposta: " + resposta);
	} catch (Exception e) {
	    System.err.println("! Erro no cliente: " + e.toString());
	    e.printStackTrace();
	}
    }
}

