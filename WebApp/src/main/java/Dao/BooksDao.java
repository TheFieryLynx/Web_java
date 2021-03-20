package Dao;
import Models.Books;

import java.util.List;

public interface BooksDao {
    void create(Books book);
    void update(Books book);
    void delete(Books book);
    Books readByID(int id);
    List<Books> readListByGenre(String genre);
    List<Books> readListByAuthor(String author);
    List<Books> readListByPubHouse(String pub_house);
    int bookAmount(Books book);
    double bookPrice(Books book);
}
