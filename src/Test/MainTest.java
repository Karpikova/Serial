package Test;

import library.Library;
import library.Modules.Book;
import library.Modules.BookInstance;
import library.Modules.Booking;
import library.Modules.Reader;
import library.Utils.Class_Implement;
import library.Utils.DataManager;
import library.Utils.ProxyForTests;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Proxy;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/*
 * ${Classname}
 * 
 * Version 1.0 
 * 
 * 10.04.2017
 * 
 * Karpikova
 */
class MainTest {

    @Test
    public void buyBookTestCatalog(){
        library.buyBook("Pair", "M", "3", 4, 1992);
        assertEquals(1, library.getCatalog().size());
        Book book = new Book("M", "Pair", 1992, "3");
        assertTrue(library.getCatalog().contains(book));
        Book bookFromCatalog = library.getCatalog().iterator().next();
        assertTrue(book.getTitle().equals(bookFromCatalog.getTitle()));
        assertTrue(book.getAuthor().equals(bookFromCatalog.getAuthor()));
        assertTrue(book.getYear() == bookFromCatalog.getYear());
        assertTrue(book.getIsbn().equals(bookFromCatalog.getIsbn()));

    }

    @Test
    public void buyBookTestStore(){
        library.buyBook("Pair",  "M", "3", 100, 1992);
        assertEquals(100, library.getStore().size());
        Book book = new Book("M", "Pair", 1992, "3");
        for (BookInstance instance:
             library.getStore()) {
            Book bookFromStore = instance.getBook();
            assertTrue(book.getTitle().equals(bookFromStore.getTitle()));
            assertTrue(book.getAuthor().equals(bookFromStore.getAuthor()));
            assertTrue(book.getYear() == bookFromStore.getYear());
            assertTrue(book.getIsbn().equals(bookFromStore.getIsbn()));
        }
    }

    @Test
    public void takeBookTestReaders(){
        library.takeBook("John", "Connor", "Caiilovish", 10101010101010l, "IDoNotExsist");
        assertEquals(0, library.getBookings().size());
        assertEquals(1, library.getReaders().size());

        library.buyBook("Pair",  "M", "3", 100, 1992);
        library.takeBook("John", "Connor", "Caiilovish", 10101010101010l, "Pair");
        assertEquals(1, library.getReaders().size());
        library.takeBook("John", "Connor", "Caiilovish", 10101010101010l, "Pair");
        assertEquals(1, library.getReaders().size());

        Reader reader = new Reader("John", "Connor", "Caiilovish", 10101010101010l);
        assertTrue(library.getReaders().contains(reader));

        Reader readerFromBase = library.getReaders().iterator().next();

        assertTrue(reader.getFirstname().equals(readerFromBase.getFirstname()));
        assertTrue(reader.getLastname().equals(readerFromBase.getLastname()));
        assertTrue(reader.getPassportNumber() == readerFromBase.getPassportNumber());
        assertTrue(reader.getSecondname().equals(readerFromBase.getSecondname()));


        assertEquals(2, library.getBookings().size());
        assertEquals(98, library.getStore().size());

        for (Booking instance:
                library.getBookings()) {
            assertTrue(instance.getBookInstance().getBook().getIsbn().equals("3")); //We need to have a method "CreateBook" and check booking in there
            assertTrue(instance.getReader().equals(reader));
            assertTrue(instance.getFinishDate().equals(instance.getReturnDate()));
        }
    }

    @Test
    public void deSerealizeTest(){

        Interface_InputStream is = (Interface_InputStream) Proxy.newProxyInstance(
                Class_Implement.class.getClassLoader(),
                Class_Implement.class.getInterfaces(),
                new ProxyForTests());
        is.read();

    }

    private static Library library;

    @BeforeAll
    public static void init(){
        library = new Library();
    }

    @AfterEach
    public void clearAll() {
        library = new Library();
    }
}