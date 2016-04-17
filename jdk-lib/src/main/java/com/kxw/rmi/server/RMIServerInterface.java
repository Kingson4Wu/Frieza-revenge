package com.kxw.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIServerInterface extends Remote{
	
	public double  getPosibility(String kxw) throws RemoteException;
	
	
	
}
