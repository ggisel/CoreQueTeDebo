package ar.com.quetedebo.core;

import java.util.ArrayList;
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
	
	public List<String> getPaymentMethods() {
		List<String> paymentMethodsSelectors = new ArrayList<String>();
		for(PaymentMethod paymentMethod : paymentMethodFactory.getPaymentMethods()) {
			paymentMethodsSelectors.add(paymentMethod.getName());
		}
		return paymentMethodsSelectors;
	}

	private PaymentMethod getPaymentMethodFromFactory(String methodName) {
		return paymentMethodFactory.getPaymentMethod(methodName);
	}
	
	


}
