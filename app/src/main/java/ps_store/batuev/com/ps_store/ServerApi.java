package ps_store.batuev.com.ps_store;
import android.util.Log;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;





public class ServerApi {
    private static final String SERVER_URL = "http://192.168.0.100:80/store.php";


    /**
     * Авторизация
     * */
    public static User login(String login, String password) {
        Request request = new Request();
        request.query = "SELECT * FROM USERS WHERE login = \'" + login + "\' and password = \'" + password + "\'";

        String response = sendRequest(request);
        if (response == null)
            return null;


        LoginResponse resp = JSON.fromJson(response, LoginResponse.class);
        if (resp.result.size() != 1)
            return null;

        return resp.result.get(0);
    }
    private static class LoginResponse {
        private List<User> result;
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
    public static List<Game> getGameList() {
        Request request = new Request();
        request.query = ALL_GAMES;

        GameListResponse resp = JSON.fromJson(sendRequest(request), GameListResponse.class);
        return resp.result;
    }


    private static class GameListResponse {
        private List<Game> result;
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
        } catch (Throwable e) {
            Log.e("ERR", "sendRequest: ", e);
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
