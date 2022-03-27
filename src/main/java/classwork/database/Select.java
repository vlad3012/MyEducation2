package classwork.database;

import java.math.BigDecimal;
import java.sql.*;
import java.sql.Timestamp;


public class Select {
    public static void main(String[] args) {
        String sql = "SELECT * FROM EMPLOYEE";
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/classwork", "vlad", "qwerty");
             Statement statement = conn.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                BigDecimal salary = resultSet.getBigDecimal("salary");
                Timestamp createDate = resultSet.getTimestamp("createDate");

                Employee obj = new Employee();
                obj.setId(id);
                obj.setName(name);
                obj.setSalary(salary);
                obj.setCreateDate(createDate.toLocalDateTime());
                System.out.println(obj);
            }
        } catch (Exception e) {
            System.err.format(  e.getMessage());
        }
    }
}
