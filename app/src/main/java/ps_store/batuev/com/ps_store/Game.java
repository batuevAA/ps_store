package ps_store.batuev.com.ps_store;
public class Game {
    private int game_id;
    private String name;
    private String info;
    private String image_url;
    private byte[] image_src;
    private int price;
    private GamePlatform console;
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


    public int getKolvo() {
        return kolvo;
    }


    public void setKolvo(int kolvo) {
        this.kolvo = kolvo;
    }


    public byte[] getImage_src() {
        return image_src;
    }


    public void setImage_src(byte[] image_src) {
        this.image_src = image_src;
    }


    public GamePlatform getConsole() {
        return console;
    }


    public void setConsole(GamePlatform console) {
        this.console = console;
    }
}
