package ar.com.quetedebo.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import ar.com.quetedebo.core.model.Debt;
import ar.com.quetedebo.factory.DataLoader;
import ar.com.quetedebo.factory.PaymentMethodFactory;
import ar.com.quetedebo.pm.PaymentMethod;

@SuppressWarnings("deprecation")
public class QueTeDebo extends Observable {
	private List<Debt> debts;
	private Payer payer = new Payer();
	private PaymentMethod paymentMethod;

	public QueTeDebo(String extensionsPath, String dataPath) {
		this.debts = loadDebts(dataPath);
		PaymentMethodFactory paymentMethodFactory = new PaymentMethodFactory(extensionsPath);
		paymentMethod = paymentMethodFactory.getPaymentMethod();
	}

	public void pay() {
		String paymentMethodName = payer.processPayments(debts, paymentMethod);
		
		debts.clear();
		
		setChanged();
        notifyObservers(paymentMethodName);
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
