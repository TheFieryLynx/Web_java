package Services;

import Models.Books;
import Models.Customers;
import Models.Orders;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class OrdersServiceTest {

    @Test
    public void testCreateOrder() {
        CustomersService customersService = new CustomersService();
        OrdersService ordersService = new OrdersService();
        Customers new_customer = new Customers("UserWithOrder", "qwertyuiop","Warton", "Clevie", "392653, Омская область, город Ступино, въезд Сталина, 30", "55(301)752-0255", "cwarton1@dyndns.org", false);

        BooksService bookService = new BooksService();
        Books new_book = new Books(869, 21, "Воин Доброй Удачи", "Бэккер Р. Скотт", "Героическое зарубежное фэнтези", "Азбука", 2021, 780, "Мягкая бумажная");
        bookService.createBook(new_book);

        customersService.createCustomer(new_customer);

        Orders new_order = new Orders(new_customer, "193857, Воронежская область, город Москва, спуск Чехова, 62",
                java.sql.Date.valueOf("2020-02-17"), "delivered", java.sql.Date.valueOf("2020-02-16"), 744,
                new_book);

        ordersService.createOrder(new_order);
        Orders check_order = ordersService.readOrderByID(new_order.getOrder_id());
        Assert.assertEquals(new_order, check_order);
        ordersService.deleteOrder(new_order);
        customersService.deleteCustomerForever(new_customer);
    }

    @Test
    public void testDeleteOrder() {
        CustomersService customersService = new CustomersService();
        OrdersService ordersService = new OrdersService();
        Customers new_customer = new Customers("UserWithOrder", "qwertyuiop","Warton", "Clevie", "392653, Омская область, город Ступино, въезд Сталина, 30", "55(301)752-0255", "cwarton1@dyndns.org", false);
        BooksService bookService = new BooksService();
        Books new_book = new Books(869, 21, "Воин Доброй Удачи", "Бэккер Р. Скотт", "Героическое зарубежное фэнтези", "Азбука", 2021, 780, "Мягкая бумажная");
        bookService.createBook(new_book);
        customersService.createCustomer(new_customer);
        Orders new_order = new Orders(new_customer, "193857, Воронежская область, город Москва, спуск Чехова, 62",
                java.sql.Date.valueOf("2020-02-17"), "delivered", java.sql.Date.valueOf("2020-02-16"), 744,
                new_book);

        ordersService.createOrder(new_order);
        Orders check_order = ordersService.readOrderByID(new_order.getOrder_id());
        Assert.assertEquals(new_order, check_order);

        ordersService.deleteOrder(new_order);
        customersService.deleteCustomerForever(new_customer);
        check_order = ordersService.readOrderByID(new_order.getOrder_id());
        Assert.assertNull(check_order);
    }

    @Test
    public void testUpdateOrder() {
        CustomersService customersService = new CustomersService();
        OrdersService ordersService = new OrdersService();
        Customers new_customer = new Customers("UserWithOrder", "qwertyuiop","Warton", "Clevie", "392653, Омская область, город Ступино, въезд Сталина, 30", "55(301)752-0255", "cwarton1@dyndns.org", false);
        BooksService bookService = new BooksService();
        Books new_book = new Books(869, 21, "Воин Доброй Удачи", "Бэккер Р. Скотт", "Героическое зарубежное фэнтези", "Азбука", 2021, 780, "Мягкая бумажная");
        bookService.createBook(new_book);
        customersService.createCustomer(new_customer);
        Orders new_order = new Orders(new_customer, "193857, Воронежская область, город Москва, спуск Чехова, 62",
                java.sql.Date.valueOf("2020-02-17"), "delivered", java.sql.Date.valueOf("2020-02-16"), 744,
                new_book);

        ordersService.createOrder(new_order);
        Orders check_order = ordersService.readOrderByID(new_order.getOrder_id());
        Assert.assertEquals(new_order, check_order);

        new_order.setAddress("Дом Пупы и Лупы");
        ordersService.updateOrder(new_order);

        check_order = ordersService.readOrderByID(new_order.getOrder_id());
        Assert.assertEquals(new_order, check_order);
        ordersService.deleteOrder(new_order);
        customersService.deleteCustomerForever(new_customer);
    }

    @Test
    public void testReadOrderByID() {
        CustomersService customersService = new CustomersService();
        OrdersService ordersService = new OrdersService();
        Customers new_customer = new Customers("UserWithOrder", "qwertyuiop","Warton", "Clevie", "392653, Омская область, город Ступино, въезд Сталина, 30", "55(301)752-0255", "cwarton1@dyndns.org", false);

        BooksService bookService = new BooksService();
        Books new_book = new Books(869, 21, "Воин Доброй Удачи", "Бэккер Р. Скотт", "Героическое зарубежное фэнтези", "Азбука", 2021, 780, "Мягкая бумажная");
        bookService.createBook(new_book);
        customersService.createCustomer(new_customer);
        Orders new_order = new Orders(new_customer, "193857, Воронежская область, город Москва, спуск Чехова, 62",
                java.sql.Date.valueOf("2020-02-17"), "delivered", java.sql.Date.valueOf("2020-02-16"), 744,
                new_book);

        ordersService.createOrder(new_order);
        Orders check_order = ordersService.readOrderByID(new_order.getOrder_id());
        Assert.assertEquals(new_order.getOrder_id(), check_order.getOrder_id());
        ordersService.deleteOrder(new_order);
        customersService.deleteCustomerForever(new_customer);

    }
}