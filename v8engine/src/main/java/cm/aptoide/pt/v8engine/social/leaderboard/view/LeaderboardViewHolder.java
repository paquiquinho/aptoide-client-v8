package cm.aptoide.pt.v8engine.social.leaderboard.view;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import cm.aptoide.pt.dataprovider.ws.v7.post.GetLeaderboardEntriesResponse;
import cm.aptoide.pt.v8engine.R;
import cm.aptoide.pt.v8engine.social.leaderboard.data.LeaderboardEntry;

/**
 * Created by franciscocalado on 8/18/17.
 */

public class LeaderboardViewHolder extends RecyclerView.ViewHolder {

  private final TextView name;
  private final TextView score;
  private final ImageView main;


  public LeaderboardViewHolder(View itemView) {
    super(itemView);
    this.name = (TextView) itemView.findViewById(R.id.leaderboard_user_name);
    this.score = (TextView) itemView.findViewById(R.id.leaderboard_user_score);
    this.main = (ImageView) itemView.findViewById(R.id.main_icon);
  }

  public void setItem(LeaderboardEntry entry){


    name.setText("#"+entry.getPosition()+" "+entry.getName());
    score.setText(String.valueOf(entry.getScore()));
    main.setImageResource(R.drawable.fake_app);
  }
}