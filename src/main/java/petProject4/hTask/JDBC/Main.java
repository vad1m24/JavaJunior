package petProject4.hTask.JDBC;

/**
 * 1. С помощью JDBC выполнить:
 * 1.1 Создать таблицу book с колонками id bigint, name varchar, author varchar, ...
 * 1.2 Добавить в таблицу 10 - 15 книг
 * 1.3 Сделать запрос select from book where author = 'какое-то имя' и прочитать его с помощью ResultSet
 */

public class Main {

    public static void main(String[] args) {

        BookDb.connect();

    }
}
