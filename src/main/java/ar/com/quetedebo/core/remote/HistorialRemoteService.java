package ar.com.quetedebo.core.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import ar.com.quetedebo.core.Debt;

public interface HistorialRemoteService extends Remote {

	void addObserverRemote(ObserverRemote observerRemote) throws RemoteException;

	void deleteObserverRemote(ObserverRemote observerRemote) throws RemoteException;
	
	void notifyObserverRemote(List<Debt> debts) throws RemoteException;
}
