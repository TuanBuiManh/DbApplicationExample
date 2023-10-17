import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.Scanner;


public class MySQLConnectionExample {

    public static Connection getMyConnection() throws SQLException{
        String hostName = "localhost";//127.0.0.1
        String dbName = "myprojectjava";
        String userName = "root";
        String password = "";
        //Chuoi ket noi database
        //"jdbc:mysql://localhost:3306?myprojectjava,userName, password
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
        Connection connection =
                DriverManager.getConnection(connectionURL,userName,password);
        return connection;
    }
    // Create new record: Insert into Employee values(1,"Ngoc","Hoan Kiem");
    public static void createEmployee() throws SQLException {
        //1. Ket noi
        Connection connection = getMyConnection();
        //2. Tao Statement(thuc thi)
        Statement statement = connection.createStatement();
        //3. Tao Query string(Chuoi truy van thao tac Db)
        String query = "insert into employee(id,name,address)" +
                "value(2,'Vinh','Hanoi')";
        //4. Thuc hien insert
        int count = statement.executeUpdate(query);
        System.out.println("Count: "+ count);
    }

    public static void getDataEmployee() throws SQLException{
        Connection connection = getMyConnection();
        Statement statement = connection.createStatement();
        String query = "select * from employee";
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()){
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            System.out.println("==================");
            System.out.println(id);
            System.out.println(name);
            System.out.println(address);
        }
        connection.close();
    }

    public static void crudMySQLDb() throws SQLException{
        // 1. Tao Connection object
        Connection connection = getMyConnection();
        // 2. Tao Statement object de thuc thi
        Statement statement = connection.createStatement();
        // 3. Create table
        // Create table product(id int primary key, proName varchar(20),
        // description varchar(20)
        // CREATE TABLE
        statement.execute("DROP TABLE IF EXISTS PRODUCT");
        statement.execute("CREATE TABLE PRODUCT(id int primary key identity (1,1), proName varchar(50), description varchar(100))");
        // INSERT DATA
        statement.execute("INSERT INTO product VALUES (1,'Iphone15','The new product')");
        // SELECT DATA WITH Statement
        ResultSet resultSet = statement.executeQuery("SELECT * FROM product");
        while (resultSet.next()){
            System.out.println("Product Name: " + resultSet.getString("proName"));
        }
    }

    public static void crudUMySQLDb() throws SQLException{
        // 1. Tao Connection object
        Connection connection = getMyConnection();
        // 2. Tao Statement object de thuc thi
        Statement statement = connection.createStatement();
        // 3. Create table
        // Create table product(id int primary key, proName varchar(20),
        // description varchar(20)
        // CREATE TABLE
        statement.execute("DROP TABLE IF EXISTS users");
        statement.execute("CREATE TABLE users(id int primary key, username varchar(50), password varchar(100))");
        // INSERT DATA
        statement.execute("INSERT INTO users VALUES (1,'admin','admin')");
        // SELECT DATA WITH Statement
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
        while (resultSet.next()){
            System.out.println("Username: " + resultSet.getString("username"));
        }
        // SELECT DATA WITH PreparedStatement
        // select * from product
        String query = "SELECT * FROM product WHERE proName like ? OR id = ?";
        PreparedStatement pstm = connection.prepareStatement(query);
        pstm.setString(1, "iphone");
        pstm.setInt(2, 1);
        resultSet = pstm.executeQuery();
        while (resultSet.next()){
            System.out.println("ID: "+ resultSet.getInt("id"));
            System.out.println("proName: "+ resultSet.getString("proName"));
        }
        // UPDATE DATA WITH PreparedStatement
        // "update product set proname = ? where id = ?
        pstm = connection.prepareStatement("UPDATE product proName = ? WHERE id = ?");
        pstm.setString(1,"SAMSUNG");
        pstm.setInt(2,1);
        int rowUpdate = pstm.executeUpdate();
        if(rowUpdate > 0){
            System.out.println("Update Successful");
        }

        // DELETE DATA WITH PreparedStatement
        // delete from froduct where proname = ? or id = ?
        pstm = connection.prepareStatement("DELETE product proName = ?");
        pstm.setString(1,"SAMSUNG");
        int rowDeleted = pstm.executeUpdate();
        if(rowDeleted > 0){
            System.out.println("Update Successful");
        }
    }

    // Login with Statement
    public static void loginWithStatement(String username, String password) throws SQLException{
        // 1. Tao ket noi database
        Connection connection =getMyConnection();
        // 2. Tao Query
        String query = "SELECT username FROM users WHERE username like '"+username+"' AND password like '"+password+"'";
        // 3. Tao Statement
        Statement statement = connection.createStatement();
        // 4. Tao ResultSet
        ResultSet resultSet = statement.executeQuery(query);
        // 5. Fetch(doc) data
        while(resultSet.next()){
            System.out.println("username: "+resultSet.getString("username"));
        }
    }
    // Login with PreparedStatement
    public static void loginWithPreparedStatement(String username, String password) throws SQLException{
        // 1. Tao ket noi
        Connection connection = getMyConnection();
        // 2. Tao Query
        String query = "SELECT username FROM users WHERE username LIKE ? AND password LIKE ?";
        // 3. Tao PreparedStatement
        PreparedStatement pstm = connection.prepareStatement(query);
        // 3.1 Gan gia tri tham so cho PreparedStatement
        pstm.setString(1,username);
        pstm.setString(2,password);
        // 4. Thuc thi
        ResultSet resultSet = pstm.executeQuery();
        // 5. Fetch data
        while (resultSet.next()) {
            System.out.println("Username: " + resultSet.getString("username"));
        }
    }

    public static void MenuLogin() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Menu.loginMenu();
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice){
            case 1:
                loginWithStatement("admin","admin");
                break;
            case 2:
                loginWithPreparedStatement("admin","admin");
                break;
        }
    }

    public static void updateDataEmployee() throws SQLException{
        Connection connection = getMyConnection();
        Statement statement = connection.createStatement();
        String query = "update employee set name = 'Tuan', address = 'HN' where id = 2";
        int count = statement.executeUpdate(query);
        System.out.println("Count: " + count);
    }

    public static void createTable() throws SQLException{
        Connection connection = getMyConnection();
        Statement statement = connection.createStatement();
        String query = "CREATE TABLE test (column1 int(11),column2 varchar(255),column3 varchar(255))";
        int count = statement.executeUpdate(query);
        System.out.println("Count: " + count);
    }

    public static void deleteTable() throws SQLException{
        Connection connection = getMyConnection();
        Statement statement = connection.createStatement();
        String query = "DELETE FROM test WHERE cloumn1 = 1";
        int count = statement.executeUpdate(query);
        System.out.println("Count: " + count);
    }

    public static void main(String[] args) throws SQLException {
//        getMyConnection();
        if(getMyConnection()!=null){
            System.out.println("Connect success!");
            //createEmployee();
            //updateDataEmployee();
            //getDataEmployee();
            //createTable();
            //deleteTable();
            //crudMySQLDb();
            //crudUMySQLDb();
            //loginWithStatement("''or 1=1--","hhhrth");
            //loginWithPreparedStatement("admin","admin");
            MenuLogin();

        }
        else{
            System.out.println("Connect fail!");
        }
    }
}
