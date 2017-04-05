package library.Modules;

import java.io.Serializable;

/**
 * Created by makar on 05.04.2017.
 */
public class Book implements Serializable{

    private String author;
    private String title;
    private int year;
    private String isbn;
    private static long serialVersionUID = 2L;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;

    }

    public Book(String author, String title, int year, String isbn) {
        this.author = author;
        this.title = title;
        this.year = year;
        this.isbn = isbn;
    }

    @Override
    public int hashCode() {
        return isbn.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
        {
            return false;
        }
        if (!(obj instanceof Book))
        {
            return false;
        }
        if (!this.isbn.equals(((Book) obj).isbn))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return author + "@" + title + "@" + isbn + "@" + isbn;
    }
}
