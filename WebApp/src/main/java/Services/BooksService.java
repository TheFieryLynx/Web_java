package Services;

import Dao.BooksDao;
import Dao.Impl.BooksDaoImpl;
import Models.Books;

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
}
