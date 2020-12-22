package org.academiadecodigo.tailormoons.placeholder.converters;

import org.academiadecodigo.tailormoons.placeholder.dto.CustomerDto;
import org.academiadecodigo.tailormoons.placeholder.persistence.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomertoCustomerDto extends AbstractConverter<Customer, CustomerDto> {

    @Override
    public CustomerDto convert(Customer customer) {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setPhone(customer.getPhone());
        customerDto.setPassword(customer.getPassword());
        customerDto.setFavourites(customer.getFavourites());

        return customerDto;
    }
}
