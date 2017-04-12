package library.Modules;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import library.Utils.DataManager;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Set;

/**
 * Created by makar on 05.04.2017. Externalizable
 */
public class Book implements Serializable{
    public Book() {
    }

    private String author;
    private String title;
    private int year;
    private String isbn;
    private static long serialVersionUID = 2L;

    public void print() throws ClassNotFoundException {
        System.out.println(Book.class.getCanonicalName());
        for (Method met:
             this.getClass().getMethods()) {
            System.out.println(met.getName());
            System.out.println(met.getReturnType().getName());
            for (Parameter param: met.getParameters()) {
                System.out.println(param.getName() + " " + param.getType().getName());
            }
            System.out.println(met.getModifiers());
        }
        Book book = new Book();
            for (Field field:
                 Class.forName("library.Modules.Book").getDeclaredFields()) {
                System.out.println(field.getName());
                System.out.println(field.getType().getName());
                System.out.println(field.isAccessible());
            }
    }

    public void printXML(Set<Book> books) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = (Document) dBuilder.newDocument();

            Element rootElement = doc.createElement("Books");
            doc.appendChild(rootElement);

            for (Book book :
                    books) {
                createXML_just_book(doc, rootElement, book);
            }

            DataManager.createXMLResult("Book.txt", doc);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static Document createXML_just_book(Document doc, Element prev, Book book) {
        Element superbook = doc.createElement("Book");
        prev.appendChild(superbook);

        DataManager.createXMLMethod(book, doc, superbook);

        Element fields = doc.createElement("Fields");
        superbook.appendChild(fields);

        for (Field field_el :
                book.getClass().getDeclaredFields()) {

            Element field = doc.createElement("Field");
            fields.appendChild(field);

            Attr attr = doc.createAttribute("name");
            attr.setValue(field_el.getName());
            field.setAttributeNode(attr);

            attr = doc.createAttribute("value");
            try {
                attr.setValue(field_el.get(book).toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            field.setAttributeNode(attr);
        }

        return doc;
    }

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
