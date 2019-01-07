package ps_store.batuev.com.ps_store;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
    private EditText first_name;
    private EditText last_name;
    private EditText login;
    private EditText password;
    private EditText card_number;
    private EditText ccv;
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

        first_name = findViewById(R.id.first_name);
        last_name  = findViewById(R.id.last_name);
        login = findViewById(R.id.login);
        password = findViewById(R.id.password);
        card_number = findViewById(R.id.card_number);
        ccv = findViewById(R.id.ccv);
        regButton = findViewById(R.id.reg_button);


        first_name.addTextChangedListener(listener);
        last_name.addTextChangedListener(listener);
        login.addTextChangedListener(listener);
        password.addTextChangedListener(listener);
        card_number.addTextChangedListener(listener);
        ccv.addTextChangedListener(listener);
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


        String code = ccv.getText().toString();
        if (code.trim().equals(EMPTY_STRING)) {
            regButton.setEnabled(false);
            return;
        }


        Matcher matcher = CARD_NUMBER_PATTERN.matcher(card_number.getText().toString());
        regButton.setEnabled(matcher.find());
    }


    private static final Pattern CARD_NUMBER_PATTERN = Pattern.compile("^\\d{16}$");




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
        User user = new User();
        user.setFirst_name(first_name.getText().toString());
        user.setLast_name(last_name.getText().toString());
        user.setLogin(login.getText().toString());
        user.setPassword(password.getText().toString());
        user.setCard_number(card_number.getText().toString());
        user.setCvv(Integer.parseInt(ccv.getText().toString()));

        ServerApi.registration(user);
    }

}
