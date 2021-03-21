package Services;

import Models.Customers;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CustomersServiceTest {

    @Test
    public void testCreateCustomer() {
        CustomersService customersService = new CustomersService();
        Customers new_customer = new Customers("cwarton1", "qwertyuiop",
                            "Warton", "Clevie", "392653, Омская область, город Ступино, въезд Сталина, 30", "55(301)752-0255", "cwarton1@dyndns.org");
        customersService.createCustomer(new_customer);

        Customers check_customer = customersService.readCustomerByID(new_customer.getCustomer_id());
        Assert.assertEquals(new_customer, check_customer);

        customersService.deleteCustomerForever(new_customer);
    }

    @Test
    public void testDeleteCustomerForever() {
        CustomersService customersService = new CustomersService();
        Customers new_customer = new Customers("cwarton1", "qwertyuiop",
                "Warton", "Clevie", "392653, Омская область, город Ступино, въезд Сталина, 30", "55(301)752-0255", "cwarton1@dyndns.org");
        customersService.createCustomer(new_customer);

        Customers check_customer = customersService.readCustomerByID(new_customer.getCustomer_id());
        Assert.assertEquals(new_customer, check_customer);

        customersService.deleteCustomerForever(new_customer);
        check_customer = customersService.readCustomerByID(new_customer.getCustomer_id());
        Assert.assertNull(check_customer);
    }

    @Test
    public void testUpdateCustomer() {
        CustomersService customersService = new CustomersService();
        Customers new_customer = new Customers("cwarton1", "qwertyuiop",
                "Warton", "Clevie", "392653, Омская область, город Ступино, въезд Сталина, 30", "55(301)752-0255", "cwarton1@dyndns.org");
        customersService.createCustomer(new_customer);

        Customers check_customer = customersService.readCustomerByID(new_customer.getCustomer_id());
        Assert.assertEquals(new_customer, check_customer);

        new_customer.setCustomer_password("ikea");
        customersService.updateCustomer(new_customer);
        check_customer = customersService.readCustomerByID(new_customer.getCustomer_id());
        Assert.assertEquals(new_customer, check_customer);
        customersService.deleteCustomerForever(new_customer);
    }

    @Test
    public void testReadCustomerByID() {
        CustomersService customersService = new CustomersService();
        Customers new_customer = new Customers("cwarton1", "qwertyuiop",
                "Warton", "Clevie", "392653, Омская область, город Ступино, въезд Сталина, 30", "55(301)752-0255", "cwarton1@dyndns.org");
        customersService.createCustomer(new_customer);

        Customers check_customer = customersService.readCustomerByID(new_customer.getCustomer_id());
        Assert.assertEquals(new_customer.getCustomer_id(), check_customer.getCustomer_id());
        customersService.deleteCustomerForever(new_customer);
    }

    @Test
    public void testReadCustomerByLogin() {
        CustomersService customersService = new CustomersService();
        Customers new_customer = new Customers("cwarton1", "qwertyuiop",
                "Warton", "Clevie", "392653, Омская область, город Ступино, въезд Сталина, 30", "55(301)752-0255", "cwarton1@dyndns.org");
        customersService.createCustomer(new_customer);

        Customers check_customer = customersService.readCustomerByLogin("cwarton1");
        Assert.assertEquals("cwarton1", check_customer.getCustomer_login());
        customersService.deleteCustomerForever(check_customer);
    }
}