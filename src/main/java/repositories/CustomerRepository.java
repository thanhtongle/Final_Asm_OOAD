package repositories;

import models.Customer;

import java.util.HashMap;
import java.util.Map;

public class CustomerRepository {
    private Map<Integer, Customer> customers = new HashMap<>();

    public CustomerRepository() {
        customers.put(1, new Customer(1, "John Doe", "123456789", "john@gmail.com"));
    }

    public Customer findById(int id) {
        return customers.get(id);
    }
}
