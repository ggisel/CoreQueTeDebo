package ar.com.quetedebo.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import ar.com.quetedebo.pm.PaymentMethodFactory;
import ar.com.quetedebo.pm.PaymentMethodPlugin;

public class QueTeDebo extends Observable {
	private List<Debt> debts;
	private Map<String, PaymentMethodPlugin> paymentMethods;
	private Payer payer;

	public QueTeDebo(String extensionsPath, String dataPath) {
		this.debts = loadDebts(dataPath);
		this.paymentMethods= getCreatedPaymentMethods(extensionsPath);
		this.payer = new Payer(extensionsPath);
	}

	public List<String> getPaymentsMethods() {
        return new ArrayList<>(paymentMethods.keySet());
	}

	public void payRequest(String paymentMethod) {
		String paymentMethodName = payer.payDebtsWithPayment(debts, this.paymentMethods.get(paymentMethod));
		QueTeDeboDTO data = new QueTeDeboDTO("pay", new ArrayList<Debt>(debts), paymentMethodName);
		setChanged();
        notifyObservers(data);

        debts.clear();
	}

	public void addDebt(Debt debt) {
		debts.add(debt);
		QueTeDeboDTO data = new QueTeDeboDTO("add", new ArrayList<Debt>(debts));
		setChanged();
		notifyObservers(data);
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

	private static Map<String, PaymentMethodPlugin> getCreatedPaymentMethods(String extensionsPath) {
		return new PaymentMethodFactory(extensionsPath).createPaymentMethods();
	}
}
