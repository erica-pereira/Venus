package org.academiadecodigo.tailormoons.placeholder.services;

import org.academiadecodigo.tailormoons.placeholder.exceptions.UserNotFoundException;
import org.academiadecodigo.tailormoons.placeholder.persistence.model.Customer;

import java.util.List;

public interface CustomerService {

    Customer get(Integer id) throws UserNotFoundException;

    Customer getByEmail(String email) throws UserNotFoundException;

    Customer save(Customer customer);

    void delete(Integer id)  throws UserNotFoundException;

    List<Customer> listUsers();

}
