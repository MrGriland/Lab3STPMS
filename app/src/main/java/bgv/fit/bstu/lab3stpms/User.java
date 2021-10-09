package bgv.fit.bstu.lab3stpms;

import java.io.Serializable;

public class User implements Serializable {
    public String name;
    public String sname;
    public String surname;
    public String mail;
    public String phone;
    public String link;
    public byte[] photo;

    public User(String name, String sname, String surname, String mail, String phone, String link) {
        this.name = name;
        this.sname = sname;
        this.surname = surname;
        this.mail = mail;
        this.phone = phone;
        this.link = link;
    }

    public User(){}
}
