package classwork.database;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

public class RowInsert {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/classwork", "vlad", "qwerty");
             Statement statement = conn.createStatement()) {
            int row = statement.executeUpdate(generateInsert("berg", new BigDecimal(10)));
            System.out.println(row);
        } catch (SQLException e) {
            System.err.format(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String generateInsert(String name, BigDecimal salary) {

        return "INSERT INTO EMPLOYEE ( name, salary, createDate) " +
                "values ('" + name + "','" + salary + "','" + LocalDateTime.now() + "')";

    }
}
