package ar.com.quetedebo.core;

import java.util.ArrayList;
import java.util.List;

public class QueTeDeboDTO {
	private String action;
    private List<Debt> debts;
    private String paymentMethod;

    public QueTeDeboDTO(String action, List<Debt> debts) {
		this.action = action;
		this.debts = debts;
    }

    public QueTeDeboDTO(String action, List<Debt> debts, String paymentMethod) {
        this.action = action;
        this.debts = debts;
        this.paymentMethod = paymentMethod;
    }

	public String getAction() {
		return action;
	}
	
	public List<Debt> getDebts() {
		return new ArrayList<Debt>(debts);
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}
}
