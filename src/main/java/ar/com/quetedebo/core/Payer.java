package ar.com.quetedebo.core;

import java.util.List;

import ar.com.quetedebo.pm.PaymentMethodPlugin;

public class Payer {

	public Payer(String extensionsPath) {
	}

	public String payDebtsWithPayment(List<Debt> debts, PaymentMethodPlugin paymentMethod) {
		String paymentMethodName = "";

		for (Debt debt : debts) {
			paymentMethodName = paymentMethod.processPayment(debt.getAddressPayment(), debt.getAmount());
		}

		return paymentMethodName;
	}

}
