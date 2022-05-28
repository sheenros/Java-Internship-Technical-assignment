
import java.sql.*;

public class TaskManager {


    public static Connection connection;
    public static Statement myStmt = null;
    public static ResultSet myRs = null;


    public static void connection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/taskmanager", "root", "");
            myStmt = connection.createStatement();

        } catch (Exception e) {
        }
    }


    public static void main(String[] args) throws SQLException {
        connection();
        String command = args[0];
        String command1, command2, command3;

        if (command.equals("-createUser")) {
            command1 = args[1];
            command2 = args[2];
            command3 = args[3];
            if (command1.contains("-fn=") && command2.contains("-ln=") && command3.contains("-un=")) {

                String fn = command1.substring(5, command1.length() - 1);
                String ln = command2.substring(5, command2.length() - 1);
                String un = command3.substring(5, command3.length() - 1);
                String myAdd = "insert into user(FirstName,LastName,UserName) values('" + fn + "','" + ln + "','" + un + "');";
                myStmt.executeUpdate(myAdd);
                System.out.println("User "+ un+" added successful");


            } else System.out.println("Command not found");

        } else if (command.equals("-showAllUsers")) {

           // ResultSet res = myStmt.executeQuery("select count(*) from task inner join user on task.UserName = user.Username");

            myRs = myStmt.executeQuery("select FirstName,LastName,UserName,(SELECT COUNT(UserName) FROM task GROUP BY UserName HAVING (COUNT(UserName) > 1) ) as count from user");
            while (myRs.next()) {
                System.out.println(myRs.getString("FirstName") + ", " + myRs.getString("LastName") + ", " + myRs.getString("UserName")+", "+myRs.getString("count"));

            }

        } else if (command.equals("-addTask")) {
            command1 = args[1];
            command2 = args[2];
            command3 = args[3];
            if (command1.contains("-un=") && command2.contains("-tt=") && command3.contains("-td=")) {
                String un = command1.substring(5, command1.length() - 1);
                String tt = command2.substring(5, command2.length() - 1);
                String td = command3.substring(5, command3.length() - 1);
                String myAdd = "insert into task(UserName,TaskTitle,TaskDescr) values('" + un + "','" + tt + "','" + td + "')";
                myStmt.executeUpdate(myAdd);

            } else System.out.println("Command not found");
        } else if (command.equals("-showTasks")) {
            command1 = args[1];
            if (command1.contains("-un=")) {
                String un = command1.substring(5, command1.length() - 1);
                myRs = myStmt.executeQuery("select TaskTitle,TaskDescr from task where UserName="+"'"+un+"'");
                while (myRs.next()) {
                    System.out.println(myRs.getString("TaskTitle")+ ", " + myRs.getString("TaskDescr"));
                }}

            }  else if (command.equals("-addGroupTask")) {
            command1 = args[1];
            command2 = args[2];
            command3 = args[3];
           String  command4 = args[4];
            if (command1.contains("-un1=")&& command2.contains("-un2") && command3.contains("-tt=") && command4.contains("-td=")) {
                String un = command1.substring(5, command1.length() - 1);
                String un2 = command2.substring(5, command1.length() - 1);
                String tt = command3.substring(5, command2.length() - 1);
                String td = command4.substring(5, command3.length() - 1);
                String myAdd = "insert into task(UserName,TaskTitle,TaskDescr) values('" + un+","+un2 + "','" + tt + "','" + td + "')";
                myStmt.executeUpdate(myAdd);

            }

        } else System.out.println("Error, unknown command");
    }
        }

