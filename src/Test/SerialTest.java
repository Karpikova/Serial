package Test;

import library.Modules.Book;
import library.Utils.DataManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/*
 * ${Classname}
 * 
 * Version 1.0 
 * 
 * 10.04.2017
 * 
 * Karpikova
 */
public class SerialTest {

    private static DataManager dataManager;

    @Test
    public void testDeserializationBook() {
        File file = mock(File.class);
        FileReader fileReader = mock(FileReader.class);
        BufferedReader bufferedReader = mock(BufferedReader.class);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader1 = new BufferedReader(new FileReader("book.txt"))) {
            stringBuilder.append(bufferedReader1.readLine());
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        try {
            when(bufferedReader.readLine()).thenReturn(stringBuilder.toString());
          //вернуть  Set<Book> collection = dataManager.DeSrialize(bufferedReader.readLine());
         //   Book book = collection.iterator().next();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @BeforeAll
    public static void init(){
        DataManager dataManager = new DataManager();
    }

}
