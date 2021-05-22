package Services;

import Dao.BooksDao;
import Dao.Impl.BooksDaoImpl;
import Models.Books;
import Models.Customers;

import java.util.List;

public class BooksService {
    private BooksDao booksDao = new BooksDaoImpl();
    public void createBook(Books book) {
        booksDao.create(book);
    }

    public void deleteBook(Books book) {
        booksDao.delete(book);
    }

    public void updateBook(Books book) {
        booksDao.update(book);
    }

    public Books readBookByID(int id) {
        return booksDao.readByID(id);
    }

    public List<Books> readBooksListByGenre(String genre) {
        return booksDao.readListByGenre(genre);
    }

    public List<Books> readBooksListByAuthor(String author) {
        return booksDao.readListByAuthor(author);
    }

    public List<Books> readBooksListByTitle(String title) {
        return booksDao.readListByTitle(title);
    }

    public List<Books> readAllBooks() {
        return booksDao.AllBooks();
    }

    public List<Books> readBooksListByPubHouse(String pub_house) {
        return booksDao.readListByPubHouse(pub_house);
    }

    public int reedBookAmount(Books book) {
        return booksDao.bookAmount(book);
    }

    public double reedBookPrice(Books book) {
        return booksDao.bookPrice(book);
    }

    public void deleteBookAccount(Books book) {
        booksDao.deleteBook(book);
    }

//    public void updateBooksPrice(Books book, double price) {
//        book.setPrice(price);
//        booksDao.update(book);
//    }
//
//    public void updateBooksAmount(Books book, int amount) {
//        book.setAmount(amount);
//        booksDao.update(book);
//    }
//
//    public void updateBooksTitle(Books book, String title) {
//        book.setTitle(title);
//        booksDao.update(book);
//    }
//
//    public void updateBooksAuthor(Books book, String author) {
//        book.setAuthor(author);
//        booksDao.update(book);
//    }
//
//    public void updateBooksGenre(Books book, String genre) {
//        book.setGenre(genre);
//        booksDao.update(book);
//    }
//
//    public void updateBooksPubHouse(Books book, String pub_house) {
//        book.setPub_house(pub_house);
//        booksDao.update(book);
//    }
//
//    public void updateBooksPubYear(Books book, int pub_year) {
//        book.setPub_year(pub_year);
//        booksDao.update(book);
//    }
//
//    public void updateBooksNumPages(Books book, int num) {
//        book.setNum_pages(num);
//        booksDao.update(book);
//    }
//
//    public void updateBooksCoverType(Books book, String cover) {
//        book.setCover_type(cover);
//        booksDao.update(book);
//    }

}
