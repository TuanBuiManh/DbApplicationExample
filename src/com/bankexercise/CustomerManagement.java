package com.bankexercise;

import com.bankexercise.controller.CustomerController;
import com.bankexercise.entity.Customer;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class CustomerManagement {
    CustomerController customerController = new CustomerController();
    Customer customer = new Customer();
    Scanner scanner = new Scanner(System.in);
    public static void insert(){}
    public static void update(){}
    public static void delete(){}
    public static void findById(){}
    public static void getAllCustomers(){}
    public void getAllCustomer() throws SQLException {
        String name = scanner.nextLine();
        List<Customer> customerList = customerController.getAllCustomers(name);
        customerList.forEach(customer ->{
            System.out.println(customer.getId()+ "\t" +customer.getName());
        });
    }
    public void menu() throws SQLException {
        int action = 0;
        do {
            System.out.println("\n\t***Welcome to Aptech Bank Online***\t");
            System.out.println("1. Create a new Customer");
            System.out.println("2. Update customer");
            System.out.println("3. Delete customer");
            System.out.println("4. Find by customer by Id");
            System.out.println("5. Display all customer information");
            System.out.println("6. Exit");
            System.out.println("Enter your choice...");
            action = scanner.nextInt();
            switch (action){
                case 1: insert();break;
                case 2: update();break;
                case 3: delete();break;
                case 4: findById();break;
                case 5: getAllCustomers();break;
                case 6: exit(0);break;
                default:
                    System.out.println("Enter a validate!");
                    break;
            }
        }while (action != 6);
    }
}
