package ar.com.quetedebo.core;

import java.util.List;
import java.util.Observable;

public class PayDebts extends Observable {

	public void pay(List<Debt> debts) {
		setChanged();
        notifyObservers("pay");
	}
}
