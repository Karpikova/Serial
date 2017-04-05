package library.Utils;

import library.Modules.Book;
import library.Modules.Booking;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by makar on 05.04.2017.
 */
public class DataManager {
    //make generic
    public static void SrializeToFile(Set<Book> books) {
        try (FileOutputStream fos = new FileOutputStream("books.txt");
             ObjectOutputStream oos = new ObjectOutputStream(fos)){
            for (Book book:
                    books) {
                oos.writeObject(book);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Set<Book> DeSrialize() {
        Set<Book> books = new HashSet<>();
        try (FileInputStream fis = new FileInputStream("books.txt");
             ObjectInputStream ois = new ObjectInputStream(fis)){
            Book book;
            while ((book = (Book) ois.readObject())!=null)
            {
                books.add(book);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            return books;
        }
    }

    public static void MySrializeToFile(Set<Booking> bookings) {
        try (FileOutputStream fos = new FileOutputStream("books.txt");
             ObjectOutputStream oos = new ObjectOutputStream(fos)){
            for (Booking booking:
                    bookings) {
                oos.writeObject(booking);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Set<Booking> MyDeSrialize() {
        Set<Booking> bookings = new HashSet<>();
        try (FileInputStream fis = new FileInputStream("books.txt");
             ObjectInputStream ois = new ObjectInputStream(fis)){
            Booking booking;
            while ((booking = (Booking) ois.readObject())!=null)
            {
                bookings.add(booking);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            return bookings;
        }
    }
}