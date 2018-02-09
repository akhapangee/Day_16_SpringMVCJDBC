package com.akhilesh.webapp.controller;

import com.akhilesh.webapp.dao.CustomerDAO;
import com.akhilesh.webapp.entity.Customer;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Akhilesh
 */
@Controller
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    private CustomerDAO customerDAO;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("customer/index");
        try {
            mv.addObject("customers", customerDAO.getAll());
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return mv;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        return "customer/add";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Customer customer) {
        try {
            if (customer.getId() == 0) {
                customerDAO.insert(customer);
            } else {
                customerDAO.update(customer);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return "redirect:/customer";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") int id, ModelMap modelMap) {
        try {
            Customer customer = customerDAO.getById(id);
            if (customer == null) {
                return "redirect:/customer";
            } else {
                modelMap.addAttribute("customer", customer);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return "customer/edit";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") int id) {
        try {
            customerDAO.delete(id);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return "redirect:/customer";
    }

}
