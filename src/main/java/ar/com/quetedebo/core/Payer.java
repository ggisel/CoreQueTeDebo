package ar.com.quetedebo.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.com.quetedebo.pm.PaymentMethod;

public class Payer {

	public Map<String, Debt> processPayments(List<Debt> debts, PaymentMethod paymentMethod) {
        Map<String, Debt> processedPayments = new HashMap<>();

        for (Debt debt : debts) {
            String paymentId = paymentMethod.processPayment(debt.getAddressPayment(), debt.getAmount());
            processedPayments.put(paymentId, debt);
        }

        return processedPayments;
    }
}
