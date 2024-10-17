package ar.com.quetedebo.core;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import ar.com.quetedebo.core.remote.HistorialRemoteService;
import ar.com.quetedebo.core.remote.HistorialRemoteServiceImpl;
import ar.com.quetedebo.factory.DataLoader;

public class QueTeDebo extends Observable {
	private List<Debt> debts;
	private Payer payer;
	private HistorialRemoteService historialRemoteService;

	public QueTeDebo(String extensionsPath, String dataPath) {
		try {
			this.debts = loadDebts(dataPath);
			payer = new Payer(extensionsPath);
			historialRemoteService = new HistorialRemoteServiceImpl();

	        Registry registry;
			registry = LocateRegistry.createRegistry(1099);
			registry.rebind("HistorialRemoteService", historialRemoteService);
	        System.out.println("Servidor Core listo.");
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
