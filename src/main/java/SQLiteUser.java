import java.sql.*;
import java.util.ArrayList;

public class SQLiteUser {
    public static ArrayList<User> select(long telegrammId) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:src/resources/db/db.sqlite");
        connection.setAutoCommit(true);
        PreparedStatement preparedStatement = connection.prepareStatement("""
            select * from userState where =?;
        """);
        preparedStatement.setLong(1, telegrammId);
        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<User> users=new ArrayList<>();
        while (resultSet.next()){
            long id= resultSet.getLong("tgId");
            long state= resultSet.getLong("state");

            users.add(new User(id, state));
        }
        return users;
    }

    public static int insert(long tg, long state) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:src/resources/db/db.sqlite");
        connection.setAutoCommit(true);
        PreparedStatement preparedStatement = connection.prepareStatement("""
            INSERT INTO user (tg, state)
            VALUES (?, ?) RETURNING id;
        """);
        preparedStatement.setString(1, String.valueOf(tg));
        preparedStatement.setString(2, String.valueOf(state));
        ResultSet resultSet = preparedStatement.executeQuery();
        int id = resultSet.getInt(1);
        connection.close();
        return id;
    }

    public static int update(int id, String columnName, String value) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:src/resources/db/db.sqlite");
        connection.setAutoCommit(true);
        PreparedStatement preparedStatement = connection.prepareStatement("""
                    UPDATE userState SET
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
