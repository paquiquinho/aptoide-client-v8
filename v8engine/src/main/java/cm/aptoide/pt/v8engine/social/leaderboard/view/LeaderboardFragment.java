package cm.aptoide.pt.v8engine.social.leaderboard.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import cm.aptoide.pt.v8engine.crashreports.CrashReport;
import cm.aptoide.pt.v8engine.social.leaderboard.data.Leaderboard;
import cm.aptoide.pt.v8engine.social.leaderboard.data.LeaderboardEntry;
import cm.aptoide.pt.v8engine.social.leaderboard.presenter.LeaderboardPresenter;
import cm.aptoide.pt.v8engine.view.fragment.FragmentView;
import java.util.List;

/**
 * Created by franciscocalado on 8/17/17.
 */

public class LeaderboardFragment extends FragmentView implements LeaderboardView {

  @Override public void showLeaderboardEntries(List<LeaderboardEntry> entries) {

  }

  @Override public void onViewCreated(android.view.View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    attachPresenter(new LeaderboardPresenter(this, new Leaderboard(), CrashReport.getInstance()), savedInstanceState);
  }
}
