package com.bankexercise.controller;

import com.bankexercise.entity.Customer;
import com.bankexercise.db.DbConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerController {
    Connection connection = null;
    PreparedStatement pstm = null;
    ResultSet resultSet = null;
    Scanner scanner = new Scanner(System.in);

    public void insert(Customer customer) throws SQLException {
        DbConnector dbConnector = new DbConnector();
        Connection connection = dbConnector.getConnection();
        PreparedStatement pstm = null;
        pstm = connection.prepareStatement("INSERT INTO users VALUES (?,?)");
        pstm.setInt(1, 1);
        pstm.setString(2, "cus1");
        pstm.executeUpdate();
        pstm.close();// Thu hoi tai nguyen da cap phat
        DbConnector.close(pstm);
    }

    public void delete(int id) throws SQLException{
        DbConnector dbConnector = new DbConnector();
        Connection connection = dbConnector.getConnection();
        PreparedStatement pstm = null;
        pstm = connection.prepareStatement("DELETE INTO users VALUES (?,?)");
        pstm.setInt(1, 1);
        pstm.setString(2, "cus1");
        pstm.executeUpdate();
    }
    public void update(Customer customer) throws SQLException{
        Connection connection = DbConnector.getConnection();
        String query = "update customer set name =? where id =?";
        pstm = connection.prepareStatement(query);
        pstm.setString(1,customer.getName());
        pstm.setInt(2,customer.getId());
        pstm.executeUpdate();
        DbConnector.close(pstm);
        DbConnector.close(connection);
    }
    public Customer findById(int id) throws SQLException{
        return null;
    }
    public List<Customer> getAllCustomers(String name)throws SQLException{
        connection = DbConnector.getConnection();
        List<Customer> reCustomerList = new ArrayList<>();
        String query = "select * from customer";
        if(name!=null && !name.equals("")){
            query += "where name like '"+name+"'";
        }
        pstm = connection.prepareStatement(query);
        resultSet = pstm.executeQuery();
        while (resultSet.next()){
            Customer customer = new Customer();
            customer.setId(resultSet.getInt(1));
            customer.setName(resultSet.getString("name"));
            reCustomerList.add(customer);
        }
        DbConnector.close(resultSet);
        DbConnector.close(pstm);
        DbConnector.close(connection);
        return reCustomerList;
    }

}
