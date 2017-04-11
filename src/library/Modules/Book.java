package library.Modules;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
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
            dBuilder = dbFactory.newDocumentBuilder();

            Element rootElement = doc.createElement("Books");
            doc.appendChild(rootElement);

            for (Book book :
                    books) {
                Element superbook = doc.createElement("Book");
                rootElement.appendChild(superbook);

                Element methods = doc.createElement("Methods");
                superbook.appendChild(methods);

                for (Method met :
                        this.getClass().getMethods()) {

                    Element method = doc.createElement("Method");
                    methods.appendChild(method);

                    Attr attr = doc.createAttribute("name");
                    attr.setValue(met.getName());
                    method.setAttributeNode(attr);

                    attr = doc.createAttribute("return_type");
                    attr.setValue(met.getReturnType().getName());
                    method.setAttributeNode(attr);

                    for (Parameter param: met.getParameters()) {
                        Element m_param = doc.createElement("mParametr");
                        method.appendChild(m_param);

                        attr = doc.createAttribute("name");
                        attr.setValue(param.getName());
                        m_param.setAttributeNode(attr);

                        attr = doc.createAttribute("type");
                        attr.setValue(param.getType().getName());
                        m_param.setAttributeNode(attr);
                    }
                }

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
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = null;
            try {
                transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                DOMSource source = new DOMSource(doc);
                StreamResult result =
                        new StreamResult(new File("Books.xml"));
                transformer.transform(source, result);
                // Output to console for testing
                StreamResult consoleResult =
                        new StreamResult(System.out);
                transformer.transform(source, consoleResult);
            } catch (TransformerConfigurationException e) {
                e.printStackTrace();
            } catch (TransformerException e) {
                e.printStackTrace();
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
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
