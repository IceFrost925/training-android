package project.com.training.model;

import java.io.Serializable;

public class User implements Serializable{

    private int id;
    private String username;
    private String image;
    private String email;
    private String passwd;
    private String tell;
    private Integer integral;
    private String extra1;
    private String extra2;

    public User(int id, String username, String image, String email, String passwd, String tell, Integer integral, String extra1, String extra2) {
        this.id = id;
        this.username = username;
        this.image = image;
        this.email = email;
        this.passwd = passwd;
        this.tell = tell;
        this.integral = integral;
        this.extra1 = extra1;
        this.extra2 = extra2;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", image='" + image + '\'' +
                ", email='" + email + '\'' +
                ", passwd='" + passwd + '\'' +
                ", tell='" + tell + '\'' +
                ", integral=" + integral +
                ", extra1='" + extra1 + '\'' +
                ", extra2='" + extra2 + '\'' +
                '}';
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getTell() {
        return tell;
    }

    public void setTell(String tell) {
        this.tell = tell;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public String getExtra1() {
        return extra1;
    }

    public void setExtra1(String extra1) {
        this.extra1 = extra1;
    }

    public String getExtra2() {
        return extra2;
    }

    public void setExtra2(String extra2) {
        this.extra2 = extra2;
    }

    public User() {
    }
}
