package classwork.jdbc.taskBook;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Book implements MapTable{
    private int id;
    private String name;
    private String number;
    private String author;
    private int count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", author='" + author + '\'' +
                ", count=" + count +
                '}';
    }

    @Override
    public Object mapObject(ResultSet resultSet) throws SQLException {
        this.setId(resultSet.getInt("id"));
        this.setName(resultSet.getString("name"));
        this.setNumber(resultSet.getString("number"));
        this.setAuthor(resultSet.getString("author"));
        this.setCount(resultSet.getInt("count"));
        return this;
    }
}
