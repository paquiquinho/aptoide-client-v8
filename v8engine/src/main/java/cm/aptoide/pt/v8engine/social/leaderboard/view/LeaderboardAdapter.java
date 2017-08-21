package cm.aptoide.pt.v8engine.social.leaderboard.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import cm.aptoide.pt.v8engine.R;
import cm.aptoide.pt.v8engine.social.leaderboard.data.LeaderboardEntry;
import java.util.List;

/**
 * Created by franciscocalado on 8/18/17.
 */

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardViewHolder> {

  private List<LeaderboardEntry> entries;

  public LeaderboardAdapter(List<LeaderboardEntry> entries){
    this.entries=entries;
  }

  @Override public LeaderboardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new LeaderboardViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.fragment_leaderboard_list_item, parent, false));
  }

  @Override public void onBindViewHolder(LeaderboardViewHolder holder, int position) {
    holder.setItem(entries.get(position));
  }

  @Override public int getItemCount() {
    return entries.size();
  }

  public void updateLeaderboardEntries(List<LeaderboardEntry> entries){
    this.entries=entries;
    notifyDataSetChanged();
  }
}
