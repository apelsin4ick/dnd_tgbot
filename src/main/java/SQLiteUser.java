import java.sql.*;
import java.util.ArrayList;

public class SQLiteUser {
    public static ArrayList<User> select(long telegrammId) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:src/resources/db/db.sqlite");
        connection.setAutoCommit(true);
        PreparedStatement preparedStatement = connection.prepareStatement("""
            select * from userState where tgId=?;
        """);
        preparedStatement.setLong(1, telegrammId);
        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<User> users=new ArrayList<>();
        while (resultSet.next()){
            long id= resultSet.getLong("tgId");
            long state= resultSet.getLong("state");
            long persNum= resultSet.getLong("persNum");

            users.add(new User(id, state, persNum));
        }
        return users;
    }

    public static int insert(long tgId, long state, long persNum) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:src/resources/db/db.sqlite");
        connection.setAutoCommit(true);
        PreparedStatement preparedStatement = connection.prepareStatement("""
            INSERT INTO userState (tgId, state, persNum)
            VALUES (?, ?, ?) RETURNING id;
        """);
        preparedStatement.setString(1, String.valueOf(tgId));
        preparedStatement.setString(2, String.valueOf(state));
        preparedStatement.setString(3, String.valueOf(persNum));
        ResultSet resultSet = preparedStatement.executeQuery();
        int id = resultSet.getInt(1);
        connection.close();
        return id;
    }

    public static int update(long tgId, long state, long persNum) throws SQLException {
        System.out.println("update " + tgId + " " + state + " " + persNum);
        Connection connection = DriverManager.getConnection("jdbc:sqlite:src/resources/db/db.sqlite");
        connection.setAutoCommit(true);
        PreparedStatement preparedStatement = connection.prepareStatement("""
                    UPDATE userState SET
                    state = ?,
                    persNum = ?
                    where tgId=?
                    RETURNING tgId;
                """);
        preparedStatement.setLong(1, state);
        preparedStatement.setLong(2, persNum);
        preparedStatement.setLong(3, tgId);
        ResultSet resultSet = preparedStatement.executeQuery();
        int _id = resultSet.getInt(1);

        connection.close();
        return _id;
    }
}
