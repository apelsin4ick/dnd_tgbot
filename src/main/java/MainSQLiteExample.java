import com.sun.source.tree.WhileLoopTree;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MainSQLiteExample {
    public static void main(String[] args) throws SQLException {
//        insert();
    }

    public static ArrayList<Personazhi> select(long telegrammId) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:src/resources/db/db.sqlite");
        connection.setAutoCommit(true);
        PreparedStatement preparedStatement = connection.prepareStatement("""
            select * from tgdnd where peapleId=?;
        """);
        preparedStatement.setLong(1, telegrammId);
        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<Personazhi> personazhisList=new ArrayList<>();
        while (resultSet.next()){
            long id= resultSet.getLong("id");
            long peapleId= resultSet.getLong("peapleId");
            String name= resultSet.getString("name");
            String classes= resultSet.getString("classes");
            String races= resultSet.getString("races");
            Long strength= resultSet.getLong("strength");
            Long dexterity= resultSet.getLong("dexterity");
            Long endurance= resultSet.getLong("endurance");
            Long intellect= resultSet.getLong("intellect");
            Long wisdom= resultSet.getLong("wisdom");
            Long charisma= resultSet.getLong("charisma");

            personazhisList.add(new Personazhi(id, peapleId,name, classes,races,strength,dexterity, endurance,intellect,wisdom,charisma));
        }
        return personazhisList;
    }


    public static int insert(int peaple, String clazz, String race, int strength) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:src/resources/db/db.sqlite");
        connection.setAutoCommit(true);
        PreparedStatement preparedStatement = connection.prepareStatement("""
            INSERT INTO tgdnd (peapleId, classes, races, strength)
            VALUES (?, ?, ?, ?) RETURNING id;
        """);
        preparedStatement.setString(1, String.valueOf(peaple));
        preparedStatement.setString(2, clazz);
        preparedStatement.setString(3, race);
        preparedStatement.setString(4, String.valueOf(strength));
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
