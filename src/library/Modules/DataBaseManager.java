package library.Modules;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/*
 * ${Classname}
 * 
 * Version 1.0 
 * 
 * 13.04.2017
 * 
 * Karpikova
 */
public class DataBaseManager {

    public Connection initConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/Library",
                                "postgres", "79");
        return connection;
    }

    public void select() {
        try {
            Connection connection = initConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from PUBLIC.Book");
            while (resultSet.next()){
                System.out.println(resultSet.getInt("book_year"));
                System.out.println(resultSet.getString(5));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert() {
        try {
            Connection connection = initConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO book(\n" +
                            " book_author, book_title, book_isbn, book_year)\n" +
                            " VALUES (?,?, ?, ?)");
            preparedStatement.setString(1, "NeSchildt");
            preparedStatement.setString(2, "PHP");
            preparedStatement.setString(3, "12344664");
            preparedStatement.setInt(4, 2017);
            System.out.println(preparedStatement.executeUpdate());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void update() {
        try {
            Connection connection = initConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE public.book\n" +
                            "            SET book_author=?, book_title=?, book_isbn=?, book_year=?\n" +
                            "            WHERE book_title = 'PHP'");
            preparedStatement.setString(1, "ButSchildt");
            preparedStatement.setString(2, "PHP");
            preparedStatement.setString(3, "12344664");
            preparedStatement.setInt(4, 2017);
            System.out.println(preparedStatement.executeUpdate());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        try{
        Connection connection = initConnection();
        PreparedStatement preparedStatement =
                connection.prepareStatement("DELETE FROM public.book\n" +
                        "            WHERE book_title = ?");
        preparedStatement.setString(1, "PHP");
        System.out.println(preparedStatement.executeUpdate());
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }



}
