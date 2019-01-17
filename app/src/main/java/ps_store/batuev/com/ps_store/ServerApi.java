package ps_store.batuev.com.ps_store;
import android.util.Log;
import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;





public class ServerApi {
    private static final String SERVER_URL = "http://192.168.0.2/";
    private static final String SERVER_CONTROLLER = SERVER_URL + "store.php";



    //Авторизация

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

    //Отрисовка изображений

    private static final Map<String, byte[]> imageCache = new ConcurrentHashMap<>();
    public static byte[] getImage(String image_url) {
        byte[] fromCache = imageCache.get(image_url);
        if (fromCache != null)
            return fromCache;

        byte[] data = getStatic(image_url);
        imageCache.put(image_url, data);

        return data;
    }


    private static class LoginResponse {
        private List<User> result;
    }



    //Регистрация

    public static void registration(User user) {
        Request checkUser = new Request();
        checkUser.query = "SELECT * FROM USERS WHERE login = \'" + user.getLogin() + "\'";
        String checkUserResponse = sendRequest(checkUser);
        if (checkUserResponse == null)
            return;

        LoginResponse checkUserResp = JSON.fromJson(checkUserResponse, LoginResponse.class);
        if (checkUserResp.result.size() > 0)
            return;

        StringBuilder request = new StringBuilder();
        request.append("INSERT INTO USERS SET ")
                .append("first_name='").append(user.getFirst_name()).append('\'').append(", ")
                .append("last_name='").append(user.getLast_name()).append('\'').append(", ")
                .append("login='").append(user.getLogin()).append('\'').append(", ")
                .append("password='").append(user.getPassword()).append('\'').append(", ")
                .append("cash=0").append(", ")
                .append("card_number='").append(user.getCard_number()).append('\'').append(", ")
                .append("cvv=").append(user.getCvv());

        Request regRequest = new Request();
        regRequest.type = "insert";
        regRequest.query = request.toString();

        sendRequest(regRequest);
    }


    //Получить список игр

    private static final String ALL_GAMES = "SELECT * FROM GAMES";
    public static List<Game> getGameList() {
        Request request = new Request();
        request.query = ALL_GAMES;

        String data = sendRequest(request);
        Log.e("getGameList: ", data);
        GameListResponse resp = JSON.fromJson(data, GameListResponse.class);
        return resp.result;
    }


    private static class GameListResponse {
        private List<Game> result;
    }


    private static final Gson JSON = new Gson(); //преобразование в json и обратно


    //Отправка запроса на сервер

    private static String sendRequest(Request request) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(SERVER_CONTROLLER);
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

//Скачивание картинки

    public static byte[] getStatic(String staticUrl) {
        try {
            URL url = new URL(SERVER_URL + staticUrl);
            InputStream in = new BufferedInputStream(url.openStream());
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            byte[] buf = new byte[16 * 1024];
            int n;

            while ((n = in.read(buf)) > 0) {
                out.write(buf, 0, n);
            }

            out.close();
            in.close();
            return out.toByteArray();
        } catch (Throwable e) {
            return null;
        }
    }


    private static class Request {
        private String type = "select";
        private String query;
    }
}
