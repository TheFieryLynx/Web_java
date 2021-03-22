package Services;

import Models.Admin;
import Models.Books;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

import static org.testng.Assert.*;

public class BooksServiceTest {

    @Test
    public void testCreateBook() {
        BooksService bookService = new BooksService();
        Books new_book = new Books(869, 21, "Воин Доброй Удачи", "Бэккер Р. Скотт", "Героическое зарубежное фэнтези", "Азбука", 2021, 780, "Мягкая бумажная");
        bookService.createBook(new_book);

        Books check_book = bookService.readBookByID(new_book.getBook_id());

        Assert.assertEquals(new_book, check_book);

        bookService.deleteBook(new_book);
    }

    @Test
    public void testDeleteBook() {
        BooksService bookService = new BooksService();
        Books new_book = new Books(869, 21, "Воин Доброй Удачи", "Бэккер Р. Скотт", "Героическое зарубежное фэнтези", "Азбука", 2021, 780, "Мягкая бумажная");
        bookService.createBook(new_book);
        Books check_book = bookService.readBookByID(new_book.getBook_id());
        assertEquals(new_book, check_book);
        bookService.deleteBook(new_book);

        check_book = bookService.readBookByID(new_book.getBook_id());
        Assert.assertNull(check_book);

    }

    @Test
    public void testUpdateBook() {
        BooksService bookService = new BooksService();
        Books new_book = new Books(869, 21, "Воин Доброй Удачи", "Бэккер Р. Скотт", "Героическое зарубежное фэнтези", "Азбука", 2021, 780, "Мягкая бумажная");
        bookService.createBook(new_book);
        Books check_book = bookService.readBookByID(new_book.getBook_id());
        Assert.assertEquals(new_book, check_book);

        new_book.setGenre("ikea");
        bookService.updateBook(new_book);
        check_book = bookService.readBookByID(new_book.getBook_id());
        Assert.assertEquals(new_book, check_book);
        bookService.deleteBook(new_book);
    }

    @Test
    public void testReadBookByID() {
        BooksService bookService = new BooksService();
        Books new_book = new Books(869, 21, "Воин Доброй Удачи", "Бэккер Р. Скотт", "Героическое зарубежное фэнтези", "Азбука", 2021, 780, "Мягкая бумажная");
        bookService.createBook(new_book);
        Books check_book = bookService.readBookByID(new_book.getBook_id());
        Assert.assertEquals(new_book.getBook_id(), check_book.getBook_id());
        bookService.deleteBook(new_book);
    }

    @Test
    public void testReadBooksListByGenre() {
        BooksService bookService = new BooksService();
        Set<Books> expected_list = Set.of(
                new Books(544, 2, "1984. Скотный двор", "Оруэлл Джордж", "Классическая зарубежная проза", "АСТ", 2020, 481, "Твердая бумажная"),
                new Books(354, 1, "Сага о Форсайтах", "Голсуорси Джон", "Классическая зарубежная проза", "Росмэн", 2018, 532, "Твердая бумажная"),
                new Books(467, 68, "Зов предков. Белый клык", "Лондон Джек", "Классическая зарубежная проза", "Азбука", 2010, 354, "Мягкая бумажная"),
                new Books(467, 68, "Зов предков. Белый клык 2", "Лондон Джек", "Классическая зарубежная проза", "Азбука", 2010, 354, "Мягкая бумажная"),
                new Books(467, 68, "Зов предков. Белый клык 3", "Лондон Джек", "Классическая зарубежная проза", "Азбука", 2010, 354, "Мягкая бумажная"),
                new Books(467, 68, "Зов предков. Белый клык 4", "Лондон Джек", "Классическая зарубежная проза", "Азбука", 2010, 354, "Мягкая бумажная")
        );
        List<Books> list_of_books = bookService.readBooksListByGenre("Классическая зарубежная проза");
        Assert.assertEquals(list_of_books.size(), expected_list.size());
        Assert.assertTrue(expected_list.contains(list_of_books.get(0)));
        Assert.assertTrue(expected_list.contains(list_of_books.get(1)));
        Assert.assertTrue(expected_list.contains(list_of_books.get(2)));
    }

    @Test
    public void testReadBooksListByPubHouse() {
        BooksService bookService = new BooksService();
        Set<Books> expected_list = Set.of(
                new Books(467, 68, "Зов предков. Белый клык", "Лондон Джек", "Классическая зарубежная проза", "Азбука", 2010, 354, "Мягкая бумажная"),
                new Books(467, 68, "Зов предков 2. Белый клык", "Лондон Джек", "Классическая зарубежная проза", "Азбука", 2010, 354, "Мягкая бумажная"),
                new Books(467, 68, "Зов предков 3. Белый клык", "Лондон Джек", "Классическая зарубежная проза", "Азбука", 2010, 354, "Мягкая бумажная"),
                new Books(467, 68, "Зов предков 4. Белый клык", "Лондон Джек", "Классическая зарубежная проза", "Азбука", 2010, 354, "Мягкая бумажная"),
                new Books(869, 21, "Воин Доброй Удачи", "Бэккер Р. Скотт", "Героическое зарубежное фэнтези", "Азбука", 2021, 780, "Мягкая бумажная")
        );
        List<Books> list_of_books = bookService.readBooksListByPubHouse("Азбука");
        Assert.assertEquals(list_of_books.size(), expected_list.size());
        Assert.assertTrue(expected_list.contains(list_of_books.get(0)));
        Assert.assertTrue(expected_list.contains(list_of_books.get(1)));
        Assert.assertTrue(expected_list.contains(list_of_books.get(2)));
        Assert.assertTrue(expected_list.contains(list_of_books.get(3)));
        Assert.assertTrue(expected_list.contains(list_of_books.get(4)));
    }

    @Test
    public void testReadBooksListByAuthor() {
        BooksService bookService = new BooksService();
        Set<Books> expected_list = Set.of(
                new Books(467, 68, "Зов предков. Белый клык", "Лондон Джек", "Классическая зарубежная проза", "Азбука", 2010, 354, "Мягкая бумажная"),
                new Books(467, 68, "Зов предков 2. Белый клык", "Лондон Джек", "Классическая зарубежная проза", "Азбука", 2010, 354, "Мягкая бумажная"),
                new Books(467, 68, "Зов предков 3. Белый клык", "Лондон Джек", "Классическая зарубежная проза", "Азбука", 2010, 354, "Мягкая бумажная"),
                new Books(467, 68, "Зов предков 4. Белый клык", "Лондон Джек", "Классическая зарубежная проза", "Азбука", 2010, 354, "Мягкая бумажная")
        );
        List<Books> list_of_books = bookService.readBooksListByAuthor("Лондон Джек");
        Assert.assertEquals(list_of_books.size(), expected_list.size());
        Assert.assertTrue(expected_list.contains(list_of_books.get(0)));
        Assert.assertTrue(expected_list.contains(list_of_books.get(1)));
        Assert.assertTrue(expected_list.contains(list_of_books.get(2)));
        Assert.assertTrue(expected_list.contains(list_of_books.get(3)));
    }

    @Test
    public void testReedBookAmount() {
        BooksService bookService = new BooksService();
        Books new_book = new Books(869, 1010101, "Воин Доброй Удачи", "Бэккер Р. Скотт", "Героическое зарубежное фэнтези", "Азбука", 2021, 780, "Мягкая бумажная");
        bookService.createBook(new_book);
        Assert.assertEquals(bookService.reedBookAmount(new_book), new_book.getAmount());
        bookService.deleteBook(new_book);
    }

    @Test
    public void testReadBookPrice() {
        BooksService bookService = new BooksService();
        Books new_book = new Books(2020202, 1010101, "Воин Доброй Удачи", "Бэккер Р. Скотт", "Героическое зарубежное фэнтези", "Азбука", 2021, 780, "Мягкая бумажная");
        bookService.createBook(new_book);
        Assert.assertEquals(bookService.reedBookPrice(new_book), new_book.getPrice());
        bookService.deleteBook(new_book);
    }

//    @Test
//    public void testUpdateBooksPrice() {
//
//    }
//
//    @Test
//    public void testUpdateBooksAmount() {
//    }
//
//    @Test
//    public void testUpdateBooksTitle() {
//    }
//
//    @Test
//    public void testUpdateBooksAuthor() {
//    }
//
//    @Test
//    public void testUpdateBooksGenre() {
//    }
//
//    @Test
//    public void testUpdateBooksPubHouse() {
//    }
//
//    @Test
//    public void testUpdateBooksPubYear() {
//    }
//
//    @Test
//    public void testUpdateBooksNumPages() {
//    }
//
//    @Test
//    public void testUpdateBooksCoverType() {
//    }
}