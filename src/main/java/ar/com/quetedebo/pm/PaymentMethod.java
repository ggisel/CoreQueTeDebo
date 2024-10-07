package ar.com.quetedebo.pm;

public interface PaymentMethod {
	public String payWithPaymentMethod(String address, Float ammount);
}