package ar.com.quetedebo.core;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import ar.com.quetedebo.core.model.Debt;
import ar.com.quetedebo.factory.DataLoader;
import ar.com.quetedebo.factory.QueTeDeboFactory;
import ar.com.quetedebo.pm.PaymentMethod;

public class QueTeDeboService {
	private QueTeDeboFactory paymentMethodFactory = new QueTeDeboFactory();
	private DataLoader dataLoader = new DataLoader();
	
	public String processPay(List<Debt> debts) {
		String metodo = "";
		try {
			Set<PaymentMethod> paymentsMethods = (Set<PaymentMethod>) paymentMethodFactory.getPaymentsMethods();
			
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
