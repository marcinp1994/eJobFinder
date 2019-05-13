package com.ejobfinder.controller;

import com.ejobfinder.model.Customer;
import com.ejobfinder.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Le on 1/25/2016.
 */

@Controller
public class RegisterController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/registerCandidate")
    public String registerCandidate(Model model) {

        Customer customer = new Customer();
        customer.setCustomerType("Candidate");
        //BillingAddress billingAddress = new BillingAddress();
        //ShippingAddress shippingAddress = new ShippingAddress();
        //customer.setBillingAddress(billingAddress);
        //customer.setShippingAddress(shippingAddress);

        model.addAttribute("customer", customer);
        model.addAttribute("customerType", "Candidate");


        return "registerCustomer";
    }

    @RequestMapping("/registerEmployer")
    public String registerEmployer(Model model) {

        Customer customer = new Customer();
        customer.setCustomerType("Employer");
        //BillingAddress billingAddress = new BillingAddress();
        //ShippingAddress shippingAddress = new ShippingAddress();
        //customer.setBillingAddress(billingAddress);
        //customer.setShippingAddress(shippingAddress);

        model.addAttribute("customer", customer);
        model.addAttribute("customerType", "Employer");

        return "registerCustomer";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerCustomerPost(@ModelAttribute("customer") Customer customer, Model model, HttpServletRequest request) {
        customer.setEnabled(true);
        customerService.addCustomer(customer);
        try {
            request.login(customer.getUsername(), customer.getPassword());
        } catch (ServletException e) {
            return "registerCustomerSuccess";
        }

        return "redirect:/";

    }

}
