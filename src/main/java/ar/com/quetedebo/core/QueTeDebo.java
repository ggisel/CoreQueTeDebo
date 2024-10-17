package ar.com.quetedebo.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import ar.com.quetedebo.factory.DataLoader;
import ar.com.quetedebo.factory.PaymentMethodFactory;
import ar.com.quetedebo.pm.PaymentMethod;
import ar.com.quetedebo.pm.PaymentObserver;

public class QueTeDebo extends Observable implements PaymentObserver {
	private List<Debt> debts;
	private Map<String, Debt> pendingPayments = new HashMap<>();
	private Payer payer = new Payer();
	private PaymentMethod paymentMethod;

	public QueTeDebo(String extensionsPath, String dataPath) {
		this.debts = loadDebts(dataPath);
		PaymentMethodFactory paymentMethodFactory = new PaymentMethodFactory(extensionsPath);
		paymentMethod = paymentMethodFactory.getPaymentMethod();
		paymentMethod.subscribe(this);
	}

	public void pay() {
		Map<String, Debt> processedPayments = payer.processPayments(debts, paymentMethod);
		
		pendingPayments.putAll(processedPayments);

		debts.clear();

		setChanged();
		notifyObservers("pay");
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
	
	public List<Debt> getPendingPayments() {
		return new ArrayList<>(pendingPayments.values());
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

	public void onPaymentProcessed(String paymentId) {
		if (pendingPayments.containsKey(paymentId)) {
			pendingPayments.remove(paymentId);
		}

		setChanged();
		notifyObservers("payment_processed");
	}
}
