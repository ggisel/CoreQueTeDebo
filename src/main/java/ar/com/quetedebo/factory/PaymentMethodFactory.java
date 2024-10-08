package ar.com.quetedebo.factory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import ar.com.quetedebo.core.config.Config;
import ar.com.quetedebo.pm.PaymentMethod;

public class PaymentMethodFactory {
	Discoverer<PaymentMethod> discoverer;
	
	public PaymentMethodFactory() {
		discoverer = new Discoverer<PaymentMethod>(Config.EXTENSIONS);
	}
	
	public Set<PaymentMethod> getPaymentsMethods()  {
		Set<PaymentMethod> payments = null;
		try {
			payments = discoverer.buildExtension(PaymentMethod.class);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return payments;
	}
}
