package ar.com.quetedebo.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import ar.com.quetedebo.factory.DataLoader;

public class QueTeDebo extends Observable {
	private List<Debt> debts;
	private final Payer payer;

	public QueTeDebo(String extensionsPath, String dataPath) {
		this.debts = loadDebts(dataPath);
		payer = new Payer(extensionsPath);
	}

	public void payRequest(String paymentMethod) {
		List<Debt> porPagar=debts; //TODO
		String paymentMethodName = payer.processPayments(debts, paymentMethod);

		setChanged();
        notifyObservers(porPagar);
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
