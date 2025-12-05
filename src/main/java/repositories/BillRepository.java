package repositories;

import models.Bill;

import java.util.HashMap;
import java.util.Map;

public class BillRepository {
    private Map<Integer, Bill> bills = new HashMap<>();
    private int idCounter = 1;

    public void save(Bill bill) {
        bill.setBillId(idCounter++);
        bills.put(bill.getBillId(), bill);
    }
}
