package classwork.jdbc.taskBook;
import jdbcTaskUsers.User;

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
            System.out.println("select item");
            System.out.println("1. Add a book \n2. Book search by number\n3. Take the book\n 4. Number of books\n5. display the author\n6. number of authors in the library\n7. Exit");
            int decision = scanner.nextInt();
            switch (decision) {
                case 1:
                    BookManager.registerUser();
                    i = 3;
                    break;
                case 2:
                    BookManager.login();
                    i = 3;
                    break;
                case 3:
                    currentUser = BookManager.login();
                    List<User> users = BookManager.getListOfUsers(currentUser);
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
