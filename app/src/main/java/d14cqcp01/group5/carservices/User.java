package d14cqcp01.group5.carservices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by T420 on 5/5/2018.
 */

public class User {
    String avatar="",email="",number="",pass="";
    HashMap<String,String> ticketList;

    public User() {
    }

    public User(String avatar, String email, String number, String pass, HashMap<String,String> ticketList) {
        this.avatar = avatar;
        this.email = email;
        this.number = number;
        this.pass = pass;
        this.ticketList = ticketList;
    }
}
