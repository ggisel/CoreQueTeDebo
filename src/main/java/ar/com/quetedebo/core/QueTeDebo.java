package ar.com.quetedebo.core;

import java.util.List;
import java.util.Observable;

import ar.com.quetedebo.core.model.Debt;

public class QueTeDebo extends Observable {
	private List<Debt> debts;
	private QueTeDeboService queTeDeboService = new QueTeDeboService();

	public QueTeDebo() {
		this.debts = queTeDeboService.loadDebts();
	}

	public void pay() {
		String paymentMethod = queTeDeboService.processPay(debts);
		
		debts.clear();
		
		setChanged();
        notifyObservers(paymentMethod);
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
	
}
