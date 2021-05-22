package Dao;
import Models.Books;
import Models.Customers;

import java.util.List;

public interface BooksDao {
    void create(Books book);
    void update(Books book);
    void delete(Books book);
    Books readByID(int id);
    List<Books> readListByGenre(String genre);
    List<Books> readListByAuthor(String author);
    List<Books> readListByTitle(String title);
    List<Books> readListByPubHouse(String pub_house);
    void deleteBook(Books book);
    int bookAmount(Books book);
    double bookPrice(Books book);
    List<Books> AllBooks();
}
