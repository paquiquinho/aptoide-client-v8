package cm.aptoide.pt.v8engine.social.leaderboard.view;

import cm.aptoide.pt.v8engine.presenter.View;
import cm.aptoide.pt.v8engine.social.leaderboard.data.LeaderboardEntry;
import java.util.List;

/**
 * Created by franciscocalado on 8/17/17.
 */

public interface LeaderboardView extends View {

  void showLeaderboardEntries(List<LeaderboardEntry> entries);
}
