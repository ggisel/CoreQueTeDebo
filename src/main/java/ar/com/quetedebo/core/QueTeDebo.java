package ar.com.quetedebo.core;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import ar.com.quetedebo.core.remote.HistorialRemoteServer;
import ar.com.quetedebo.factory.DataLoader;

public class QueTeDebo extends Observable {
	private List<Debt> debts;
	private Payer payer;
	private HistorialRemoteServer historialRemoteService;

	public QueTeDebo(String extensionsPath, String dataPath) {
		this.debts = loadDebts(dataPath);
		payer = new Payer(extensionsPath);
		try {
			historialRemoteService = new HistorialRemoteServer();
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
        
	}
	
	public List<String> getPaymentsMethod() {
		return payer.getPaymentMethods();
	}

	public void payRequest(String paymentMethod) {
		List<Debt> porPagar=debts; //TODO
		String paymentMethodName = payer.processPayments(debts, paymentMethod);

		setChanged();
        notifyObservers(paymentMethodName);
        
        // Notificación remota
        try {
			historialRemoteService.notifyObserverRemote(debts);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void addDebt(Debt debt) {
		debts.add(debt);
		setChanged();
		notifyObservers("add");
	}
	
	public void removeDebt(Debt debt) {
		debts.remove(debt);
		setChanged();
		notifyObservers("remove");
	}
	
	public List<Debt> getDebts() {
		return debts;
	}
	
	public List<Debt> loadDebts(String dataPath) {
		DataLoader dataLoader = new DataLoader(dataPath);
		List<Debt> debts = new ArrayList<>();
		try {
			debts = dataLoader.loadDataFromJson(Debt.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return debts;
	}
}
