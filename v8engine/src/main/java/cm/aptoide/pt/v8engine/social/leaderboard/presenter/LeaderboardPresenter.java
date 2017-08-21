package cm.aptoide.pt.v8engine.social.leaderboard.presenter;

import android.os.Bundle;
import cm.aptoide.pt.v8engine.crashreports.CrashReport;
import cm.aptoide.pt.v8engine.presenter.Presenter;
import cm.aptoide.pt.v8engine.presenter.View;
import cm.aptoide.pt.v8engine.social.leaderboard.data.Leaderboard;
import cm.aptoide.pt.v8engine.social.leaderboard.view.LeaderboardView;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by franciscocalado on 8/17/17.
 */

public class LeaderboardPresenter implements Presenter {

  private final LeaderboardView view;
  private final Leaderboard leaderboard;
  private final CrashReport crashReport;

  public LeaderboardPresenter(LeaderboardView view, Leaderboard leaderboard,
      CrashReport crashReport) {

    this.view = view;
    this.leaderboard = leaderboard;
    this.crashReport = crashReport;
  }

  @Override public void saveState(Bundle state) {

  }

  @Override public void restoreState(Bundle state) {

  }

  @Override public void present() {
    view.getLifecycle()
        .filter(event -> event.equals(View.LifecycleEvent.CREATE))
        .flatMap(__-> leaderboard.getLeaderboardEntries())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnNext(leaderboardEntries->view.showLeaderboardEntries(leaderboardEntries))
        .compose(view.bindUntilEvent(View.LifecycleEvent.DESTROY))
        .subscribe(leaderboardEntries -> {},
            throwable -> crashReport.log(throwable));
  }

}
