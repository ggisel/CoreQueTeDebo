package ar.com.quetedebo.core;

import java.util.List;

import ar.com.quetedebo.pm.PaymentMethod;

public class Payer {

	public String processPayments(List<Debt> debts, PaymentMethod paymentMethod) {
		String paymentMethodName = "";
		
		for(Debt debt : debts) {
			paymentMethodName = paymentMethod.processPayment(debt.getAddressPayment(), debt.getAmount());
        }

		return paymentMethodName;
	}
}
