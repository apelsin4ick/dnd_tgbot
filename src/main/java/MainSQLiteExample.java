import java.sql.*;

public class MainSQLiteExample {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:src/resources/db/db.sqlite");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM leha;");
        while (resultSet.next()){
            int id = resultSet.getInt(1);
            String da = resultSet.getString(2);
            System.out.println(id + " "+ da);
        }
    }
}
