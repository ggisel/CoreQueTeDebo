package ar.com.quetedebo.pm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.com.quetedebo.core.Discoverer;

public class PaymentMethodFactory {
	
	private Discoverer<PaymentMethodPlugin> discoverer;

	public PaymentMethodFactory(String extensionsPath) {
		discoverer = new Discoverer<>(extensionsPath);
	}

	public Map<String, PaymentMethodPlugin> createPaymentMethods() {
		Map<String, PaymentMethodPlugin> paymentMethods = new HashMap<>();
		List<PaymentMethodPlugin> paymentMethodPlugins = discoverer.buildExtensions(PaymentMethodPlugin.class);
		
		for (PaymentMethodPlugin paymentMethodPlugin : paymentMethodPlugins) {
            String methodName = paymentMethodPlugin.getName();
            paymentMethods.put(methodName, paymentMethodPlugin);
        }
		
		return paymentMethods;
	}

}
