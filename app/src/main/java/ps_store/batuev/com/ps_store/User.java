package ps_store.batuev.com.ps_store;
public class User {
    private int id;
    private String first_name;
    private String last_name;
    private String login;
    private String password;
    private int cash;
    private String card_number;
    private int cvv;


    public static User CURRENT_USER = null;

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getFirst_name() {
        return first_name;
    }


    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }


    public String getLast_name() {
        return last_name;
    }


    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }


    public String getLogin() {
        return login;
    }


    public void setLogin(String login) {
        this.login = login;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public int getCash() {
        return cash;
    }


    public void setCash(int cash) {
        this.cash = cash;
    }


    public String getCard_number() {
        return card_number;
    }


    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }


    public int getCvv() {
        return cvv;
    }


    public void setCvv(int cvv) {
        this.cvv = cvv;
    }


    @Override public String toString() {
        return "User{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", cash=" + cash +
                ", card_number='" + card_number + '\'' +
                ", cvv=" + cvv +
                '}';
    }
}
