package library;

import library.Modules.*;

import java.util.*;

/**
 * Created by makar on 05.04.2017.
 */
public class Library {
    private Set<Book> catalog;
    private Set<BookInstance> store;
    private Set<Reader> readers;
    private Set<Booking> bookings;

    public Set<Book> getCatalog() {
        return catalog;
    }

    public Library() {
        catalog = new HashSet<>(1024);
        store = new HashSet<>(4096);//&&&???
        readers = new HashSet<>(512);
        bookings = new HashSet<>(2048);
    }

    public void buyBook(String title, String author, String isbn, int quantity, int year){
        Book book = new Book(author, title, year, isbn);
        catalog.add(book);
        for (int i = 0; i<quantity; i++)
        {
            BookInstance bookInstance = new BookInstance(book, UUID.randomUUID());
            store.add(bookInstance);
        }
    }

    public void takeBook(String firstname, String secondname, String lastname, final long passportNumber, String title){
        Object[] reader = readers.stream().filter((r)->r.getPassportNumber() == passportNumber).toArray();
        Reader tempReader = null;
        if (reader.length != 0){
            tempReader = (Reader) reader[0];
        }else{
            tempReader = new Reader(firstname, secondname, lastname, passportNumber);
            readers.add(tempReader);
        }

        BookInstance bookInstance = (BookInstance) store.stream().filter((s)->s.getBook().getTitle().equals(title)).toArray()[0];
        if (bookInstance == null)
        {
            System.out.println("no book");
            return;
        }
        Booking booking = new Booking(bookInstance, tempReader, new Date(), new Date());

        bookings.add(booking);
        store.remove(bookInstance);

    }

    public void returnBook(String firstname, String secondname, String lastname, final long passportNumber, String title) {
        Reader reader = new Reader(firstname, secondname, lastname, passportNumber);
        Booking booking = (Booking) bookings.stream().filter((b)->b.getBookInstance().getBook().getTitle().equals(title) &&
                b.getReader().equals(reader)).toArray()[0];

        if (booking == null)
        {
            System.out.println("No such booking");
            return;
        }
        store.add(booking.getBookInstance());
        bookings.remove(booking);
    }

    public void setCatalog(Set<Book> catalog) {
        this.catalog = catalog;
    }

    public void ShowAllData(){
        for (Book book:

             catalog) {
            System.out.println(book);
        }
        for (BookInstance bookInstance:
             store) {
            System.out.println(bookInstance);
        }
        for (Reader reader:
             readers) {
            System.out.println(reader);
        }
        for (Booking booking:
             bookings) {
            System.out.println(booking);
        }
    }
}
