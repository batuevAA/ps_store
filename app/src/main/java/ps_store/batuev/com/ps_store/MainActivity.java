package ps_store.batuev.com.ps_store;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;





public class MainActivity extends Activity {
    private EditText login;
    private EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.login);
        password = findViewById(R.id.password);
    }


    public void registration(View view) {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }


    public void login(View view) {
        new AsyncTask<Void, Void, User>() {
            @Override
            protected User doInBackground(Void... strings) {
                return ServerApi.login(login.getText().toString(), password.getText().toString());
            }


            @Override
            protected void onPostExecute(User user) {
                User.CURRENT_USER = user;
                // TODO: На экран с играми
            }
        }.execute();
    }
}
