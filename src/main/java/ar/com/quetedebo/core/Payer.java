package ar.com.quetedebo.core;

import java.util.List;

import ar.com.quetedebo.factory.PaymentMethodFactory;
import ar.com.quetedebo.pm.PaymentMethod;
import ar.com.quetedebo.storage.PaymentHistory;
import ar.com.quetedebo.storage.PaymentRecord;

public class Payer {
	public PaymentMethodFactory paymentMethodFactory;
	private final PaymentHistory paymentHistory;

	public Payer(String extensionsPath, String storageType) {
		this.paymentMethodFactory = new PaymentMethodFactory(extensionsPath);
		this.paymentHistory= new PaymentHistory(storageType);
	}


	public String processPayments(List<Debt> debts, String methodName) {
		String paymentMethodName = "";
		PaymentMethod paymentMethod = getPaymentMethodFromFactory(methodName);

		for(Debt debt : debts) {
			paymentMethodName = paymentMethod.processPayment(debt.getAddressPayment(), debt.getAmount());
        	paymentHistory.addRecord(new PaymentRecord(paymentMethodName, debt.getAmount(), debt.getAddressPayment()));
		}

		return paymentMethodName;
	}

	private PaymentMethod getPaymentMethodFromFactory(String methodName) {
		return paymentMethodFactory.getPaymentMethod(methodName);
	}


}
