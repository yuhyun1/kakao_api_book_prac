package dao;

import config.ConfigLoader;
import model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private final String url = ConfigLoader.getDbUrl();
    private final String username = ConfigLoader.getDbUsername();
    private final String password = ConfigLoader.getDbPassword();
    private List<Book> bookList;

    public void getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,username,password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public int bookRegister(Book book) {
        String SQL = "insert into booktable(booktitle,price,publisher,author,saleprice,isbn) values(?,?,?,?,?,?)";
        getConnection();
        int cnt = -1;
        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, book.getBooktitle());
            preparedStatement.setInt(2, book.getPrice());
            preparedStatement.setString(3, book.getPublisher());
            preparedStatement.setString(4, book.getAuthor());
            preparedStatement.setInt(5, book.getSaleprice());
            preparedStatement.setString(6, book.getIsbn());
            cnt = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbClose();
        }
        return cnt;
    }

    public List<Book> bookList() {
        String SQL = "select * from booktable";
        getConnection();
        bookList = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(SQL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int num = resultSet.getInt("num");
                String booktitle = resultSet.getString("booktitle");
                int price = resultSet.getInt("price");
                String publisher = resultSet.getString("publisher");
                String author = resultSet.getString("author");
                int saleprice = resultSet.getInt("saleprice");
                String isbn = resultSet.getString("isbn");
                Book book = new Book(num, booktitle, price, publisher, author, saleprice, isbn);
                bookList.add(book);
            }
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        } finally {
            dbClose();
        }
        return bookList;
    }

    public void dbClose() {
        try {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }
}