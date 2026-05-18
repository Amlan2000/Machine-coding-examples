package Services.Impl;

import Entities.Customer;
import Exceptions.NotificationException;
import Services.CustomerService;

import java.util.HashMap;

public class CustomerServiceImpl implements CustomerService {

    public HashMap<String,Customer> customerHashMap = new HashMap<>();

    @Override
    public void addCustomer(String customerID, String customerName) {

        if(!customerHashMap.isEmpty() && customerHashMap.containsKey(customerID)){
            throw new NotificationException("Customer is already added. \n");
        }

        Customer customer = Customer.builder().customerName(customerName).customerID(customerID).build();

        customerHashMap.put(customerID,customer);
        System.out.println("Customer "+customerName+ " is successfully added. \n");
    }

    @Override
    public Customer getCustomer(String customerID) {
        if(customerHashMap.containsKey(customerID)){
            return customerHashMap.get(customerID);
        }

        throw new NotificationException("Customer not found. \n");
    }
}
