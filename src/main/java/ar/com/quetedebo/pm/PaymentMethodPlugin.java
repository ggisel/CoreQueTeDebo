package ar.com.quetedebo.pm;

public interface PaymentMethodPlugin {
	public String processPayment(String address, Float amount);
	public String getName();
}