package petProject4.hTask.JDBC;

import java.sql.*;

public class BookDb {

    public static final String URL = "jdbc:mysql://localhost:3306";
    public static final String USER = "root";
    public static final String PASSWORD = "admin";

    public static void connect() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Connection Successful !\n");
            Statement statement = con.createStatement();
            createDb(statement);
            showingDb(statement);
            System.out.println();
            showingSamplingByAuthor(statement);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    private static void showingSamplingByAuthor(Statement statement) throws SQLException {
        String result2 = "SELECT author, title, year FROM bookDb.books WHERE author = 'Joanne Rowling'";
        ResultSet set2 = statement.executeQuery(result2);
        while (set2.next()) {
            String title = set2.getString("title");
            String author = set2.getString("author");
            int year = set2.getInt("year");
            System.out.printf("%s  -  %s  (%d) \n", author, title, year);
        }
    }

    private static void showingDb(Statement statement) throws SQLException {
        String result1 = "SELECT * FROM bookDb.books";
        ResultSet set1 = statement.executeQuery(result1);
        while (set1.next()){
            int id = set1.getInt("id");
            String title = set1.getString("title");
            String author = set1.getString("author");
            int year = set1.getInt("year");
            System.out.printf("%d   %s   %s   %d \n", id, author, title, year);
        }
    }

    private static void createDb(Statement statement) throws SQLException {
        statement.execute("DROP SCHEMA IF EXISTS `bookDb`");
        statement.execute("CREATE SCHEMA `bookDb`");
        statement.execute("CREATE TABLE `bookDb`.`books`" +
                "(`id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
                "`title` VARCHAR (45) NULL, " +
                "`author` VARCHAR (45) NULL, " +
                "`year` INT NULL);");
        statement.execute("INSERT INTO `bookDb`.`books` (`author`, `title`, `year`) " +
                "VALUES ('Alex Michaelides', 'The Silent Patient', 2019);");
        statement.execute("INSERT INTO `bookDb`.`books` (`author`, `title`, `year`) " +
                "VALUES ('Stephen King', 'Misery', 1987);");
        statement.execute("INSERT INTO `bookDb`.`books` (`author`, `title`, `year`) " +
                "VALUES ('Francis Scott Key Fitzgerald', 'The Great Gatsby', 1925);");
        statement.execute("INSERT INTO `bookDb`.`books` (`author`, `title`, `year`) " +
                "VALUES ('Francis Scott Key Fitzgerald', 'This Side of Paradise', 1920);");
        statement.execute("INSERT INTO `bookDb`.`books` (`author`, `title`, `year`) " +
                "VALUES ('Francis Scott Key Fitzgerald', 'The Curious Case of Benjamin Button', 1921);");
        statement.execute("INSERT INTO `bookDb`.`books` (`author`, `title`, `year`) " +
                "VALUES ('Stephen King', 'The Green Mile', 1999);");
        statement.execute("INSERT INTO `bookDb`.`books` (`author`, `title`, `year`) " +
                "VALUES ('John Ronald Reuel Tolkien', 'The Lord of the Rings', 1954);");
        statement.execute("INSERT INTO `bookDb`.`books` (`author`, `title`, `year`) " +
                "VALUES ('John Ronald Reuel Tolkien', 'The Hobbit', 1937);");
        statement.execute("INSERT INTO `bookDb`.`books` (`author`, `title`, `year`) " +
                "VALUES ('Joanne Rowling', 'Harry Potter and the Philosophers Stone', 1997);");
        statement.execute("INSERT INTO `bookDb`.`books` (`author`, `title`, `year`) " +
                "VALUES ('Joanne Rowling', 'Harry Potter and the Chamber of Secrets', 1998);");
        statement.execute("INSERT INTO `bookDb`.`books` (`author`, `title`, `year`) " +
                "VALUES ('Joanne Rowling', 'Harry Potter and the Prisoner of Azkaban', 1999);");
        statement.execute("INSERT INTO `bookDb`.`books` (`author`, `title`, `year`) " +
                "VALUES ('Joanne Rowling', 'Harry Potter and the Goblet of Fire', 2000);");
        statement.execute("INSERT INTO `bookDb`.`books` (`author`, `title`, `year`) " +
                "VALUES ('Joanne Rowling', 'Harry Potter and the Order of the Phoenix', 2003);");
        statement.execute("INSERT INTO `bookDb`.`books` (`author`, `title`, `year`) " +
                "VALUES ('Joanne Rowling', 'Harry Potter and the Half-Blood Prince', 2005);");
        statement.execute("INSERT INTO `bookDb`.`books` (`author`, `title`, `year`) " +
                "VALUES ('Joanne Rowling', 'Harry Potter and the Deathly Hallows', 2007);");
    }

}
