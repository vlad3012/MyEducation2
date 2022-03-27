package jdbcTaskUsers;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface MapTable {
        User mapObject(ResultSet resultSet) throws SQLException;
}
