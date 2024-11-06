package ar.com.quetedebo.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ar.com.quetedebo.pm.PaymentMethodPlugin;
import ar.com.quetedebo.pm.PaymentMethodFactory;

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
