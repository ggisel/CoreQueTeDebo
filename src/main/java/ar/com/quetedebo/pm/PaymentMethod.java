package ar.com.quetedebo.pm;
import ar.com.quetedebo.core.Debt;

public interface PaymentMethod {
    String getName();
    void processPayment(Debt debt);
}