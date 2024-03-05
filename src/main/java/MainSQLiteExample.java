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

    public static int insert(int peaple, String clazz, String race) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:src/resources/db/db.sqlite");
        connection.setAutoCommit(true);
        PreparedStatement preparedStatement = connection.prepareStatement("""
            INSERT INTO tgdnd (peapleId, classes, races)
            VALUES (?, ?, ?) RETURNING id;
        """);
        preparedStatement.setString(1, String.valueOf(peaple));
        preparedStatement.setString(2, clazz);
        preparedStatement.setString(3, race);
        ResultSet resultSet = preparedStatement.executeQuery();
        int id = resultSet.getInt(1);
        connection.close();
        return id;
    }

    public static int update(int id, String columnName, String value) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:src/resources/db/db.sqlite");
        connection.setAutoCommit(true);
        PreparedStatement preparedStatement = connection.prepareStatement("""
            UPDATE tgdnd SET
            %s = ?
            where id=?
            RETURNING id;
        """.formatted(columnName));
        preparedStatement.setString(1, value);
        preparedStatement.setInt(2, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        int _id = resultSet.getInt(1);
        connection.close();
        return _id;

    }
}
