package Controllers;

import Models.Customers;
import Services.CustomersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    CustomersService customersService = new CustomersService();
    @GetMapping("/hello")
    public String hello(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }

    @PostMapping("/superSecureLogin")
    public String superSecureLoginPage(@RequestParam(name="login", required=true) String login,
                                       @RequestParam(name="password", required=true) String password, Model model) {
        Customers customer = customersService.readCustomerByLogin(login);
        if ((customer != null) && (password.equals(customer.getCustomer_password()))) {
            return "AutothrizedCustomer";
        }

        return "superSecureLogin";
    }

    @GetMapping("/superSecureLogin")
    public String superSecureLoginPage() {
        return "superSecureLogin";
    }


}