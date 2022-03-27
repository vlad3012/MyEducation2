package jdbcTaskUsers;

import jdbcTaskUsers.settings.Config;

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
            List<User> users = new ArrayList<>();
            try (Statement statement = getDbConnection().createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    User obj = new User();
                    obj.mapObject(resultSet);
                    users.add(obj);
                }
            } catch (SQLException | ClassNotFoundException e) {
                System.err.println(e.getMessage());
            }
            return users;
        }

        public int executeUpdate(String sql, User user) {
            int rows = 0;
            try (PreparedStatement statement = getDbConnection().prepareStatement(sql)) {
                statement.setString(1, user.getName());
                statement.setString(2, user.getlogin());
                statement.setString(3, user.getPassword());
                statement.setString(4, user.getemail());
                rows = statement.executeUpdate();
                System.out.println(rows + " rows are changed");
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return rows;
        }

    }

