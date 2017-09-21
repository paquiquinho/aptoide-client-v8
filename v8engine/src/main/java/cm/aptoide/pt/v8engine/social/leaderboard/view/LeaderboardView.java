package cm.aptoide.pt.v8engine.social.leaderboard.view;

import cm.aptoide.pt.dataprovider.ws.v7.post.GetLeaderboardEntriesResponse;
import cm.aptoide.pt.dataprovider.ws.v7.post.GetUserGameInfoResponse;
import cm.aptoide.pt.v8engine.presenter.View;
import cm.aptoide.pt.v8engine.social.data.CardTouchEvent;
import cm.aptoide.pt.v8engine.social.leaderboard.data.LeaderboardEntry;
import java.util.List;
import rx.Observable;

/**
 * Created by franciscocalado on 8/17/17.
 */

public interface LeaderboardView extends View {

  void showLeaderboardEntries(List<List<LeaderboardEntry>> entries);
  void showCurrentUser(GetUserGameInfoResponse.User user);
  Observable<LeaderboardEntry> postClicked();
}
