package com.kxw.rmi.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

import org.omg.IOP.RMICustomMaxStreamFormat;


public class RMIServerInterfaceImpl extends UnicastRemoteObject implements
		RMIServerInterface, RMICustomMaxStreamFormat {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


private HashMap<String,Double> scores=new HashMap<String,Double>();

public  RMIServerInterfaceImpl() throws RemoteException{
	init();
}
	
protected void init() {
	scores.put("kxw", new Double(99));
	scores.put("Torres", new Double(95));
	scores.put("Messi", new Double(98));
	scores.put("Cristiano Ronaldo", new Double(98));
	
}
	
	

	@Override
	public double getPosibility(String kxw) throws RemoteException {
		// TODO Auto-generated method stub
		Double d=(Double)getPosibility(kxw);
		
		
		
		return d.doubleValue();
	}

}
