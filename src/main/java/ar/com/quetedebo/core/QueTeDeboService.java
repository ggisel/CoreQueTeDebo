package ar.com.quetedebo.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ar.com.quetedebo.core.config.Config;
import ar.com.quetedebo.core.model.Debt;
import ar.com.quetedebo.factory.DataLoader;
import ar.com.quetedebo.factory.PaymentMethodFactory;
import ar.com.quetedebo.pm.PaymentMethod;

public class QueTeDeboService {
	private DataLoader dataLoader = new DataLoader(Config.DATA_LOADER);
	private PaymentMethod paymentMethod;
	
	public QueTeDeboService() {
		PaymentMethodFactory paymentMethodFactory = new PaymentMethodFactory();
		paymentMethod = paymentMethodFactory.getPaymentMethod();
	}
	
	public String processPay(List<Debt> debts) {
		String metodo = "";
		
		for(Debt debt : debts) {
        	metodo = paymentMethod.processPayment(debt.getAddressPayment(), debt.getAmount());
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
