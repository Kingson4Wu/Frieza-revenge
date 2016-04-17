package com.kxw.rmi.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RegisterWithRMIServer {

	public static void main(String[] args){
		
		try {
			RMIServerInterface obj =new RMIServerInterfaceImpl();
			Registry registry =LocateRegistry.getRegistry();
			registry.rebind("RMIServerInterfaceImpl", obj);
			
			System.out.println("Server:"+obj+"register!");
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
	
}
