package ar.com.quetedebo.storage;

import java.util.ArrayList;
import java.util.List;

public class InMemoryPaymentHistoryStorage implements PaymentHistoryStorage {
    private final List<PaymentRecord> history = new ArrayList<>();

    @Override
    public void saveRecord(PaymentRecord record) {
        history.add(record);
    }

    @Override
    public List<PaymentRecord> getHistory() {
        return new ArrayList<>(history);
    }
}