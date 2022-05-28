package ex2;

import java.io.Serializable;

public class User implements Serializable {
     String fn,ln,un;


    public User(String fn, String ln, String un) {
        this.fn = fn;
        this.ln = ln;
        this.un = un;

    }
}
