package ps_store.batuev.com.ps_store;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toolbar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;





public class RegistrationActivity extends Activity {
    private static final String EMPTY_STRING = "";
    private TextInputEditText first_name;
    private TextInputEditText last_name;
    private TextInputEditText login;
    private TextInputEditText password;
    private TextInputEditText card_number;
    private TextInputEditText cvv;
    private Button regButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setActionBar(toolbar);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

        first_name = (TextInputEditText) findViewById(R.id.first_name);
        last_name  = (TextInputEditText) findViewById(R.id.last_name);
        login = (TextInputEditText) findViewById(R.id.login);
        password = (TextInputEditText) findViewById(R.id.password);
        card_number = (TextInputEditText) findViewById(R.id.card_number);
        cvv = (TextInputEditText) findViewById(R.id.cvv);
        regButton = findViewById(R.id.reg_button);


        first_name.addTextChangedListener(listener);
        last_name.addTextChangedListener(listener);
        login.addTextChangedListener(listener);
        password.addTextChangedListener(listener);
        card_number.addTextChangedListener(listener);
        cvv.addTextChangedListener(listener);
    }


    private void checkValid() {
        String fName = first_name.getText().toString();
        if (fName.trim().equals(EMPTY_STRING)) {
            regButton.setEnabled(false);
            return;
        }


        String lName = last_name.getText().toString();
        if (lName.trim().equals(EMPTY_STRING)) {
            regButton.setEnabled(false);
            return;
        }


        String log = login.getText().toString();
        if (log.trim().equals(EMPTY_STRING)) {
            regButton.setEnabled(false);
            return;
        }


        String pass = password.getText().toString();
        if (pass.trim().equals(EMPTY_STRING)) {
            regButton.setEnabled(false);
            return;
        }

        Matcher matcher = CARD_NUMBER_PATTERN.matcher(card_number.getText().toString());
        regButton.setEnabled(matcher.find());

        Matcher matcher1 = CVV_PATTERN.matcher(cvv.getText().toString());
        regButton.setEnabled(matcher1.find());
    }


    private static final Pattern CARD_NUMBER_PATTERN = Pattern.compile("^\\d{16}$");
    private static final Pattern CVV_PATTERN = Pattern.compile("^\\d{3}$");



    private final TextWatcher listener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            checkValid();
        }
    };



    @Override
    public void onBackPressed() {
        finish();
    }


    public void registration(View view) {
        final User user = new User();
        user.setFirst_name(first_name.getText().toString());
        user.setLast_name(last_name.getText().toString());
        user.setLogin(login.getText().toString());
        user.setPassword(password.getText().toString());
        user.setCard_number(card_number.getText().toString());
        user.setCvv(Integer.parseInt(cvv.getText().toString()));

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                ServerApi.registration(user);
                return null;
            }


            @Override
            protected void onPostExecute(Void v) {
                onBackPressed();
            }
        }.execute();
    }

}
