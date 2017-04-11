package library.Utils;

import library.Modules.Book;
import library.Modules.Booking;
import sun.misc.IOUtils;

import java.io.*;
import java.util.Collection;
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

    public static Set<Book> DeSrialize(String content) {
        Set<Book> books = new HashSet<>();
        try (InputStream is = new ByteArrayInputStream(content.getBytes());
             ObjectInputStream ois = new ObjectInputStream(is)){
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

 /*   public static < E > void SrializeToFileGeneric(Collection<E> eObjects) {
        try (FileOutputStream fos = new FileOutputStream("books.txt");
             ObjectOutputStream oos = new ObjectOutputStream(fos)){
            for (E eObj:
                    eObjects) {
                oos.writeObject(eObj);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <E> Set<E> DeSrializeGeneric(Collection<E> eObjects) {

        try (FileInputStream fis = new FileInputStream("books.txt");
             ObjectInputStream ois = new ObjectInputStream(fis)){
            E eObj;
            while ((eObj = (E) ois.readObject())!=null)
            {
                eObjects.add(eObj);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            return (Set<E>)eObjects;
        }
    }*/

}
