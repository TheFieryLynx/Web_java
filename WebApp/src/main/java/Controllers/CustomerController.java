package Controllers;

import Models.Books;
import Models.Customers;
import Services.BooksService;
import Services.CustomersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CustomerController {
    CustomersService customersService = new CustomersService();

    @GetMapping("/logged/personal")
    public String filmPage(@CookieValue(value = "login", defaultValue = "DefaultValueForCookieUsername") String username,
                           @RequestParam(name = "customer_id", required = true) int customer_id, Model model) {
        Customers customer = customersService.readCustomerByID(customer_id);
        if (!customer.getCustomer_login().equals(username)) {
            model.addAttribute("error", "Permission denied.");
            return "pageERROR";
        }
        model.addAttribute("customer", customer);
        return "customer";
    }

    @GetMapping("/customerEdit")
    public String customerEditPage(@RequestParam(name = "customer_id", required = true) int customer_id, Model model) {
        Customers customer = customersService.readCustomerByID(customer_id);
        model.addAttribute("customer", customer);
        return "customerEdit";
    }

    @PostMapping("/saveEditCustomer")
    public String saveCustomerEditPage(@RequestParam(name = "password") String password,
                                       @RequestParam(name = "first_name") String first_name,
                                       @RequestParam(name = "last_name") String last_name,
                                       @RequestParam(name = "address") String address,
                                       @RequestParam(name = "phone_number") String phone_number,
                                       @RequestParam(name = "mail") String mail,
                                       @RequestParam(name = "customer_id", required = true) int customer_id, Model model) {

        Customers customer = customersService.readCustomerByID(customer_id);
        customer.setCustomer_password(password);
        customer.setFirstname(first_name);
        customer.setLastname(last_name);
        customer.setAddress(address);
        customer.setPhone_number(phone_number);
        customer.setMail(mail);

        customersService.updateCustomer(customer);

        Customers check_customer = customersService.readCustomerByID(customer.getCustomer_id());
        if (check_customer == null) {
            model.addAttribute("error", "Customer update error");
            return "pageERROR";
        }
        model.addAttribute("customer", customer);
        return "customer";
    }
}