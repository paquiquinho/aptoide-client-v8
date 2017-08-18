package cm.aptoide.pt.v8engine.social.leaderboard.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import cm.aptoide.pt.v8engine.R;
import cm.aptoide.pt.v8engine.social.leaderboard.data.LeaderboardEntry;

/**
 * Created by franciscocalado on 8/18/17.
 */

public class LeaderboardViewHolder extends RecyclerView.ViewHolder {

  private final TextView name;
  private final TextView score;

  public LeaderboardViewHolder(View itemView) {
    super(itemView);
    this.name = (TextView) itemView.findViewById(R.id.user_name);
    this.score = (TextView) itemView.findViewById(R.id.user_score);
  }

  public void setItem(LeaderboardEntry entry){
    name.setText("#"+entry.getPosition()+" "+entry.getName());
    score.setText(entry.getScore());
  }
}
