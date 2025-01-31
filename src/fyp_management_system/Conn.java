package fyp_management_system;

import java.sql.*;

public class Conn {
    private static  final String url = "jdbc:mysql://127.0.0.1:3306/project";
    private static final String username = "root";
    private static final String password = "#c++<mysql>";
    public static Connection conn;

    Conn(){
        //Trying to import JDBC class; return ClassNotFoundException
         try{
             Class.forName("com.mysql.cj.jdbc.Driver");
         } catch (ClassNotFoundException e){
             System.out.println(e.getMessage());
         }

         //Creates a connection with the database with these credentials; return SQLException
         try{
             conn = DriverManager.getConnection(url, username, password);
         } catch (SQLException e){
            System.out.println(e.getMessage());
        }

//        String loginQuery =
//        PreparedStatement preparedStatement1 = new PreparedStatement();













//        try{
//            //Creatig a connection with Database
//            Connection conn = DriverManager.getConnection(Conn.url, Conn.username, Conn.password);
//
//            //Creating a statement object to run or update queries
//            Statement statement = conn.createStatement();
//            //Any SQL Query that you want to run
//            //Adding data (Note that the query and the data are both hardcoded and in order to add differen data wthout writing th complete query, you can use the prepared statement)
//            String query2 = String.format("INSERT INTO testField(name, age, gpa) VALUES ('%s', %d, %f)", "Muhammad Ahmed Raza", 20, 3.34);
//            //To add to DB, use statement.executeUpdate
//            statement.executeUpdate(query2);
//
//            //query3 with no arguments as values
//            String query3 = "Insert Into testField(name, age, gpa) VALUES (?, ?, ?)";
//            //PreparedStatement just lik creating statement but with argument (the query with no values)
//            PreparedStatement preparedStatement = conn.prepareStatement(query3);
//            //The parameter idx is the sequence of the ? in the non-parameterized query
//            preparedStatement.setString(1, "Kaleemullah");
//            preparedStatement.setInt(2, 30);
//            preparedStatement.setDouble(3, 3.2);
//            int rowsEffected = preparedStatement.executeUpdate();
//            //Retrieving Data
//            String query1 = "SELECT * FROM testField";
//            //To retrieve from DB, use statement.executeQuery
//            ResultSet result = statement.executeQuery(query1);
//            if(rowsEffected>0){
//                while(result.next()){
//                    int id = result.getInt("id");
//                    String name = result.getString("name");
//                    int age = result.getInt("age");
//                    double gpa = result.getDouble("gpa");
//
//                    System.out.println("ID : "+id+"\nName : "+name+"\nAge : "+age+"\nGPA : "+gpa);
//                }
//            }
//
//            //Adding data in batches at once instead of doing it one by one
//            String query = "Insert Into testField(name, age, gpa) VALUES (?, ?, ?)";
//            Scanner in = new Scanner(System.in);
//            PreparedStatement preparedStatement = conn.prepareStatement(query);
//
//            while(true){
//                System.out.println("Enter name : ");
//                String name = in.nextLine();
//                System.out.println("Enter age : ");
//                int age = in.nextInt();
//                in.nextLine();
//                System.out.println("Enter gpa : ");
//                double gpa = in.nextDouble();
//                in.nextLine();
//
//                System.out.println("Want to enter more data? (Y/N) : ");
//                String c = in.nextLine();
//
//                if(c.equalsIgnoreCase("n"))
//                    break;
//
//                preparedStatement.setString(1, name);
//                preparedStatement.setInt(2, age);
//                preparedStatement.setDouble(3, gpa);
//
//                preparedStatement.addBatch();
//            }
//
//            //This returns array showing which query executed and which failed
//            int[] arr = preparedStatement.executeBatch();
//
//            for (int j : arr) {
//                if (j == 0)
//                    System.out.println("Query failed to execute.");
//                else
//                    System.out.println("Query executed successfully");
//            }
//
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
    }

}
