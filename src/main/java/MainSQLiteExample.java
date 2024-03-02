import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.*;

public class MainSQLiteExample {
    public static void main(String[] args) throws SQLException {
//        insert();
    }

//    public static void select() throws SQLException {
//        Connection connection = DriverManager.getConnection("jdbc:sqlite:src/resources/db/db.sqlite");
//        Statement statement = connection.createStatement();
//        ResultSet resultSet = statement.executeQuery("SELECT * FROM tgdnd;");
////        statement.execute("");
//        while (resultSet.next()) {
//            int id = resultSet.getInt(1);
//            String classes = resultSet.getString(2);
//            String races = resultSet.getString(3);
//
////            INSERT INTO book(book_id, title, author, price, amount)
////            VALUES (1, "Мастер и Маргарита", "Булгаков М.А.",670.99,3);
////            select * from book;
//
//            statement.execute("elf");
//
//            System.out.println(id + " " + classes + " " + races);
//        }
//    }

    public static void insert(String clazz, String race) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:src/resources/db/db.sqlite");
        PreparedStatement preparedStatement = connection.prepareStatement("""
            INSERT INTO tgdnd (classes, races)
            VALUES (?, ?);
        """);
        preparedStatement.setString(1, clazz);
        preparedStatement.setString(2, race);
//        public insert (Update update); {
//            try {
//                if(){
//
//                }
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        }
        preparedStatement.execute();
    }
}
