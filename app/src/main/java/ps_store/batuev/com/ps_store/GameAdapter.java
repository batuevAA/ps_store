package ps_store.batuev.com.ps_store;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;





public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<Game> games;
    private Activity activity;


    public GameAdapter(Activity context, List<Game> games) {
        this.games = games;
        this.inflater = LayoutInflater.from(context);
        this.activity = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.game, parent, false);

        return new ViewHolder(view, activity);
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
        private final Activity activity;
        private TextView gameName;
        private TextView gamePrice;
        private TextView gamePlatform;
        private ImageView gameImage;


        public ViewHolder(View itemView, Activity activity) {
            super(itemView);

            this.gameName = itemView.findViewById(R.id.gameName);
            this.gamePrice = itemView.findViewById(R.id.gamePrice);
            this.gamePlatform = itemView.findViewById(R.id.gamePlatform);
            this.gameImage = itemView.findViewById(R.id.gameImage);
            this.activity = activity;
        }


        @SuppressLint({ "SetTextI18n", "StaticFieldLeak" })
        public void setGame(Game game) {
            final ViewHolder instance = this;

            this.gameName.setText(game.getName());
            this.gamePlatform.setText("Плтаформа: " + game.getConsole());
            this.gamePrice.setText(game.getPrice() + " р.");
            this.gameImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle data = new Bundle();
                    data.putInt("GAME_ID", game.getGame_id());

                    Intent intent = new Intent(instance.activity, GameActivity.class);
                    intent.putExtra("GAME_ID", game.getGame_id());
                    instance.activity.startActivity(intent);
                }
            });


            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    byte[] img = ServerApi.getImage(game.getImage_url());
                    if (img == null)
                        return null;

                    instance.activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Bitmap bitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
                                instance.gameImage.setImageBitmap(bitmap);
                            } catch (Throwable e) {
                                Log.e("AA", "run: ", e);
                            }
                        }
                    });


                    return null;
                }
            }.execute();
        }
    }
}
