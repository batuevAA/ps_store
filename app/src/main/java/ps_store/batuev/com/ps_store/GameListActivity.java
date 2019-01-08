package ps_store.batuev.com.ps_store;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.Collections;





public class GameListActivity extends Activity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);

        this.recyclerView = findViewById(R.id.gameList);

        // создаем адаптер
        GameAdapter adapter = new GameAdapter(this, Collections.emptyList());
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);
    }
}
