package ar.com.quetedebo.factory;

import ar.com.quetedebo.pm.PaymentMethod;

public class PaymentMethodFactory {
	Discoverer<PaymentMethod> discoverer;
	
	public PaymentMethodFactory(String extensionsPath) {
		discoverer = new Discoverer<PaymentMethod>(extensionsPath);
	}
	
	public PaymentMethod getPaymentMethod()  {
		PaymentMethod paymentMethod = discoverer.buildExtension(PaymentMethod.class);
		
		return paymentMethod;
	}
}
