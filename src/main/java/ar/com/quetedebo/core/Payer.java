package ar.com.quetedebo.core;

import java.util.List;

import ar.com.quetedebo.pm.PaymentMethodPlugin;
import ar.com.quetedebo.pm.PaymentMethodRegistry;

public class Payer {

	public Payer(String extensionsPath) {
		PaymentMethodRegistry.loadPaymentMethods(extensionsPath);
	}

	public String processPayments(List<Debt> debts, String methodName) {
		String paymentMethodName = "";
		PaymentMethodPlugin paymentMethod = PaymentMethodRegistry.get(methodName);

		for(Debt debt : debts) {
			paymentMethodName = paymentMethod.processPayment(debt.getAddressPayment(), debt.getAmount());
		}

		return paymentMethodName;
	}
	
	public List<String> getPaymentMethods() {
		List<String> paymentMethodsSelectors = PaymentMethodRegistry.getAllMethodNames();
		return paymentMethodsSelectors;
	}

}
