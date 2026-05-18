package Services;

import Entities.Customer;

public interface CustomerService {

    void addCustomer(String customerID, String customerName);

    Customer getCustomer(String customerID);
}
