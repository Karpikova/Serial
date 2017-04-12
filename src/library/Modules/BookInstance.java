package library.Modules;

import library.Utils.DataManager;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

/**
 * Created by makar on 05.04.2017.
 */
public class BookInstance {
    private Book book;

    public Book getBook() {
        return book;
    }

    private UUID number;
    private List<Booking> bookingHistory;

    public UUID getNumber() {
        return number;
    }

    public List<Booking> getBookingHistory() {
        return bookingHistory;
    }

    public BookInstance(Book book, UUID number) {
        this.book = book;
        this.number = number;
        bookingHistory = new ArrayList<>(32);
    }

    @Override
    public int hashCode() {
        return number.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (!(obj instanceof  BookInstance))
            return false;

        if (!this.number.equals(((BookInstance) obj).number))
            return false;

        return true;
    }

    @Override
    public String toString() {
        return book + "@" + number;
    }

    public void printXML(Set<BookInstance> bookInstances) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = (Document) dBuilder.newDocument();

            Element rootElement = doc.createElement("BooksInstanses");
            doc.appendChild(rootElement);

            for (BookInstance bookinstance :
                    bookInstances) {
                Element superbook = doc.createElement("BookInstance");
                rootElement.appendChild(superbook);

                DataManager.createXMLMethod(this, doc, superbook);

                Element fields = doc.createElement("Fields");
                superbook.appendChild(fields);

                for (Field field_el :
                        this.getClass().getDeclaredFields()) {

                    Element field = doc.createElement("Field");
                    fields.appendChild(field);

                    Attr attr = doc.createAttribute("name");
                    attr.setValue(field_el.getName());
                    field.setAttributeNode(attr);

                    attr = doc.createAttribute("value");
                    try {
                        attr.setValue(field_el.get(this).toString());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    field.setAttributeNode(attr);

                    if (field_el.getName().equals("book")) {
                        try {
                            Book book = (Book) field_el.get(this);
                            Book.createXML_just_book(doc, field, book);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            DataManager.createXMLResult("BookInstance.txt", doc);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
}
