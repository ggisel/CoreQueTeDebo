package ar.com.quetedebo.pm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.com.quetedebo.core.Discoverer;

public class PaymentMethodRegistry {
	private static final Map<String, PaymentMethodPlugin> registry = new HashMap<>();

	public static void register(String name, PaymentMethodPlugin method) {
		registry.put(name, method);
	}

	public static PaymentMethodPlugin get(String name) {
		return registry.get(name);
	}

	public static void loadPaymentMethods(String extensionsPath) {
		List<PaymentMethodPlugin> paymentMethodPlugins = new Discoverer<PaymentMethodPlugin>(extensionsPath)
                .buildExtensions(PaymentMethodPlugin.class);
		
		for (PaymentMethodPlugin paymentMethodPlugin : paymentMethodPlugins) {
            String methodName = paymentMethodPlugin.getName();
            register(methodName, paymentMethodPlugin);
        }
	}
	
	public static List<String> getAllMethodNames() {
        return new ArrayList<>(registry.keySet());
    }

}
