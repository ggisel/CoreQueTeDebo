package ar.com.quetedebo.factory;

import ar.com.quetedebo.pm.PaymentMethod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PaymentMethodFactory {
	List<PaymentMethod> paymentMethods;

	public PaymentMethodFactory(String extensionsPath) {
		this.paymentMethods = getPaymentMethods(extensionsPath);
	}

	private static List<PaymentMethod> getPaymentMethods(String extensionsPath) {
		return Collections.unmodifiableList(new ArrayList<>(new Discoverer<PaymentMethod>(extensionsPath)
                .buildExtensions(PaymentMethod.class))
		);
	}

	public List<PaymentMethod> getPaymentMethods()  {
		return paymentMethods;
	}
	public PaymentMethod getPaymentMethod(String methodName) {
		return paymentMethods.stream()
				.filter(method -> method.getName().equalsIgnoreCase(methodName))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Método de pago no encontrado: " + methodName));
	}
}
