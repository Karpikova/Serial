package com.company;

import library.Library;
import library.Modules.Book;
import library.Modules.Reader;
import library.Utils.DataManager;

public class Main {

    public static void main(String[] args) {
        Library library = new Library();

        for (Book book:
                DataManager.DeSrialize()) {
            library.buyBook(book.getTitle(), book.getAuthor(), book.getIsbn(), 1, book.getYear());
        }
        library.setCatalog(DataManager.DeSrialize());

        Reader john = new Reader("John", "Connor", "Caiilovish", 10101010101010l);
        Reader sara = new Reader("Sara", "Connor", "Human", 10101010101012l);

    //    library.buyBook("May", "F", "2", 3, 1990);
    //    library.buyBook("Pair", "M", "3", 4, 1992);

        library.takeBook("John", "Connor", "Caiilovish", 10101010101010l, "May");
        library.takeBook("John", "Connor", "Caiilovish", 10101010101010l, "Pair");

        library.returnBook("John", "Connor", "Caiilovish", 10101010101010l, "May");

        library.ShowAllData();

        DataManager.SrializeToFile(library.getCatalog());
    }
}
