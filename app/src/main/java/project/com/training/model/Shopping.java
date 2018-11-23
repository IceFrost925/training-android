package project.com.training.model;



import java.io.Serializable;

public class Shopping implements Serializable {

    private int id;
    private String jhi_number;
    private String number;
    private String extra2;
    private int suser_id;
    private int book_id_id;
    public Shopping(int id, String jhi_number, String number, String extra2, int suser_id, int book_id_id) {
        this.id = id;
        this.jhi_number = jhi_number;
        this.number = number;
        this.extra2 = extra2;
        this.suser_id = suser_id;
        this.book_id_id = book_id_id;
    }

    public Shopping() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJhi_number() {
        return jhi_number;
    }

    public void setJhi_number(String jhi_number) {
        this.jhi_number = jhi_number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getExtra2() {
        return extra2;
    }

    public void setExtra2(String extra2) {
        this.extra2 = extra2;
    }

    public int getSuser_id() {
        return suser_id;
    }

    public void setSuser_id(int suser_id) {
        this.suser_id = suser_id;
    }

    public int getBook_id_id() {
        return book_id_id;
    }

    public void setBook_id_id(int book_id_id) {
        this.book_id_id = book_id_id;
    }

    @Override
    public String toString() {
        return "shopping{" +
                "id=" + id +
                ", jhi_number='" + jhi_number + '\'' +
                ", number='" + number + '\'' +
                ", extra2='" + extra2 + '\'' +
                ", suser_id=" + suser_id +
                ", book_id_id=" + book_id_id +
                '}';
    }
}

