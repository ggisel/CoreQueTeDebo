package ar.com.quetedebo.core.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import ar.com.quetedebo.core.Debt;

public interface ObserverRemote extends Remote {

	void update(List<Debt> debts) throws RemoteException;
}
