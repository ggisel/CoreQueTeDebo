package ar.com.quetedebo.core;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;

import ar.com.quetedebo.pm.Discoverer;
import ar.com.quetedebo.pm.PaymentMethod;

public class PayDebts {

	public Set<PaymentMethod> getPaymentsMethods() throws IllegalArgumentException, InvocationTargetException, IOException {
		Discoverer<PaymentMethod> discoverer = new Discoverer<PaymentMethod>();

		return discoverer.loadImplementation(PaymentMethod.class);
	}

	public String pay(List<Debt> debts) {
		String metodo = "";
		try {
			Set<PaymentMethod> paymentMethods = (Set<PaymentMethod>) getPaymentsMethods();
			for (PaymentMethod paymentMethod : paymentMethods) {
	            metodo = paymentMethod.payWithPaymentMethod("", 0f);
	        }
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return metodo;
	}
}
