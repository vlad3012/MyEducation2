package classwork.jdbc.taskBook;
import jdbcTaskUsers.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookManager {
    private static final DatabaseHandler USER_DATABASE_MANAGER = DatabaseHandler.getInstance();

    public static void registerUser() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Set number book");
        String number = scanner.next();
        List<Book> bookList = new ArrayList(USER_DATABASE_MANAGER.executeQuery("SELECT * FROM bookstore.book where number like '" + number + "'"));
        if (bookList.size()== 0) {
            Book book = new Book();
            book.setNumber(number);

            System.out.println("Set name book");
            book.setName(scanner.next());

            System.out.println("Set name author");
            book.setAuthor(scanner.next());

            System.out.println("Set count book");
            book.setCount(scanner.nextInt());
            registerUser(book);
        }else{
            System.out.println("this book already exists");
            System.out.println("Add to existing?\n 1. Yes\n 2. No");
            if(scanner.nextInt()==1){
                updateCountBook(bookList.get(0).getId());
            }
        }
    }

    private static void registerUser(Book book) {
        int rows = USER_DATABASE_MANAGER.executeUpdate("INSERT INTO bookstore.book (name, number, author, count) "
                + "VALUES(?,?,?,?)", book);
        if (rows > 0) {
            System.out.println("You are successfully add");
        }
    }

    private static void updateCountBook(int id){
        int rows = USER_DATABASE_MANAGER.executeUpdate("UPDATE bookstore.book SET count = count+1 WHERE id=?", id);
        if (rows > 0) {
            System.out.println("You are successfully add");
        }
 /* private static Book setNumber(Scanner scanner, Book book) {
        try {
            System.out.println("Set number");
            String number = scanner.next();
            validateLogin(number);
            book.setNumber(number);
            return book;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return setNumber(scanner, book);
        }
    }

    private static Book validateNumberBook(String number) throws Exception {
        List<Book> book = new ArrayList<>(USER_DATABASE_MANAGER.executeQuery("SELECT * FROM bookstore.book where number like '" + number + "'"));
        if (book.size() != 0) {
            //throw new Exception("Such login is already used");
            return book.get(0);
        } else {
            return null;
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
        if (currentUser.getSuper_user() == 0) {
            throw new Exception("TaskUsers.User doesn't have Admin permission");
        }
        return USER_DATABASE_MANAGER.executeQuery("SELECT * FROM users_schema.users");
    }
*/

}

    public static List<User> getListOfUsers(User currentUser)throws Exception {
        if (currentUser.getSuper_user() == 0) {
            throw new Exception("TaskUsers.User doesn't have Admin permission");
        }
        return  USER_DATABASE_MANAGER.executeQuery("SELECT * FROM classwork.bookstore");
    }


    public static User login(String login, String password)throws Exception {
        List<User> users = USER_DATABASE_MANAGER.executeQuery("SELECT * FROM classwork.bookstore where login like '"
                + login + "' and password like '" + password + "'");
        if (users.size() == 0) {
            throw new Exception("There is no user with such credentials");
        }System.out.println("You are successfully logged in");
        return users.get(0);
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
}