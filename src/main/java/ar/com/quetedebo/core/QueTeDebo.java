package ar.com.quetedebo.core;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import ar.com.quetedebo.core.remote.HistorialRemoteServer;

public class QueTeDebo extends Observable {
	private List<Debt> debts;
	private Payer payer;
	private HistorialRemoteServer historialRemoteService;

	public QueTeDebo(String extensionsPath, String dataPath) {
		this.debts = loadDebts(dataPath);
		payer = new Payer(extensionsPath,getDiscovererPaymentMethods(extensionsPath));
		try {
			historialRemoteService = new HistorialRemoteServer();
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
        
	}
	
	public List<String> getPaymentsMethods() {
		return payer.getPaymentMethods();
	}

	public void payRequest(String paymentMethod) {
		String paymentMethodName = payer.payDebtsWithPayment(debts, paymentMethod);

		setChanged();
        notifyObservers(paymentMethodName);
        
        // Notificación remota
        try {
			historialRemoteService.notifyObserverRemote(debts);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
        
        // FIXME ver como podemos limpiar las deudas
        debts.clear();
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

	private Map<String, PaymentMethodPlugin> getDiscovererPaymentMethods(String extensionsPath){
		return new Discoverer<>(extensionsPath).getPaymentMethods();
	}
	
}
