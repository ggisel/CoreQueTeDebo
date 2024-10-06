package ar.com.quetedebo.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class QueTeDebo implements Observer {
	private List<Observer> observers = new ArrayList<>();
	private DataLoader dataLoader = new DataLoader();
	private PayDebts payDebts;

	public QueTeDebo() {
		this.payDebts = new PayDebts();
		this.payDebts.addObserver(this);
	}

	public void pay(List<Debt> debts) {
		this.payDebts.pay(debts);
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
	
	public void subscribe(Observer observer) {
        observers.add(observer);
    }

	@Override
	public void update(Observable arg0, Object arg1) {
		notifyObservers("pay");
	}
	
	private void notifyObservers(String message) {
		observers.forEach(observer -> observer.update(payDebts, message));
    }

}
