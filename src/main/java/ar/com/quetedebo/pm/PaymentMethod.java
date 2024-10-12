package ar.com.quetedebo.pm;

public interface PaymentMethod {
	public String processPayment(String address, Float amount);
}