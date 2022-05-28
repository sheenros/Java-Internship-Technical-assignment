package ex2;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
public class TaskManager {

    public static final String userPath = "C:\\Users\\Acer\\eclipse-workspace\\InternshipTest\\src\\ex2\\userFile.txt";
    public static final String taskPath = "C:\\Users\\Acer\\eclipse-workspace\\InternshipTest\\src\\ex2\\taskFile.txt";

    public static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<Task> tasks = new ArrayList<>();

    private static void serialize(Object object,String path) throws ClassNotFoundException{

        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(path))) {
            os.writeObject(object);

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static void deserializeUser() throws ClassNotFoundException{

        try(ObjectInputStream us = new ObjectInputStream(new FileInputStream(userPath))){
            User user = (User) us.readObject();
            users.add(new User(user.fn, user.ln, user.un));
        }catch (IOException e){
            System.out.println(e);
        }

    }
    private static void deserializeTask() throws  ClassNotFoundException{
        try(ObjectInputStream ts = new ObjectInputStream(new FileInputStream(taskPath))){
            Task task = (Task) ts.readObject();
            tasks.add(new Task(task.user, task.taskTitle, task.taskDescr));
        }catch (IOException e){
            System.out.println(e);
        }
    }
    public static void main(String[] args) throws ClassNotFoundException  {

        String command = args[0];
        String command1,command2,command3;

        if(command.equals("-createUser") ){
             command1 = args[1];
             command2 = args[2];
             command3 = args[3];
            if(command1.contains("-fn=")&& command2.contains("-ln=")&& command3.contains("-un=")) {

                String fn = command1.substring(5, command1.length() - 1);
                String ln = command2.substring(5, command2.length() - 1);
                String un = command3.substring(5, command3.length() - 1);
                User user = new User(fn, ln, un);
               serialize(user,userPath);
                System.out.println("User has been created");

            }else System.out.println("Command not found");

        }
        else if(command.equals("-showAllUsers")){

            int count = 0;
            deserializeUser();
            deserializeTask();
            System.out.println("List of users:");
            for(Task t : tasks){
            for (User u : users) {
                if(t.user.equals(u.un)){
                    count++;
                System.out.print(u.fn +" "+u.ln+" "+u.un+" "+count);
            }}}

        }
        else if(command.equals("-addTask")){
            command1 = args[1];
            command2 = args[2];
            command3 = args[3];
            if(command1.contains("-un=")&& command2.contains("-tt=")&& command3.contains("-td=")) {
                String un = command1.substring(5, command1.length() - 1);
                String tt = command2.substring(5, command2.length() - 1);
                String td = command3.substring(5, command3.length() - 1);
                        Task task = new Task(un,tt,td);
                serialize(task,taskPath);
                System.out.println("Task has been created");
            }else System.out.println("Command not found");
        }
        else if(command.equals("-showTasks")){
            command1 = args[1];
            if(command1.contains("-un=")) {
                String un = command1.substring(5, command1.length() - 1);
                deserializeTask();
                 System.out.println("List of tasks");
                    for (Task t : tasks) {
                        if(un.equals(t.user)){
                        System.out.print(" "+t.taskTitle+" "+t.taskDescr);}
                        else System.out.println("Unknown user");
            }
        }}
        else System.out.println("Error, unknown command");
    }
}
