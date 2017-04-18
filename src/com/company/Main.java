package com.company;

import library.Library;
import library.Modules.*;
import library.Utils.DataManager;
import library.Utils.JarClassLoader;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Main {

  //  первый логгер
    static {
        PropertyConfigurator.configure("log4j.properties");
    }

    private static final Logger logger =  Logger.getLogger(Main.class);

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {

        JarClassLoader jarClassLoader = new JarClassLoader();
        Class Ticket = null;
        try {
            Ticket = jarClassLoader.loadClass("Ticket");
            Object ticket = Ticket.newInstance();
            Method method = Ticket.getMethod("mes");
            System.out.println(method.invoke(ticket));//Исполняется.
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        JarClassLoader jarClassLoaderReader = new JarClassLoader();
        Class Reader = null;
        try {
            jarClassLoader.loadClass("MyReader");
            Object ticket = Ticket.newInstance();
            Method method = Ticket.getMethod("mes");
            System.out.println(method.invoke(ticket));//Исполняется.
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        if(true) return;

        DataBaseManager dataBaseManager = new DataBaseManager();
        dataBaseManager.delete();

        Book book = new Book("Bulgacov", "Dog Heart", 1925, "12");
        Set<Book> bookset = new HashSet<Book>();
        bookset.add(book);
        book.printXML(bookset);

        BookInstance bookInstance = new BookInstance(book, UUID.randomUUID());
        bookInstance.printXML(bookInstance);
       // Booking booking = new Booking(bookInstance, new Reader("Elena", "Sergeevna", "Bulgacova", 101010), new Date(), new Date());


        Library library = new Library();
       // Set<Book> DeSerial = DataManager.DeSrializeGeneric(new HashSet<Book>());
       /* for (Book book:
                DataManager.DeSrialize("books.txt")) {
            library.buyBook(book.getTitle(), book.getAuthor(), book.getIsbn(), 1, book.getYear());
        }
        library.setCatalog( DataManager.DeSrialize("books.txt"));*/

        Reader john = new Reader("John", "Connor", "Caiilovish", 10101010101010l);
        Reader sara = new Reader("Sara", "Connor", "Human", 10101010101012l);

        library.buyBook("May", "F", "2", 3, 1990);
        library.buyBook("Pair", "M", "3", 4, 1992);

        library.takeBook("John", "Connor", "Caiilovish", 10101010101010l, "May");
        library.takeBook("John", "Connor", "Caiilovish", 10101010101010l, "Pair");

        library.returnBook("John", "Connor", "Caiilovish", 10101010101010l, "May");

        library.ShowAllData();

      DataManager.SrializeToFile(library.getCatalog());
    }
}
