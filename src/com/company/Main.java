package com.company;

import library.Library;
import library.Modules.Book;
import library.Modules.BookInstance;
import library.Modules.Booking;
import library.Modules.Reader;
import library.Utils.DataManager;

import javax.xml.transform.TransformerException;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Book book1 = new Book("Bulgacov", "Dog Heart", 1925, "12");
//        Set<Book> bookset = new HashSet<Book>();
//        bookset.add(book1);
//        book1.printXML(bookset);

        BookInstance bookInstance = new BookInstance(book1, UUID.randomUUID());
        Set<BookInstance> bookInstance_set = new HashSet<BookInstance>();
        bookInstance_set.add(bookInstance);
        bookInstance.printXML(bookInstance_set);
       // Booking booking = new Booking(bookInstance, new Reader("Elena", "Sergeevna", "Bulgacova", 101010), new Date(), new Date());





        Library library = new Library();
       //* Set<Book> DeSerial = DataManager.DeSrializeGeneric(new HashSet<Book>());
       /* for (Book book:
                DataManager.DeSrialize("books.txt")) {
            library.buyBook(book.getTitle(), book.getAuthor(), book.getIsbn(), 1, book.getYear());
        }
        library.setCatalog( DataManager.DeSrialize("books.txt"));*/

        Reader john = new Reader("John", "Connor", "Caiilovish", 10101010101010l);
        Reader sara = new Reader("Sara", "Connor", "Human", 10101010101012l);

 //       library.buyBook("May", "F", "2", 3, 1990);
   //     library.buyBook("Pair", "M", "3", 4, 1992);

        library.takeBook("John", "Connor", "Caiilovish", 10101010101010l, "May");
        library.takeBook("John", "Connor", "Caiilovish", 10101010101010l, "Pair");

        library.returnBook("John", "Connor", "Caiilovish", 10101010101010l, "May");

        library.ShowAllData();

  //    DataManager.SrializeToFileGeneric(library.getCatalog());
    }
}
