package ps_store.batuev.com.ps_store;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;





public class ServerApi {
    private static final String SERVER_URL = "";


    /**
     * Авторизация
     * */
    public static User login(String login, String password) {
        Request request = new Request();
        request.query = "SELECT * FROM USERS WHERE login = \'" + login + "\' and password = \'" + password + "\'";

        String data = sendRequest(request);
        if (data == null)
            return null;

        return new User();
    }


    /**
     * Регистрация
     * */
    public static int registration(User user) {
        return 0;
    }


    /**
     * Получить список игр
     * */
    private static final String ALL_GAMES = "SELECT * FROM GAMES";
    public static ArrayList<Game> getGameList() {
        ArrayList<Game> result = new ArrayList<>();
        return result;
    }


    /**
     * Получить список игр по платформе
     * */
    public static ArrayList<Game> getGames(GamePlatform platform) {

        return null;
    }



    /**
     * Покупка игры
     * */
    public static void buy(User user, Game game) {
    }


    private static final Gson JSON = new Gson();



    private static String sendRequest(Request request) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(SERVER_URL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(JSON.toJson(request));


            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }


            wr.close();
            rd.close();

            return response.toString();
        } catch (Exception e) {
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }


    private static class Request {
        private String type = "select";
        private String query;
    }
}
