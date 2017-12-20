package mobile.esprit.pim.Entities;

public class User {

    private int id_user;
    private String mail;
    private String pass;


    public User(int id_user, String mail, String pass) {
        this.id_user = id_user;
        this.mail = mail;
        this.pass = pass;

    }

    @Override
    public String toString() {
        return "User{" +
                "id_user=" + id_user +
                ", mail='" + mail + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }


    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
