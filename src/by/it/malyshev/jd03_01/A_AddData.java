package by.it.malyshev.jd03_01;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class A_AddData {

    public static void main(String[] args) {

        try (Connection connection =
                     DriverManager.getConnection
                             (CN.URL_DB, CN.USER_DB, CN.PASSWORD_DB);
             Statement statement = connection.createStatement()) {
            int book = 0, author = 0;

            statement.executeUpdate(
                    "insert into messages(`message`,`users_ID`,`books_ID`)" +
                            " values('Одна из самых любимых книг! История Средиземья, в которой звучат древние сказания, волшебные эльфийские песни, старинные легенды и захватывающие приключения...',1,2);"
            );


            if (1 == statement.executeUpdate(
                    "insert into books(`title`,`pubyear`,`isbn`,`price`)" +
                            " values('Generation П',2015, '978-5-699-83323-8',12.21);", Statement.RETURN_GENERATED_KEYS)) {
                ResultSet key = statement.getGeneratedKeys();
                if (key.next()) {
                    book = key.getInt(1);
                }
            }

            if (1 == statement.executeUpdate(
                    "insert into authors(`name`)" +
                            " values('Виктор Пелевин');", Statement.RETURN_GENERATED_KEYS)) {
                ResultSet key = statement.getGeneratedKeys();
                if (key.next()) {
                    author = key.getInt(1);
                }
            }

            statement.executeUpdate(String.format(
                    "insert into authorsBooks(`authors_ID`,`books_ID`) values(%d, %d);", author, book));

            statement.executeUpdate(String.format(
                    "insert into store(`quantity`,`books_ID`)" +
                            " values(100, %d);", book));

            statement.executeUpdate(String.format(
                    "insert into orders(`quantity`,`users_ID`, `books_ID`)" +
                            " values(1, 2, %d);", book));


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
