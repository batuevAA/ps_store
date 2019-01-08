package ps_store.batuev.com.ps_store;
import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;





public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Game> games;


    public GameAdapter(Context context, List<Game> games) {
        this.games = games;
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.game, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setGame(games.get(position));
    }


    @Override
    public int getItemCount() {
        return games.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView gameName;
        private TextView gamePrice;
        private TextView gamePlatform;
        private ImageView gameImage;


        public ViewHolder(View itemView) {
            super(itemView);

            this.gameName = itemView.findViewById(R.id.gameName);
            this.gamePrice = itemView.findViewById(R.id.gamePrice);
            this.gamePlatform = itemView.findViewById(R.id.gamePlatform);
            this.gameImage = itemView.findViewById(R.id.gameImage);
        }


        @SuppressLint("SetTextI18n")
        public void setGame(Game game) {
            this.gameName.setText(game.getName());
            this.gamePlatform.setText("Плтаформа: " + game.getConsole());
            this.gamePrice.setText(game.getPrice() + " р.");
        }
    }
}
