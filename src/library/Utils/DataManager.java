package library.Utils;

import library.Modules.Book;
import library.Modules.Booking;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import sun.misc.IOUtils;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
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

    public static Set<Book> DeSrialize(InputStream is) {
        Set<Book> books = new HashSet<>();
        try ( //
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

    public static Document createXMLMethod(Object this_Class, Document doc, Element prev) {

        Element methods = doc.createElement("Methods");
        prev.appendChild(methods);

        for (Method met :
                this_Class.getClass().getMethods()) {

            Element method = doc.createElement("Method");
            prev.appendChild(method);

            Attr attr = doc.createAttribute("name");
            attr.setValue(met.getName());
            method.setAttributeNode(attr);

            attr = doc.createAttribute("return_type");
            attr.setValue(met.getReturnType().getName());
            method.setAttributeNode(attr);

            for (Parameter param : met.getParameters()) {
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
        return doc;
    }

    public static void createXMLResult(String f_name, Document doc) {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(f_name));
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
    }

}


