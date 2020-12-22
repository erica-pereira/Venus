package org.academiadecodigo.tailormoons.placeholder.services;

import org.academiadecodigo.tailormoons.placeholder.exceptions.UserNotFoundException;
import org.academiadecodigo.tailormoons.placeholder.persistence.dao.jpa.JpaCustomerDao;
import org.academiadecodigo.tailormoons.placeholder.persistence.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private JpaCustomerDao userDao;

    @Autowired
    public void setUserDao(JpaCustomerDao userDao) {
        this.userDao = userDao;
    }


    @Transactional (readOnly = true)
    @Override
    public Customer get(Integer id) throws UserNotFoundException {

        Customer customer = userDao.findById(id);

        if (customer == null) {
            throw new UserNotFoundException();
        }

        return customer;
    }

    @Transactional (readOnly = true)
    @Override
    public Customer getByEmail(String email) throws UserNotFoundException{

        Customer customer = userDao.findByEmail(email);

        if (customer == null) {
            throw new UserNotFoundException();
        }

        return userDao.findByEmail(email);
    }

    @Transactional
    @Override
    public Customer save(Customer customer) {
        return userDao.saveOrUpdate(customer);
    }

    @Transactional
    @Override
    public void delete(Integer id) throws UserNotFoundException {

        Customer customer = userDao.findById(id);

        if (customer == null) {
            throw new UserNotFoundException();
        }

        userDao.delete(id);
    }


    @Transactional (readOnly = true)
    @Override
    public List<Customer> listUsers() {
        return userDao.findAll();
    }





}
