package ex2;

import java.io.Serializable;

public class Task implements Serializable {
    String user;
    String taskTitle;
    String taskDescr;

    public Task(String user, String taskTitle, String taskDescr) {
        this.user = user;
        this.taskTitle = taskTitle;
        this.taskDescr = taskDescr;
    }
}

