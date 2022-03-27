package classwork.jdbc.taskBook;

import classwork.jdbc.taskBook.settings.Config;
import jdbcTaskUsers.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends Config {
    private static DatabaseHandler instance;
    private static Connection dbConnection;


    private DatabaseHandler(){
    }

    public static DatabaseHandler getInstance() {
        if (instance == null) {
            instance = new DatabaseHandler();
        }
        return instance;
    }


    private Connection getDbConnection() throws ClassNotFoundException, SQLException {
        if (dbConnection == null) {
            String connectionString = "jdbc:mysql://" + DbHost + ":" + DbPort + "/" + DbName;
            dbConnection = DriverManager.getConnection(connectionString, DbUser, DbPass);
            return dbConnection;
        }
        return dbConnection;
    }



    public List<User> executeQuery(String sql) {
        List<Book> Books = new ArrayList<>();
        try (Statement statement = getDbConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Book obj = new Book();
                obj.mapObject(resultSet);
                Books.add(obj);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return Books;
    }

    public int executeUpdate(String sql, Book Book) {
        int rows = 0;
        try (PreparedStatement statement = getDbConnection().prepareStatement(sql)) {
            statement.setString(1, Book.getName());
            statement.setString(2, Book.getNumber());
            statement.setString(3, Book.getAuthor());
            statement.setInt(4, Book.getCount());
            rows = statement.executeUpdate();
            System.out.println(rows + " rows are changed");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return rows;
    }

    public int executeUpdate(String sql, int id) {
        int rows = 0;
        try (PreparedStatement statement = getDbConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            rows = statement.executeUpdate();
            System.out.println(rows + " rows are changed");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return rows;
    }
}
