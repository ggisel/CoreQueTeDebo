package ar.com.quetedebo.pm;

public interface PaymentObserver {
	void onPaymentProcessed(String paymentId);
}
