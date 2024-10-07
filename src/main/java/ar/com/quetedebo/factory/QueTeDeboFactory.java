package ar.com.quetedebo.factory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import ar.com.quetedebo.core.config.Config;
import ar.com.quetedebo.pm.PaymentMethod;

public class QueTeDeboFactory {
	Discoverer<PaymentMethod> discoverer;
	
	public QueTeDeboFactory() {
		discoverer = new Discoverer<PaymentMethod>(Config.EXTENSIONS);
	}
	
	public Set<PaymentMethod> getPaymentsMethods() throws IllegalArgumentException, InvocationTargetException, IOException {
		return discoverer.buildExtension(PaymentMethod.class);
	}
}
