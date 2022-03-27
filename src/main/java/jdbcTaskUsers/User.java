package jdbcTaskUsers;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User implements MapTable {
        private long id;
        private String login;
        private String password;
        private int super_user;
        private String name;
        private String email;


        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getlogin() {
            return login;
        }

        public void setlogin(String login) {
            this.login = login;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getSuper_user() {
            return super_user;
        }

        public void setSuper_user(int super_user) {
            this.super_user = super_user;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getemail() {
            return email;
        }

        public void setemail(String email) {
            this.email = email;
        }

        @Override
        public User mapObject(ResultSet resultSet) throws SQLException {
            this.setId(resultSet.getLong("id"));
            this.setName(resultSet.getString("name"));
            this.setlogin(resultSet.getString("login"));
            this.setPassword(resultSet.getString("password"));
            this.setemail(resultSet.getString("email"));
            this.setSuper_user(resultSet.getInt("super_user"));
            return this;
        }

        @Override
        public String toString() {
            return "TaskUsers.User{" +
                    "id=" + id +
                    ", login='" + login + '\'' +
                    ", password='" + password + '\'' +
                    ", super_user=" + super_user +
                    ", name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }
    }


