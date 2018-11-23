package project.com.training.model;

import java.io.Serializable;

public class Collect implements Serializable {

    private int id;
    private int suser_id;
    private int book_id;

    private String extra1;

    public Collect() {
    }

    private String extra2;

    public Collect(int id, String extra1, String extra2, int suser_id, int book_id) {
        this.id = id;
        this.extra1 = extra1;
        this.extra2 = extra2;
        this.suser_id = suser_id;
        this.book_id = book_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSuser_id() {
        return suser_id;
    }

    public void setSuser_id(int suser_id) {
        this.suser_id = suser_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
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

    @Override
    public String toString() {
        return "Collect{" +
                "id=" + id +
                ", suser_id=" + suser_id +
                ", book_id=" + book_id +
                ", extra1='" + extra1 + '\'' +
                ", extra2='" + extra2 + '\'' +
                '}';
    }
}
