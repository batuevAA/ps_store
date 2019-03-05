package ps_store.batuev.com.ps_store;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.util.List;


public class MainActivity extends Activity {
    private TextInputLayout login;
    private TextInputLayout password;


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


//    public class MainActivity extends AppCompatActivity {
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_main);
//
//            if (savedInstanceState == null) {
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .add(R.id.container, new LoginFragment())
//                        .commit();
//            }
//        }
//
//    public void navigateTo(Fragment fragment, boolean addToBackstack) {
//        FragmentTransaction transaction =
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.container, fragment);
//
//        if (addToBackstack) {
//            transaction.addToBackStack(null);
//        }
//
//        transaction.commit();
//    }


    //регистрация
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
                return ServerApi.login(login.getEditText().getText().toString(), password.getEditText().getText().toString());
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
