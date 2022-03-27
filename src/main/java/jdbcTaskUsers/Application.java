package jdbcTaskUsers;

import java.util.List;
import java.util.Scanner;

public class Application {
        public Application() throws Exception {
            start();
        }

        private void start() throws Exception {
            User currentUser;
            Scanner scanner = new Scanner(System.in);
            int i = 0;
            while (i < 3) {
                System.out.println("Enter 'register' if you are a new user. \nEnter 'login' if you are already registered");
                System.out.println("Enter 'getUsers' to get all users");
                String decision = scanner.next();
                switch (decision) {
                    case "register":
                        UserManager.registerUser();
                        i = 3;
                        break;
                    case "login":
                        UserManager.login();
                        i = 3;
                        break;
                    case "getUsers":
                        currentUser = UserManager.login();
                        List<User> users = UserManager.getListOfUsers(currentUser);
                        for (User user : users) {
                            System.out.println(user);
                        }
                        i = 3;
                        break;
                    default:
                        System.err.println("You should enter one of: register/login/getUsers");
                        i = 0;
                }
                i++;
            }
        }
    }

