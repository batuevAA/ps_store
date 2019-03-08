package ps_store.batuev.com.ps_store;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;





public class GameListActivity extends AppCompatActivity
{

    private RecyclerView recyclerView;
    private Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);

        this.recyclerView = findViewById(R.id.gameList);
        this.spinner = findViewById(R.id.consoleType);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Пока - кнопка назад; Нужно сделать менюшку (автор и выход)
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        TextView userCash = findViewById(R.id.balance);
//        userCash.setText("Баланс: " + User.CURRENT_USER.getCash() + " р.");

        this.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] choose = getResources().getStringArray(R.array.consoleList);
                String selected = choose[position];
                GamePlatform platform = GamePlatform.of(selected);

                List<Game> games = Game.GAMES;
                if (platform != null) {
                    List<Game> filteredGames = new ArrayList<>();
                    for (Game game : Game.GAMES) {
                        if (game.getConsole() != platform)
                            continue;

                        filteredGames.add(game);
                    }

                    games = filteredGames;
                }

                GameAdapter adapter = new GameAdapter(GameListActivity.this, games);
                recyclerView.setAdapter(adapter);
            }




            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        GameAdapter adapter = new GameAdapter(this, Game.GAMES);
        recyclerView.setAdapter(adapter);


    }

}
