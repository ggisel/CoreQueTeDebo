package ar.com.quetedebo.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class QueTeDebo extends Observable {
	private DataLoader dataLoader = new DataLoader();

	public QueTeDebo() {

	}

	public void pay(List<Debt> debts) {
		setChanged();
        notifyObservers("pay");
	}

	public List<Debt> getDebts() {
		List<Debt> debts = new ArrayList<>();
		try {
			debts = this.dataLoader.loadDataFromJson(Debt.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return debts;
	}

}
