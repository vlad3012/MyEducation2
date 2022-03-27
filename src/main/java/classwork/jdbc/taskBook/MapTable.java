package classwork.jdbc.taskBook;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface MapTable {
    Object mapObject(ResultSet resultSet) throws SQLException;
}
