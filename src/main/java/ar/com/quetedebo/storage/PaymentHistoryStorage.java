package ar.com.quetedebo.storage;

import java.util.List;

public interface PaymentHistoryStorage {
    void saveRecord(PaymentRecord record);
    List<PaymentRecord> getHistory();
}
