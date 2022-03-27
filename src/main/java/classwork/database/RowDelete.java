package classwork.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class RowDelete {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/classwork", "vlad", "qwerty");
             Statement statement = conn.createStatement()){
            int row = statement.executeUpdate(deleteByName("berg"));
            System.out.println(row);
        }catch (SQLException e) {
            System.err.format(  e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
    }
}private static String deleteByName(String name) {

        return "DELETE FROM EMPLOYEE WHERE NAME='" + name + "'";

    }}
