package ar.com.quetedebo.core;

import java.util.List;

import ar.com.quetedebo.factory.PaymentMethodFactory;
import ar.com.quetedebo.pm.PaymentMethod;

public class Payer {
	public PaymentMethodFactory paymentMethodFactory;

	public Payer(String extensionsPath) {
		this.paymentMethodFactory = new PaymentMethodFactory(extensionsPath);

	}


	public String processPayments(List<Debt> debts, String methodName) {
		String paymentMethodName = "";
		PaymentMethod paymentMethod = getPaymentMethodFromFactory(methodName);

		for(Debt debt : debts) {
			paymentMethodName = paymentMethod.processPayment(debt.getAddressPayment(), debt.getAmount());
		}

		return paymentMethodName;
	}

	private PaymentMethod getPaymentMethodFromFactory(String methodName) {
		return paymentMethodFactory.getPaymentMethod(methodName);
	}


}
