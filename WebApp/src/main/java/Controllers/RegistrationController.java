package Controllers;

import Models.Books;
import Models.Customers;
import Services.CustomersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    CustomersService customersService = new CustomersService();

    @GetMapping("/registration")
    public String registrationPage() {
        return "registration";
    }

    @PostMapping("/registered")
    public String checkRegistrationData (@RequestParam(name = "username") String username,
                                         @RequestParam(name = "password") String password,
                                         @RequestParam(name = "first_name") String first_name,
                                         @RequestParam(name = "last_name") String last_name,
                                         @RequestParam(name = "address") String address,
                                         @RequestParam(name = "phone_number") String phone_number,
                                         @RequestParam(name = "mail") String mail,
                                         Model model) {
        Customers new_customer;
        System.out.println(username);
        System.out.println(password);
        System.out.println(first_name);
        System.out.println(last_name);

        Customers exist_customer = customersService.readCustomerByLogin(username);
        if (exist_customer != null) {
            model.addAttribute("error", "This username is already taken! :(");
            return "pageERROR";
        }

        try {
            new_customer = new Customers(username, password,
                    last_name, first_name, address, phone_number, mail, false);
            customersService.createCustomer(new_customer);

        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "pageERROR";
        }
        Customers check_customer = customersService.readCustomerByID(new_customer.getCustomer_id());
        if (check_customer == null) {
            model.addAttribute("error", "Account creation error");
            return "pageERROR";
        }
        if (check_customer.equals(new_customer)) {
            return "registration_success";
        }
        model.addAttribute("error", "Account creation error");
        return "pageERROR";
    }

}
