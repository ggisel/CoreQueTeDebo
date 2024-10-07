package ar.com.quetedebo.pm;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import ar.com.quetedebo.core.config.Config;
import ar.com.quetedebo.extensionfactory.Discoverer;

public class PaymentMethodFactory {
	
	
	public Set<PaymentMethod> getPaymentsMethods() throws IllegalArgumentException, InvocationTargetException, IOException {
		Discoverer<PaymentMethod> discoverer = new Discoverer<PaymentMethod>(Config.EXTENSIONS);

		return discoverer.loadImplementation(PaymentMethod.class);
	}
}
