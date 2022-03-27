package jdbcTaskUsers;

import java.util.List;
import java.util.Scanner;

public class UserManager {
        private static final DatabaseHandler USER_DATABASE_MANAGER = DatabaseHandler.getInstance();


        public static void registerUser() {
            Scanner scanner = new Scanner(System.in);
            User user = new User();
            System.out.println("Set name");
            user.setName(scanner.next());

            user = setLogin(scanner, user);

            System.out.println("Set password");
            user.setPassword(scanner.next());

            System.out.println("Set Email");
            user.setemail(scanner.next());
            registerUser(user);
        }

        private static void registerUser(User user) {
            int rows = USER_DATABASE_MANAGER.executeUpdate("INSERT INTO users_schema.users (name, login, password, email) "
                    + "VALUES(?,?,?,?)", user);
            if (rows > 0) {
                System.out.println("You are successfully registered");
            }
        }

        private static void validateLogin(String login) throws Exception {
            if (USER_DATABASE_MANAGER.executeQuery("SELECT * FROM users_schema.users where login like '" + login + "'").size() != 0) {
                throw new Exception("Such login is already used");
            }
        }

        private static User setLogin(Scanner scanner, User user) {
            try {
                System.out.println("Set login");
                String login = scanner.next();
                validateLogin(login);
                user.setlogin(login);
                return user;
            } catch (Exception e) {
                System.err.println(e.getMessage());
                return setLogin(scanner, user);
            }
        }

        public static User login() {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter login");
            String login = scanner.next();
            try {
                System.out.println("Enter password");
                return login(login, scanner.next());
            } catch (Exception e) {
                System.err.println(e.getMessage());
                System.out.println("Try 1 more time");
                return login();
            }
        }

        private static User login(String login, String password) throws Exception {
            List<User> users = USER_DATABASE_MANAGER.executeQuery("SELECT * FROM users_schema.users where login like '"
                    + login + "' and password like '" + password + "'");
            if (users.size() == 0) {
                throw new Exception("There is no user with such credentials");
            }
            System.out.println("You are successfully logged in");
            return users.get(0);
        }

        public static List<User> getListOfUsers(User currentUser) throws Exception {
            if (currentUser.getSuper_user() == 0 ) {
                throw new Exception("TaskUsers.User doesn't have Admin permission");
            }
            return USER_DATABASE_MANAGER.executeQuery("SELECT * FROM users_schema.users");
        }

    }

