import Models.Books;
import Models.Customers;
import Services.BooksService;
import Services.CustomersService;
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

        new_customer.setCustomer_password(new_customer.getFirstname() + "_new");
        new_customer.setCustomer_password(new_customer.getLastname() + "_new");
        new_customer.setCustomer_password(new_customer.getAddress() + "_new");
        new_customer.setCustomer_password(new_customer.getPhone_number() + "_new");
        new_customer.setCustomer_password(new_customer.getMail() + "_new");

        driver.findElement(By.id("inputFirstName")).clear();
        driver.findElement(By.id("inputFirstName")).sendKeys(new_customer.getFirstname());
        driver.findElement(By.id("inputLastName")).clear();
        driver.findElement(By.id("inputLastName")).sendKeys(new_customer.getLastname());
        driver.findElement(By.id("inputAddress")).clear();
        driver.findElement(By.id("inputAddress")).sendKeys(new_customer.getAddress());
        driver.findElement(By.id("inputPhoneNumber")).clear();
        driver.findElement(By.id("inputPhoneNumber")).sendKeys(new_customer.getPhone_number());
        driver.findElement(By.id("inputMail")).clear();
        driver.findElement(By.id("inputMail")).sendKeys(new_customer.getMail());

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
        Assert.assertTrue(customerInfoTable.contains(check_customer.getCustomer_login()));
        Assert.assertTrue(customerInfoTable.contains(check_customer.getCustomer_password()));
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


    }

    @AfterClass
    public void end() {
        Customers customer = customersService.readCustomerByLogin(new_customer.getCustomer_login());
        customersService.deleteCustomerForever(customer);
        driver.quit();
    }

}
