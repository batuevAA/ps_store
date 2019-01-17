package ps_store.batuev.com.ps_store;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.List;





public class MainActivity extends Activity {
    private EditText login;
    private EditText password;


    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.login);
        password = findViewById(R.id.password);

        new AsyncTask<Void, Void, List<Game>>() {
            @Override
            protected List<Game> doInBackground(Void... voids) {
                return ServerApi.getGameList();
            }


            @Override
            protected void onPostExecute(List<Game> games) {
                Game.GAMES = games;

            }
        }.execute();
    }


    public void registration(View view) {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }

    @SuppressLint("StaticFieldLeak")
   //авторизация
    public void login(View view) {
        final MainActivity that = this;

        new AsyncTask<Void, Void, User>() {
            @Override
            protected User doInBackground(Void... strings) {
                return ServerApi.login(login.getText().toString(), password.getText().toString());
            }


            @Override
            protected void onPostExecute(final User user) {
                User.CURRENT_USER = user;
                if (user == null)
                    return;

                Intent intent = new Intent(that, GameListActivity.class);
                that.startActivity(intent);
            }
        }.execute();
    }

    public void gotocontact(View view) {
        Intent intent = new Intent(this, ContactActivity.class);
        startActivity(intent);
    }
}
