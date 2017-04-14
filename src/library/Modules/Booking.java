package library.Modules;

import library.Utils.DataManager;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.Set;

/**
 * Created by makar on 05.04.2017.
 */
public class Booking{
    private BookInstance bookInstance;
    private Reader reader;
    private Date startDate;
    private Date returnDate;
    private Date finishDate;

    public Date getStartDate() {
        return startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    @Override
    public int hashCode() {
        return bookInstance.hashCode() + reader.hashCode() + startDate.hashCode();
    }

    public BookInstance getBookInstance() {
        return bookInstance;
    }

    public Reader getReader() {
        return reader;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;


        if (!(obj instanceof  Booking))
            return false;

        if (!(this.bookInstance.equals(((Booking) obj).bookInstance))
            || !(this.reader.equals(((Booking) obj).reader))
                || !(this.startDate.equals(startDate)))
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookInstance=" + bookInstance +
                ", reader=" + reader +
                ", startDate=" + startDate +
                ", returnDate=" + returnDate +
                ", finishDate=" + finishDate +
                '}';
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Booking(BookInstance bookInstance, Reader reader, Date startDate, Date finishDate) {

        this.bookInstance = bookInstance;
        this.reader = reader;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public void printXML(Booking booking) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = (Document) dBuilder.newDocument();

            createXML_just_booking(doc, booking);

            DataManager.createXMLResult("Booking.xml", doc);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    private Document createXML_just_booking(Document doc, Booking booking) {
        Element rootElement = doc.createElement("Booking");
        doc.appendChild(rootElement);

        DataManager.createXMLMethod(booking, doc, rootElement);

        Element fields = doc.createElement("Fields");
        rootElement.appendChild(fields);

        for (Field field_el :
                booking.getClass().getDeclaredFields()) {

            Element field = doc.createElement("Field");
            fields.appendChild(field);

            Attr attr = doc.createAttribute("name");
            attr.setValue(field_el.getName());
            field.setAttributeNode(attr);

            attr = doc.createAttribute("value");
            try {
                attr.setValue(field_el.get(booking).toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            field.setAttributeNode(attr);

            if (field_el.getName().equals("reader")) {
                try {
                    Reader reader = (Reader) field_el.get(booking);

                   // attr = doc.createAttribute("ReaderPassport");
                   // attr.setValue( Long.parseLong(reader.getPassportNumber()));
                   // field.setAttributeNode(attr);

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return doc;
    }

}
