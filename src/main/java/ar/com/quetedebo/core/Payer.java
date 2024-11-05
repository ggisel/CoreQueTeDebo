package ar.com.quetedebo.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ar.com.quetedebo.pm.PaymentMethodPlugin;
import ar.com.quetedebo.pm.PaymentMethodFactory;

public class Payer {
	private Map<String, PaymentMethodPlugin> paymentMethods;
	private PaymentMethodFactory paymentMethodFactory;

	public Payer(String extensionsPath) {
		paymentMethodFactory = new PaymentMethodFactory(extensionsPath);
		paymentMethods = paymentMethodFactory.createPaymentMethods();
	}

	public String payDebtsWithPayment(List<Debt> debts, String methodName) {
		String paymentMethodName = "";
		PaymentMethodPlugin paymentMethod = paymentMethods.get(methodName);

		for (Debt debt : debts) {
			paymentMethodName = paymentMethod.processPayment(debt.getAddressPayment(), debt.getAmount());
		}

		return paymentMethodName;
	}

	public List<String> getPaymentMethods() {
		List<String> paymentMethodsSelectors = new ArrayList<>(paymentMethods.keySet());
		return paymentMethodsSelectors;
	}

}
