package ar.com.quetedebo.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import ar.com.quetedebo.core.config.Config;
import ar.com.quetedebo.core.model.Debt;
import ar.com.quetedebo.factory.DataLoader;
import ar.com.quetedebo.factory.QueTeDeboFactory;
import ar.com.quetedebo.pm.PaymentMethod;

public class QueTeDeboService {
	private DataLoader dataLoader = new DataLoader(Config.DATA_LOADER);
	private Set<PaymentMethod> paymentsMethods;
	
	public QueTeDeboService() {
		QueTeDeboFactory paymentMethodFactory = new QueTeDeboFactory();
		paymentsMethods = (Set<PaymentMethod>) paymentMethodFactory.getPaymentsMethods();
	}
	
	public String processPay(List<Debt> debts) {
		String metodo = "";
		
		// Por el momento solo va a tener un metodo de pago, dejamos que seleccione el metodo de pago en US3
		if(paymentsMethods.size() == 1) {
			Iterator<PaymentMethod> iterator = paymentsMethods.iterator();
	        if (iterator.hasNext()) {
	            PaymentMethod paymentMethod = iterator.next();
	            
	            for(Debt debt : debts) {
	            	metodo = paymentMethod.processPayment(debt.getAddressPayment(), debt.getAmount());
	            }
	        }
		}

		return metodo;
	}
	
	public List<Debt> loadDebts() {
		List<Debt> debts = new ArrayList<>();
		try {
			debts = dataLoader.loadDataFromJson(Debt.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return debts;
	}
}
