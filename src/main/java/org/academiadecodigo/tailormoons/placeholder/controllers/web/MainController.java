package org.academiadecodigo.tailormoons.placeholder.controllers.web;

import org.academiadecodigo.tailormoons.placeholder.converters.CustomerDtoToCustomer;
import org.academiadecodigo.tailormoons.placeholder.converters.CustomertoCustomerDto;
import org.academiadecodigo.tailormoons.placeholder.dto.CustomerDto;
import org.academiadecodigo.tailormoons.placeholder.dto.SignUp;
import org.academiadecodigo.tailormoons.placeholder.exceptions.UserNotFoundException;
import org.academiadecodigo.tailormoons.placeholder.persistence.model.Customer;
import org.academiadecodigo.tailormoons.placeholder.security.EncrypterSHA;
import org.academiadecodigo.tailormoons.placeholder.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.NoResultException;
import javax.validation.Valid;

@Controller
public class MainController {

    //services
    //dto
    private CustomerService customerService;
    private CustomertoCustomerDto customertoCustomerDto;
    private CustomerDtoToCustomer customerDtoToCustomer;

    //setters
    @Autowired
    public void setCustomertoCustomerDto(CustomertoCustomerDto customertoCustomerDto) {
        this.customertoCustomerDto = customertoCustomerDto;
    }

    @Autowired
    public void setCustomerDtoToCustomer(CustomerDtoToCustomer customerDtoToCustomer) {
        this.customerDtoToCustomer = customerDtoToCustomer;
    }

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(path = {"/", "index"})
    public String home() {
        return "index";
    }

    @RequestMapping("/signup")
    public ModelAndView signUp() {

        CustomerDto customerDto = customertoCustomerDto.convert(new Customer());

        return new ModelAndView("signup", "userDto", customerDto);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/signup", params = "action=save")
    public String addUser(@Valid @ModelAttribute("userDto") CustomerDto customerDto, BindingResult bindingResult) throws UserNotFoundException {

        System.out.println(bindingResult.getAllErrors());

        if (bindingResult.hasErrors()) {
            return "signup";
        }

        String hashedPassword = EncrypterSHA.getHash(customerDto.getPassword());
        customerDto.setPassword(hashedPassword);
        Customer customer = customerDtoToCustomer.convert(customerDto);

        customerService.save(customer);

        customerDto.setPassword("");
        return "login";
    }

    @RequestMapping("/signin")
    public ModelAndView login() {

        return new ModelAndView("login", "userDto", new CustomerDto());
    }

    @PostMapping(value = "/auth")
    public String authentication(@Validated(SignUp.class) @ModelAttribute("userDto") CustomerDto customerDto, BindingResult bindingResult) throws UserNotFoundException {

        if (bindingResult.hasErrors()) {
            return "redirect: /signup";
        }

        try {
            Customer loggingCustomer = customerService.getByEmail(customerDto.getEmail());

            if (loggingCustomer.getPassword() == null) {
                return "redirect: /signup";
            }

            if(loggingCustomer.getPassword().equals(EncrypterSHA.getHash(customerDto.getPassword()))){
                return "redirect:/user/" + loggingCustomer.getId();
            }

        } catch (NoResultException e) { }
        return "redirect: /signup";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/about")
    public String aboutMe() {
        return "about";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/contact")
    public String contact() {
        return "contact";
    }
}
