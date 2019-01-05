package ps_store.batuev.com.ps_store;
public class Game {
    private int game_id;
    private String name;
    private String info;
    private String image_url;
    private int price;
    private String console;
    private int kolvo;


    public int getGame_id() {
        return game_id;
    }


    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getInfo() {
        return info;
    }


    public void setInfo(String info) {
        this.info = info;
    }


    public String getImage_url() {
        return image_url;
    }


    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }


    public int getPrice() {
        return price;
    }


    public void setPrice(int price) {
        this.price = price;
    }


    public String getConsole() {
        return console;
    }


    public void setConsole(String console) {
        this.console = console;
    }


    public int getKolvo() {
        return kolvo;
    }


    public void setKolvo(int kolvo) {
        this.kolvo = kolvo;
    }
}
