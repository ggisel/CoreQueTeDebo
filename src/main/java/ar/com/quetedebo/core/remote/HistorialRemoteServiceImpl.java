package ar.com.quetedebo.core.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import ar.com.quetedebo.core.Debt;

public class HistorialRemoteServiceImpl extends UnicastRemoteObject implements HistorialRemoteService {
	private static final long serialVersionUID = 236165515438913653L;
	private List<ObserverRemote> observers;

	public HistorialRemoteServiceImpl() throws RemoteException {
		observers = new ArrayList<>();
	}

	@Override
	public void addObserverRemote(ObserverRemote observerRemote) throws RemoteException {
		observers.add(observerRemote);
	}

	@Override
	public void deleteObserverRemote(ObserverRemote observerRemote) throws RemoteException {
		observers.remove(observerRemote);
	}

	@Override
	public void notifyObserverRemote(List<Debt> debts) throws RemoteException {
		for (ObserverRemote observerRemote : observers) {
			observerRemote.update(debts);
		}
	}
}
