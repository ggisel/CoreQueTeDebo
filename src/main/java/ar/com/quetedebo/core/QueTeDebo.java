package ar.com.quetedebo.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import ar.com.quetedebo.core.model.Debt;

public class QueTeDebo extends Observable {
	private List<Debt> debts;
	private QueTeDeboService queTeDeboService = new QueTeDeboService();

	public QueTeDebo() {
		this.debts = this.queTeDeboService.loadDebts();
	}

	public void pay(List<Debt> debts) {
		String metodo = this.queTeDeboService.processPay(debts);
		
		for (Debt debt: debts) {			
			if (!this.debts.remove(debt)) {
				//throw new Exception("Tried to pay unexisting debt: "+debt);
			}
			System.out.println("Paid " + debt);
		}
		
		setChanged();
        notifyObservers(metodo);
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
		List<Debt> newDebts =  new ArrayList<Debt>();
		debts.forEach(debt -> newDebts.add(new Debt(debt)));
		return newDebts;
	}
	
}
