package com.kxw.rmi.client;

import com.kxw.rmi.server.RMIServerInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;




public class RMIServerInterfaceClient {

	
	private RMIServerInterface rmiServer;
	
	
	
	public RMIServerInterfaceClient(){
		
		
		
		
		String host="localhost";
		try {
			Registry registry=LocateRegistry.getRegistry(host);
			rmiServer=(RMIServerInterface)registry.lookup("RMIServerInterfaceImpl");
			
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public void print() {
		try {
			System.out.println(rmiServer.getPosibility("kxw"));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) {
		
		RMIServerInterfaceClient client=new RMIServerInterfaceClient();
		
		
		client.print();
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
