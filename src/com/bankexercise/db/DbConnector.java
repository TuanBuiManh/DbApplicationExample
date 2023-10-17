package com.bankexercise.db;

import java.sql.*;

public class DbConnector {
    public static Connection getConnection() throws SQLException {
        String hostName = "localhost";//127.0.0.1
        String dbName = "bankexercise";
        String userName = "root";
        String password = "";
        //Chuoi ket noi database
        //"jdbc:mysql://localhost:3306?myprojectjava,userName, password
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
        Connection connection =
                DriverManager.getConnection(connectionURL,userName,password);
        return connection;
    }
    public static void close(Connection connection) throws SQLException {
        if(connection!=null){
            connection.close();
        }
    }
    public static void close(PreparedStatement pstm) throws SQLException {
        if(pstm!=null){
            pstm.close();
        }
    }
    public static void close(ResultSet resultSet) throws SQLException {
        if(resultSet!=null){
            resultSet.close();
        }
    }
}
