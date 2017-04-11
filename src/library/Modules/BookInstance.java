package library.Modules;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

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
            dBuilder = dbFactory.newDocumentBuilder();

            Element rootElement = doc.createElement("BooksInstanses");
            doc.appendChild(rootElement);

            for (BookInstance bookinstance :
                    bookInstances) {
                Element superbook = doc.createElement("BookInstance");
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

//                    if (field_el.getName().equals("book")) {
//                        try {
//                            field_el.get(this).
//                        } catch (IllegalAccessException e) {
//                            e.printStackTrace();
//                        }
//                    }
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
}
