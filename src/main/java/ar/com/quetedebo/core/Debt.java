package ar.com.quetedebo.core;

import java.io.Serializable;

public class Debt implements Serializable {
	private static final long serialVersionUID = -1972263900940025021L;
	private String memberPayment;
	private String addressPayment;
	private Float amount;
	private String description;
	
	public Debt() {
    }
	
	public Debt(String memberPayment, String addressPayment, Float amount, String description) {
		this.memberPayment = memberPayment;
		this.addressPayment = addressPayment;
		this.amount = amount;
		this.description = description;
	}

	public Debt(Debt debt) {
		memberPayment = debt.memberPayment;
		addressPayment = debt.addressPayment;
		amount = debt.amount;
		description = debt.description;
	}

	public String getMemberPayment() {
		return memberPayment;
	}

	public void setMemberPayment(String memberPayment) {
		this.memberPayment = memberPayment;
	}

	public String getAddressPayment() {
		return addressPayment;
	}

	public void setAddressPayment(String addressPayment) {
		this.addressPayment = addressPayment;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "$" + amount + " to " + memberPayment + " at "+ addressPayment + " for " + description;
	}
	
	@Override
	public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj.getClass() != this.getClass()) return false;
        Debt o = (Debt) obj;
        return	o.memberPayment.equals(memberPayment) &&
        		o.addressPayment.equals(addressPayment) &&
        		o.amount.equals(amount) &&
        		o.description.equals(description);
	}
}
