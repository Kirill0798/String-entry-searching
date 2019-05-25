package database;

import java.sql.*;
import com.mysql.cj.jdbc.Driver;

public class DatabaseConnector {
    private String URL = "jdbc:sqlite:/Users/kirill/Desktop/labs_db/tnp_3.db";
//    private String URL = "jdbc:mysql://localhost:3306/test?" +
//            "autoReconnect=true" +
//            "&useSSL=false" +
//            "&useLegacyDatetimeCode=false" +
//            "&serverTimezone=UTC";
//    private String USER = "root";
//    private String PASSWORD = "";
    private static Connection connection;
    private static Driver driver;
    private static Statement statement;
    private static DatabaseConnector databaseConnector;

    static {
        try {
            create();
        }catch(Exception ignored){
            ignored.printStackTrace();
        }
    }

    private static synchronized void create() throws SQLException{
        if (databaseConnector == null){
            databaseConnector = new DatabaseConnector();
            databaseConnector.build();
        }
    }
    private DatabaseConnector(){}

    private synchronized void build() throws SQLException{
        driver = new Driver();
        DriverManager.registerDriver(driver);
        connection = DriverManager.getConnection(URL);
        statement = connection.createStatement();
    }

    public synchronized Statement getStatement(){
        return statement;
    }

    public synchronized Connection getConnection(){
        return connection;
    }
    public synchronized Driver getDriver(){
        return driver;
    }
    public synchronized void close() throws SQLException{
        connection.close();
        statement.close();
    }
    public static synchronized void insert(String first, String second, String result) throws SQLException{
        String statement = "INSERT INTO searching SET 'first'=?, 'second'=?,'result'=?;";
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setString(1, first);
        preparedStatement.setString(2, second);
        preparedStatement.setString(3, result);
        preparedStatement.executeUpdate();
    }
    public static synchronized String select(String first, String second) throws SQLException{
        String statement = "SELECT 'result' FROM searching where 'first' = ? AND 'second' = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setString(1, first);
        preparedStatement.setString(2, second);
        ResultSet set = preparedStatement.executeQuery();
        if (set.next()){
            return set.getString("result");
        }
        return null;
    }




}
