package org.academiadecodigo.tailormoons.placeholder.converters;

import org.academiadecodigo.tailormoons.placeholder.dto.CustomerDto;
import org.academiadecodigo.tailormoons.placeholder.exceptions.UserNotFoundException;
import org.academiadecodigo.tailormoons.placeholder.persistence.model.Customer;
import org.academiadecodigo.tailormoons.placeholder.security.EncrypterSHA;
import org.academiadecodigo.tailormoons.placeholder.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoToCustomer {

    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public Customer convert(CustomerDto customerDto) throws UserNotFoundException {

        Customer customer = (customerDto.getId() != null ? customerService.get(customerDto.getId()) : new Customer());

        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setEmail(customerDto.getEmail());
        customer.setPhone(customerDto.getPhone());
        customer.setPassword(customerDto.getPassword());

        return customer;
    }
}
