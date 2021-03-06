package com.iks.rmiTutorial.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.iks.rmiTutorial.commons.Address;
import com.iks.rmiTutorial.commons.Greeter;
import com.iks.rmiTutorial.commons.Person;

public class RMIApplication {
	private final static String DEFAULT_NAME = "Willi";
	private final static String DEFAULT_HOST = "localhost";
	private final static int DEFAULT_PORT = 1099;

	public static void main(String[] args) {
		String name = (args.length > 0) ? args[0] : DEFAULT_NAME;
		String host = (args.length > 1) ? args[1] : DEFAULT_HOST;
		int port = (args.length > 2) ? Integer.parseInt(args[2]) : DEFAULT_PORT;
		
		Person person = createPerson(name);
// A security manager is only necessary when the classes are load over the network
//		if (System.getSecurityManager() == null) {
//            System.setSecurityManager(new SecurityManager());
//        }
		
        try {
        	System.out.println("Looking for registry on " + host + " port: " + port);
            Registry registry = LocateRegistry.getRegistry(host, port);
            Greeter stub = (Greeter) registry.lookup("Greeter");
            String response = stub.greetMe(person);
            System.out.println("response: " + response);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }

	}
	
	private static Person createPerson(String name) {
		return new Person (name, "Muster", new Address("Teststraße", "1000", "1111", "Taucha"));
	}

}
