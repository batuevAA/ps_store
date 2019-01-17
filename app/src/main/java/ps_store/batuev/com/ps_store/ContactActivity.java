package ps_store.batuev.com.ps_store;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

public class ContactActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        Toolbar contact = findViewById(R.id.contact);
        setActionBar(contact);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowHomeEnabled(true);
        contact.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }

        });

        TextView fio = findViewById(R.id.fio);
        fio.setText("Группа: РИС-16-1бзу \n" +
                "Батуев Артём Александрович" );

        TextView mail = findViewById(R.id.mail);
        mail.setText("Email:  artbatu@yandex.ru" );


    }

}
