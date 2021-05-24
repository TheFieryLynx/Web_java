import Models.Admin;
import Models.Books;
import Models.Customers;
import Models.Orders;
import Services.AdminService;
import Services.BooksService;
import Services.CustomersService;
import Services.OrdersService;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class SeleniumTests {
    String URL = "http://localhost:8080/";
    WebDriver driver;
    WebDriverWait wait;
    CustomersService customersService = new CustomersService();
    BooksService bookService = new BooksService();
    OrdersService ordersService = new OrdersService();
    AdminService adminService = new AdminService();
    Books new_book = new Books(869, 21, "ТестНазвание", "ТестАвтор", "ТестЖанр", "ТестИздательство", 2021, 780, "ТестОбложка", false);

    Customers new_customer = new Customers("testUser", "testpswd",
            "testfirst", "testlast", "testAddress", "testphone", "testmail", false);


    @BeforeClass
    public void settings() {
        final String safariDriverPath = "/usr/bin/safaridriver";
        System.setProperty("webdriver.safari.driver", safariDriverPath);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "eager");
        driver = new SafariDriver(capabilities);
        driver.manage().window().setSize(new Dimension(1000, 1000));
        driver.manage().timeouts().implicitlyWait(10, SECONDS);

        wait = new WebDriverWait(driver,10);
        driver.manage().timeouts().pageLoadTimeout(10, SECONDS);


    }

    @Test(priority=1)
    public void indexRootPageButtonTest() {
        driver.get(URL);

        WebElement button = wait.until(visibilityOfElementLocated(By.id("index_root_page_button")));
        Assert.assertEquals(driver.getTitle(), "Main Page for not logged user");
        button.click();
        wait.until(stalenessOf(button));

        wait.until(visibilityOfElementLocated(By.id("index_root_page_button")));
        Assert.assertEquals(driver.getTitle(), "Main Page for not logged user");

    }

    @Test(priority=2)
    public void indexRegistrationButtonTest() {
        driver.get(URL);

        WebElement button = wait.until(visibilityOfElementLocated(By.id("index_registration_button")));
        Assert.assertEquals(driver.getTitle(), "Main Page for not logged user");
        button.click();
        wait.until(stalenessOf(button));

        wait.until(visibilityOfElementLocated(By.id("registration_root_page_button")));
        Assert.assertEquals(driver.getTitle(), "Registration Page");

        driver.findElement(By.id("validationDefaultUsername")).sendKeys(new_customer.getCustomer_login());
        driver.findElement(By.id("inputPassword")).sendKeys(new_customer.getCustomer_password());
        driver.findElement(By.id("validationDefaultFirstName")).sendKeys(new_customer.getFirstname());
        driver.findElement(By.id("validationDefaultSecondName")).sendKeys(new_customer.getLastname());
        driver.findElement(By.id("validationDefaultAddress")).sendKeys(new_customer.getAddress());
        driver.findElement(By.id("validationDefaultPhone")).sendKeys(new_customer.getPhone_number());
        driver.findElement(By.id("validationDefaultMail")).sendKeys(new_customer.getMail());

        button = wait.until(visibilityOfElementLocated(By.id("registration_button")));
        button.click();
        wait.until(stalenessOf(button));
        wait.until(visibilityOfElementLocated(By.id("registration_success_root_page_button")));
        Assert.assertEquals(driver.getTitle(), "Registration Success");

        Customers check_customer = customersService.readCustomerByLogin(new_customer.getCustomer_login());
        Assert.assertEquals(new_customer.getCustomer_login(), check_customer.getCustomer_login());

        button = wait.until(visibilityOfElementLocated(By.id("registration_success_root_page_button")));
        button.click();
        wait.until(stalenessOf(button));

        button = wait.until(visibilityOfElementLocated(By.id("index_registration_button")));
        Assert.assertEquals(driver.getTitle(), "Main Page for not logged user");

        button.click();
        wait.until(stalenessOf(button));

        wait.until(visibilityOfElementLocated(By.id("registration_root_page_button")));
        Assert.assertEquals(driver.getTitle(), "Registration Page");

        driver.findElement(By.id("validationDefaultUsername")).sendKeys(new_customer.getCustomer_login());
        driver.findElement(By.id("inputPassword")).sendKeys(new_customer.getCustomer_password());
        driver.findElement(By.id("validationDefaultFirstName")).sendKeys(new_customer.getFirstname());
        driver.findElement(By.id("validationDefaultSecondName")).sendKeys(new_customer.getLastname());
        driver.findElement(By.id("validationDefaultAddress")).sendKeys(new_customer.getAddress());
        driver.findElement(By.id("validationDefaultPhone")).sendKeys(new_customer.getPhone_number());
        driver.findElement(By.id("validationDefaultMail")).sendKeys(new_customer.getMail());

        button = wait.until(visibilityOfElementLocated(By.id("registration_button")));
        button.click();
        wait.until(stalenessOf(button));

        wait.until(visibilityOfElementLocated(By.id("page_error_root_page_button")));
        Assert.assertEquals(driver.getTitle(), "Page Error");
    }


    @Test(priority=3)
    public void indexBookButtonTest() {

        BooksService bookService = new BooksService();

        driver.get(URL);
        wait.until(visibilityOfElementLocated(By.id("index_root_page_button")));
        Assert.assertEquals(driver.getTitle(), "Main Page for not logged user");

        String str_book_id = driver.findElement(By.name("index_book_id")).getAttribute("value");

        driver.get("http://localhost:8080/book?book_id=" + str_book_id);
        wait.until(visibilityOfElementLocated(By.id("book_root_page_button")));
        Assert.assertEquals(driver.getTitle(), "Book Info Page");

        Books book = bookService.readBookByID(Integer.valueOf(str_book_id));

        String bookInfoTable = driver.findElement(By.id("bookInfoTable")).getText();
        System.out.println(bookInfoTable);
        Assert.assertTrue(bookInfoTable.contains(book.getTitle()));
        Assert.assertTrue(bookInfoTable.contains(book.getAuthor()));
        Assert.assertTrue(bookInfoTable.contains(book.getGenre()));
        Assert.assertTrue(bookInfoTable.contains(book.getPub_house()));
        Assert.assertTrue(bookInfoTable.contains(String.valueOf(book.getPub_year())));
        Assert.assertTrue(bookInfoTable.contains(String.valueOf(book.getNum_pages())));
        Assert.assertTrue(bookInfoTable.contains(book.getCover_type()));
        Assert.assertTrue(bookInfoTable.contains(String.valueOf(book.getPrice())));
        Assert.assertTrue(bookInfoTable.contains(String.valueOf(book.getAmount())));

    }

    @Test(priority=4)
    public void indexLogInButtonTest() {
        driver.get(URL);

        WebElement button = wait.until(visibilityOfElementLocated(By.id("index_log_in_button")));
        Assert.assertEquals(driver.getTitle(), "Main Page for not logged user");
        button.click();
        wait.until(stalenessOf(button));

        wait.until(visibilityOfElementLocated(By.id("log_in_root_page_button")));
        Assert.assertEquals(driver.getTitle(), "Log In Page");

        driver.findElement(By.id("inputUsername")).sendKeys(new_customer.getCustomer_login());
        driver.findElement(By.id("inputPassword")).sendKeys(new_customer.getCustomer_password());

        button = wait.until(visibilityOfElementLocated(By.id("log_in_button")));
        button.click();
        wait.until(stalenessOf(button));

        wait.until(visibilityOfElementLocated(By.id("LUindex_root_page_button")));
        Assert.assertEquals(driver.getTitle(), "Main Page for logged user");
    }

    @Test(priority=5)
    public void LUindexRootPageButtonTest() {
        driver.get(URL);

        WebElement button = wait.until(visibilityOfElementLocated(By.id("LUindex_root_page_button")));
        Assert.assertEquals(driver.getTitle(), "Main Page for logged user");
        button.click();
        wait.until(stalenessOf(button));

        wait.until(visibilityOfElementLocated(By.id("LUindex_root_page_button")));
        Assert.assertEquals(driver.getTitle(), "Main Page for logged user");
    }

    @Test(priority=6)
    public void LUindexPersonalAreaButtonTest() {
        driver.get(URL);

        WebElement button = wait.until(visibilityOfElementLocated(By.id("LUindex_personal_area_button")));
        Assert.assertEquals(driver.getTitle(), "Main Page for logged user");
        button.click();
        wait.until(stalenessOf(button));

        wait.until(visibilityOfElementLocated(By.id("log_in_root_page_button")));
        Assert.assertEquals(driver.getTitle(), "Customer info");

        String customerInfoTable = driver.findElement(By.id("customer_info_table")).getText();
        System.out.println(customerInfoTable);
        Assert.assertTrue(customerInfoTable.contains(new_customer.getCustomer_login()));
        Assert.assertTrue(customerInfoTable.contains(new_customer.getCustomer_password()));
        Assert.assertTrue(customerInfoTable.contains(new_customer.getFirstname()));
        Assert.assertTrue(customerInfoTable.contains(new_customer.getLastname()));
        Assert.assertTrue(customerInfoTable.contains(new_customer.getAddress()));
        Assert.assertTrue(customerInfoTable.contains(new_customer.getPhone_number()));
        Assert.assertTrue(customerInfoTable.contains(new_customer.getMail()));

        button = wait.until(visibilityOfElementLocated(By.id("customer_info_edit_button")));
        button.click();
        wait.until(stalenessOf(button));

        wait.until(visibilityOfElementLocated(By.id("customer_info_edit_root_page_button")));
        Assert.assertEquals(driver.getTitle(), "Customer info edit");

//        new_customer.setCustomer_password(new_customer.getFirstname() + "_new");
//        new_customer.setCustomer_password(new_customer.getLastname() + "_new");
//        new_customer.setCustomer_password(new_customer.getAddress() + "_new");
//        new_customer.setCustomer_password(new_customer.getPhone_number() + "_new");
//        new_customer.setCustomer_password(new_customer.getMail() + "_new");

        driver.findElement(By.id("inputFirstName")).clear();
        driver.findElement(By.id("inputFirstName")).sendKeys(new_customer.getFirstname()+ "_new");
        driver.findElement(By.id("inputLastName")).clear();
        driver.findElement(By.id("inputLastName")).sendKeys(new_customer.getLastname()+ "_new");
        driver.findElement(By.id("inputAddress")).clear();
        driver.findElement(By.id("inputAddress")).sendKeys(new_customer.getAddress()+ "_new");
        driver.findElement(By.id("inputPhoneNumber")).clear();
        driver.findElement(By.id("inputPhoneNumber")).sendKeys(new_customer.getPhone_number()+ "_new");
        driver.findElement(By.id("inputMail")).clear();
        driver.findElement(By.id("inputMail")).sendKeys(new_customer.getMail()+ "_new");

        button = wait.until(visibilityOfElementLocated(By.id("customer_info_edit_save")));
        button.click();
        wait.until(stalenessOf(button));

        wait.until(visibilityOfElementLocated(By.id("log_in_root_page_button")));
        Assert.assertEquals(driver.getTitle(), "Customer info");

        customerInfoTable = driver.findElement(By.id("customer_info_table")).getText();
        System.out.println(customerInfoTable);
        Assert.assertTrue(customerInfoTable.contains(new_customer.getCustomer_login()));
        Assert.assertTrue(customerInfoTable.contains(new_customer.getFirstname()));
        Assert.assertTrue(customerInfoTable.contains(new_customer.getLastname()));
        Assert.assertTrue(customerInfoTable.contains(new_customer.getAddress()));
        Assert.assertTrue(customerInfoTable.contains(new_customer.getPhone_number()));
        Assert.assertTrue(customerInfoTable.contains(new_customer.getMail()));
    }

    @Test(priority=7)
    public void LUindexBookTest() {
        driver.get(URL);
        wait.until(visibilityOfElementLocated(By.id("LUindex_root_page_button")));
        Assert.assertEquals(driver.getTitle(), "Main Page for logged user");

        String str_book_id = driver.findElement(By.name("index_book_id")).getAttribute("value");

        driver.get("http://localhost:8080/book?book_id=" + str_book_id);
        wait.until(visibilityOfElementLocated(By.id("book_buy_root_page_button")));
        Assert.assertEquals(driver.getTitle(), "Book buy info");

        Books book = bookService.readBookByID(Integer.valueOf(str_book_id));

        String bookInfoTable = driver.findElement(By.id("bookInfoTable")).getText();
        System.out.println(bookInfoTable);
        Assert.assertTrue(bookInfoTable.contains(book.getTitle()));
        Assert.assertTrue(bookInfoTable.contains(book.getAuthor()));
        Assert.assertTrue(bookInfoTable.contains(book.getGenre()));
        Assert.assertTrue(bookInfoTable.contains(book.getPub_house()));
        Assert.assertTrue(bookInfoTable.contains(String.valueOf(book.getPub_year())));
        Assert.assertTrue(bookInfoTable.contains(String.valueOf(book.getNum_pages())));
        Assert.assertTrue(bookInfoTable.contains(book.getCover_type()));
        Assert.assertTrue(bookInfoTable.contains(String.valueOf(book.getPrice())));
        Assert.assertTrue(bookInfoTable.contains(String.valueOf(book.getAmount())));

        WebElement button = wait.until(visibilityOfElementLocated(By.id("book_buy_button")));
        button.click();
        wait.until(stalenessOf(button));

        wait.until(visibilityOfElementLocated(By.id("book_check_order_root_page_button")));
        Assert.assertEquals(driver.getTitle(), "Book check order");

        bookInfoTable = driver.findElement(By.id("bookInfoTable")).getText();
        System.out.println(bookInfoTable);
        Assert.assertTrue(bookInfoTable.contains(book.getTitle()));
        Assert.assertTrue(bookInfoTable.contains(book.getAuthor()));
        Assert.assertTrue(bookInfoTable.contains(book.getGenre()));
        Assert.assertTrue(bookInfoTable.contains(book.getPub_house()));
        Assert.assertTrue(bookInfoTable.contains(String.valueOf(book.getPub_year())));
        Assert.assertTrue(bookInfoTable.contains(String.valueOf(book.getNum_pages())));
        Assert.assertTrue(bookInfoTable.contains(book.getCover_type()));
        Assert.assertTrue(bookInfoTable.contains(String.valueOf(book.getPrice())));
        Assert.assertTrue(bookInfoTable.contains(String.valueOf(book.getAmount())));

        Customers check_customer = customersService.readCustomerByLogin(new_customer.getCustomer_login());

        String customerInfoTable = driver.findElement(By.id("customer_info_table")).getText();
        System.out.println(customerInfoTable);
        Assert.assertTrue(customerInfoTable.contains(check_customer.getFirstname()));
        Assert.assertTrue(customerInfoTable.contains(check_customer.getLastname()));
        Assert.assertTrue(customerInfoTable.contains(check_customer.getAddress()));
        Assert.assertTrue(customerInfoTable.contains(check_customer.getPhone_number()));
        Assert.assertTrue(customerInfoTable.contains(check_customer.getMail()));

        button = wait.until(visibilityOfElementLocated(By.id("book_check_order_edit_customer_button")));
        button.click();
        wait.until(stalenessOf(button));

        wait.until(visibilityOfElementLocated(By.id("book_edit_check_order_root_page_button")));
        Assert.assertEquals(driver.getTitle(), "Book edit check order");

        bookInfoTable = driver.findElement(By.id("bookInfoTable")).getText();
        System.out.println(bookInfoTable);
        Assert.assertTrue(bookInfoTable.contains(book.getTitle()));
        Assert.assertTrue(bookInfoTable.contains(book.getAuthor()));
        Assert.assertTrue(bookInfoTable.contains(book.getGenre()));
        Assert.assertTrue(bookInfoTable.contains(book.getPub_house()));
        Assert.assertTrue(bookInfoTable.contains(String.valueOf(book.getPub_year())));
        Assert.assertTrue(bookInfoTable.contains(String.valueOf(book.getNum_pages())));
        Assert.assertTrue(bookInfoTable.contains(book.getCover_type()));
        Assert.assertTrue(bookInfoTable.contains(String.valueOf(book.getPrice())));
        Assert.assertTrue(bookInfoTable.contains(String.valueOf(book.getAmount())));

        driver.findElement(By.id("first_name")).clear();
        driver.findElement(By.id("first_name")).sendKeys(new_customer.getFirstname()+ "_new");
        driver.findElement(By.id("last_name")).clear();
        driver.findElement(By.id("last_name")).sendKeys(new_customer.getLastname()+ "_new");
        driver.findElement(By.id("address")).clear();
        driver.findElement(By.id("address")).sendKeys(new_customer.getAddress()+ "_new");
        driver.findElement(By.id("phone")).clear();
        driver.findElement(By.id("phone")).sendKeys(new_customer.getPhone_number()+ "_new");
        driver.findElement(By.id("mail")).clear();
        driver.findElement(By.id("mail")).sendKeys(new_customer.getMail()+ "_new");

        button = wait.until(visibilityOfElementLocated(By.id("book_check_order_edit_customer_save_button")));
        button.click();
        wait.until(stalenessOf(button));

        wait.until(visibilityOfElementLocated(By.id("book_check_order_root_page_button")));
        Assert.assertEquals(driver.getTitle(), "Book check order");


        button = wait.until(visibilityOfElementLocated(By.id("book_check_order_buy_customer_button")));
        button.click();
        wait.until(stalenessOf(button));

        wait.until(visibilityOfElementLocated(By.id("order_success_root_page_button")));
        Assert.assertEquals(driver.getTitle(), "Order success");

    }

    @Test(priority=8)
    public void LUindexOrdersTest() {
        driver.get(URL);

        WebElement button = wait.until(visibilityOfElementLocated(By.id("LUindex_orders_button")));
        Assert.assertEquals(driver.getTitle(), "Main Page for logged user");
        button.click();
        wait.until(stalenessOf(button));

        wait.until(visibilityOfElementLocated(By.id("customer_orders_root_page_button")));
        Assert.assertEquals(driver.getTitle(), "Customer orders");

        Customers customer = customersService.readCustomerByLogin(new_customer.getCustomer_login());

        Orders order = ordersService.readOrdersByCustomerId(customer).get(0);

        String CustomerOrdersTable = driver.findElement(By.id("CustomerOrdersTable")).getText();
        System.out.println(CustomerOrdersTable);
        Assert.assertTrue(CustomerOrdersTable.contains(String.valueOf(order.getOrder_id())));
        Assert.assertTrue(CustomerOrdersTable.contains(order.getAddress()));
        Assert.assertTrue(CustomerOrdersTable.contains(order.getStatus()));
        Assert.assertTrue(CustomerOrdersTable.contains(String.valueOf(order.getOrder_price())));
        Assert.assertTrue(CustomerOrdersTable.contains(order.getBook_id().getTitle()));
    }

    @Test(priority=9)
    public void LUindexLogOutTest() {
        driver.get(URL);

        WebElement button = wait.until(visibilityOfElementLocated(By.id("LUindex_logout_button")));
        Assert.assertEquals(driver.getTitle(), "Main Page for logged user");
        button.click();
        wait.until(stalenessOf(button));

        wait.until(visibilityOfElementLocated(By.id("index_root_page_button")));
        Assert.assertEquals(driver.getTitle(), "Main Page for not logged user");
    }

    @Test(priority=10)
    public void LAindexLogInButtonTest() {
        driver.get(URL);

        WebElement button = wait.until(visibilityOfElementLocated(By.id("index_log_in_button")));
        Assert.assertEquals(driver.getTitle(), "Main Page for not logged user");
        button.click();
        wait.until(stalenessOf(button));

        wait.until(visibilityOfElementLocated(By.id("log_in_root_page_button")));
        Assert.assertEquals(driver.getTitle(), "Log In Page");

        Admin admin = adminService.readAdminByLogin("admin");

        driver.findElement(By.id("inputUsername")).sendKeys(admin.getAdmin_login());
        driver.findElement(By.id("inputPassword")).sendKeys(admin.getAdmin_password());

        button = wait.until(visibilityOfElementLocated(By.id("log_in_button")));
        button.click();
        wait.until(stalenessOf(button));

        wait.until(visibilityOfElementLocated(By.id("LAindex_root_page_button")));
        Assert.assertEquals(driver.getTitle(), "Main Page for admin");
    }

    @Test(priority=11)
    public void indexAddBookButtonTest() {
        driver.get(URL);

        WebElement button = wait.until(visibilityOfElementLocated(By.id("LAindex_add_book_button")));
        Assert.assertEquals(driver.getTitle(), "Main Page for admin");
        button.click();
        wait.until(stalenessOf(button));

        wait.until(visibilityOfElementLocated(By.id("add_book_root_page_button")));
        Assert.assertEquals(driver.getTitle(), "Add Book");

        driver.findElement(By.id("validationBookTitle")).sendKeys(new_book.getTitle());
        driver.findElement(By.id("validationBookAuthor")).sendKeys(new_book.getAuthor());
        driver.findElement(By.id("validationBookGenre")).sendKeys(new_book.getGenre());
        driver.findElement(By.id("validationBookPubHouse")).sendKeys(new_book.getPub_house());
        driver.findElement(By.id("validationBookYear")).sendKeys(String.valueOf(new_book.getPub_year()));
        driver.findElement(By.id("validationNumOfPages")).sendKeys(String.valueOf(new_book.getNum_pages()));
        driver.findElement(By.id("validationCoverType")).sendKeys(new_book.getCover_type());
        driver.findElement(By.id("validationBookPrice")).sendKeys(String.valueOf(new_book.getPrice()));
        driver.findElement(By.id("validationAmount")).sendKeys(String.valueOf(new_book.getAmount()));

        button = wait.until(visibilityOfElementLocated(By.id("add_book_button")));
        button.click();
        wait.until(stalenessOf(button));

        wait.until(visibilityOfElementLocated(By.id("LAindex_add_book_button")));
        Assert.assertEquals(driver.getTitle(), "Main Page for admin");
    }

    @Test(priority=12)
    public void LAindexBookButtonTest() {
        driver.get(URL);
        wait.until(visibilityOfElementLocated(By.id("LAindex_add_book_button")));
        Assert.assertEquals(driver.getTitle(), "Main Page for admin");

        Books book = bookService.readBooksListByTitle(new_book.getTitle()).get(0);

        driver.get("http://localhost:8080/book?book_id=" + String.valueOf(book.getBook_id()));
        wait.until(visibilityOfElementLocated(By.id("book_root_page_button")));
        Assert.assertEquals(driver.getTitle(), "Book admin info");

        String bookInfoTable = driver.findElement(By.id("bookInfoTable")).getText();
        System.out.println(bookInfoTable);
        Assert.assertTrue(bookInfoTable.contains(book.getTitle()));
        Assert.assertTrue(bookInfoTable.contains(book.getAuthor()));
        Assert.assertTrue(bookInfoTable.contains(book.getGenre()));
        Assert.assertTrue(bookInfoTable.contains(book.getPub_house()));
        Assert.assertTrue(bookInfoTable.contains(String.valueOf(book.getPub_year())));
        Assert.assertTrue(bookInfoTable.contains(String.valueOf(book.getNum_pages())));
        Assert.assertTrue(bookInfoTable.contains(book.getCover_type()));
        Assert.assertTrue(bookInfoTable.contains(String.valueOf(book.getPrice())));
        Assert.assertTrue(bookInfoTable.contains(String.valueOf(book.getAmount())));

        WebElement button = wait.until(visibilityOfElementLocated(By.id("book_admin_edit_button")));
        button.click();
        wait.until(stalenessOf(button));

        wait.until(visibilityOfElementLocated(By.id("book_edit_root_page_button")));
        Assert.assertEquals(driver.getTitle(), "Book edit");

        driver.findElement(By.id("inputTitle")).clear();
        driver.findElement(By.id("inputTitle")).sendKeys(new_book.getTitle()+ "_new");
        driver.findElement(By.id("inputAuthor")).clear();
        driver.findElement(By.id("inputAuthor")).sendKeys(new_book.getAuthor()+ "_new");
        driver.findElement(By.id("inputGenre")).clear();
        driver.findElement(By.id("inputGenre")).sendKeys(new_book.getGenre()+ "_new");
        driver.findElement(By.id("inputPubHouse")).clear();
        driver.findElement(By.id("inputPubHouse")).sendKeys(new_book.getPub_house()+ "_new");
        driver.findElement(By.id("inputYear")).clear();
        driver.findElement(By.id("inputYear")).sendKeys(String.valueOf(new_book.getPub_year() + 5));
        driver.findElement(By.id("inputPages")).clear();
        driver.findElement(By.id("inputPages")).sendKeys(String.valueOf(new_book.getNum_pages()+ 5));
        driver.findElement(By.id("inputCover")).clear();
        driver.findElement(By.id("inputCover")).sendKeys(new_book.getCover_type()+ "_new");
        driver.findElement(By.id("inputPrice")).clear();
        driver.findElement(By.id("inputPrice")).sendKeys(String.valueOf(new_book.getPrice()+ 5));
        driver.findElement(By.id("inputAmount")).clear();
        driver.findElement(By.id("inputAmount")).sendKeys(String.valueOf(new_book.getAmount()+ 5));

        button = wait.until(visibilityOfElementLocated(By.id("save_edit_book_button")));
        button.click();
        wait.until(stalenessOf(button));

        wait.until(visibilityOfElementLocated(By.id("book_root_page_button")));
        Assert.assertEquals(driver.getTitle(), "Book admin info");

        book = bookService.readBooksListByTitle(new_book.getTitle() + "_new").get(0);
        new_book.setBook_id(book.getBook_id());

        button = wait.until(visibilityOfElementLocated(By.id("book_admin_delete_button")));
        button.click();
        wait.until(stalenessOf(button));

        wait.until(visibilityOfElementLocated(By.id("LAindex_root_page_button")));
        Assert.assertEquals(driver.getTitle(), "Main Page for admin");

        System.out.println(new_book.getBook_id());

        Books deleted_book = bookService.readBookByID(new_book.getBook_id());
        Assert.assertEquals(deleted_book.getDeleted_book(), true);

    }

    @Test(priority=13)
    public void indexAddCustomerButtonTest() {
        driver.get(URL);

        WebElement button = wait.until(visibilityOfElementLocated(By.id("LAindex_add_customer_button")));
        Assert.assertEquals(driver.getTitle(), "Main Page for admin");
        button.click();
        wait.until(stalenessOf(button));

        wait.until(visibilityOfElementLocated(By.id("add_customer_root_page_button")));
        Assert.assertEquals(driver.getTitle(), "Add Customer");

        driver.findElement(By.id("validationDefaultUsername")).sendKeys(new_customer.getCustomer_login() + "1");
        driver.findElement(By.id("inputPassword")).sendKeys(new_customer.getCustomer_password());
        driver.findElement(By.id("validationDefaultFirstName")).sendKeys(new_customer.getFirstname());
        driver.findElement(By.id("validationDefaultSecondName")).sendKeys(new_customer.getLastname());
        driver.findElement(By.id("validationDefaultAddress")).sendKeys(String.valueOf(new_customer.getAddress()));
        driver.findElement(By.id("validationDefaultPhone")).sendKeys(String.valueOf(new_customer.getPhone_number()));
        driver.findElement(By.id("validationDefaultMail")).sendKeys(new_customer.getMail());

        button = wait.until(visibilityOfElementLocated(By.id("add_customer_button")));
        button.click();
        wait.until(stalenessOf(button));

        wait.until(visibilityOfElementLocated(By.id("LAindex_add_book_button")));
        Assert.assertEquals(driver.getTitle(), "Main Page for admin");

        Customers check_customer = customersService.readCustomerByLogin(new_customer.getCustomer_login() + "1");
        Assert.assertEquals(new_customer.getCustomer_login()+ "1", check_customer.getCustomer_login() );
    }

    @AfterClass
    public void end() {
        Customers customer = customersService.readCustomerByLogin(new_customer.getCustomer_login());
        Customers customer1 = customersService.readCustomerByLogin(new_customer.getCustomer_login() + "1");
        Orders order = ordersService.readOrdersByCustomerId(customer).get(0);
        Books book = bookService.readBookByID(new_book.getBook_id());
        bookService.deleteBook(book);
        ordersService.deleteOrder(order);
        customersService.deleteCustomerForever(customer);
        customersService.deleteCustomerForever(customer1);
        driver.quit();
    }
}
