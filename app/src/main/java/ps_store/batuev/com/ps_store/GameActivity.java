package ps_store.batuev.com.ps_store;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;





public class GameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setActionBar(toolbar);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Bundle extra = getIntent().getExtras();
        int gameId = extra.getInt("GAME_ID");

        Game game = null;
        for (int i = 0; i < Game.GAMES.size(); i++) {
            Game g = Game.GAMES.get(i);
            if (g.getGame_id() == gameId) {
                game = g;
                break;
            }
        }

        TextView gameName = findViewById(R.id.gameName);
        gameName.setText(game.getName());

        ImageView gameImage = findViewById(R.id.gameImage);
        byte[] img = ServerApi.getImage(game.getImage_url());
        if (img != null) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
                        gameImage.setImageBitmap(bitmap);
                    } catch (Throwable e) {
                        Log.e("AA", "run: ", e);
                    }
                }
            });
        }

        TextView gameDescription = findViewById(R.id.gameDescription);
        gameDescription.setText(game.getInfo());

        TextView gamePrice = findViewById(R.id.gamePrice);
        gamePrice.setText("Цена: " + game.getPrice() + "р.");
    }
}
