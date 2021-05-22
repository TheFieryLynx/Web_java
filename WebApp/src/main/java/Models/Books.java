package Models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "books")
public class Books
{
    public Books(double price,
                     int amount, String title, String author,
                        String genre, String pub_house, int pub_year,
                            int num_pages, String cover_type, boolean deleted_book) {
        this.price = price;
        this.amount = amount;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.pub_house = pub_house;
        this.pub_year = pub_year;
        this.num_pages = num_pages;
        this.cover_type = cover_type;
        this.deleted_book = deleted_book;

    }

    @Override
    public int hashCode() {
        return Objects.hash(price, amount, title, author, genre, pub_house,
                                pub_year, num_pages, cover_type, deleted_book);
    }

    public Books() {    }

    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPub_house() {
        return pub_house;
    }

    public void setPub_house(String pub_house) {
        this.pub_house = pub_house;
    }

    public int getPub_year() {
        return pub_year;
    }

    public void setPub_year(int pub_year) {
        this.pub_year = pub_year;
    }

    public int getNum_pages() {
        return num_pages;
    }

    public void setNum_pages(int num_pages) {
        this.num_pages = num_pages;
    }

    public String getCover_type() {
        return cover_type;
    }

    public void setCover_type(String cover_type) {
        this.cover_type = cover_type;
    }

    public void setDeleted_book(boolean deleted_book) {
        this.deleted_book = deleted_book;
    }

    public boolean getDeleted_book() {
        return deleted_book;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj.getClass() != this.getClass()) { return false; }
        final Books other = (Books) obj;
        return  (Double.compare(this.price, other.price) == 0) &&
                (this.amount == other.amount) &&
                (this.title.equals(other.title)) &&
                (this.author.equals(other.author)) &&
                (this.genre.equals(other.genre)) &&
                (this.pub_house.equals(other.pub_house)) &&
                (this.pub_year == other.pub_year) &&
                (this.num_pages == other.num_pages) &&
                (this.deleted_book == other.deleted_book) &&
                (this.cover_type.equals(other.cover_type));
    }

    private int book_id;
    private double price;
    private int amount;
    private String title;
    private String author;
    private String genre;
    private String pub_house;
    private int pub_year;
    private int num_pages;
    private String cover_type;
    private boolean deleted_book;
}
