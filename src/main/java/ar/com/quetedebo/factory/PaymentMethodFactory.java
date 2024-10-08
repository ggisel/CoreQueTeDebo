package ar.com.quetedebo.factory;

import ar.com.quetedebo.core.config.Config;
import ar.com.quetedebo.pm.PaymentMethod;

public class PaymentMethodFactory {
	Discoverer<PaymentMethod> discoverer;
	
	public PaymentMethodFactory() {
		discoverer = new Discoverer<PaymentMethod>(Config.EXTENSIONS);
	}
	
	public PaymentMethod getPaymentMethod()  {
		PaymentMethod paymentMethod = discoverer.buildExtension(PaymentMethod.class);
		
		return paymentMethod;
	}
}
