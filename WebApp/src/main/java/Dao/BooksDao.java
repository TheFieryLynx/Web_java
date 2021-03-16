package Dao;
import Models.Books;

public interface BooksDao {
    public void create(Books book);
    public void update(Books book);
    public void delete(Books book);
}
