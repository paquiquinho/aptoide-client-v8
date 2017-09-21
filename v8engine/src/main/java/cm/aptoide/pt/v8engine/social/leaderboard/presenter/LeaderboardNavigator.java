package cm.aptoide.pt.v8engine.social.leaderboard.presenter;

import cm.aptoide.pt.v8engine.V8Engine;
import cm.aptoide.pt.v8engine.view.navigator.FragmentNavigator;
import cm.aptoide.pt.v8engine.view.navigator.TabNavigator;
import cm.aptoide.pt.v8engine.view.store.StoreFragment;

/**
 * Created by franciscocalado on 9/7/17.
 */

public class LeaderboardNavigator implements LeaderboardNavigation {

  private final FragmentNavigator fragmentNavigator;
  private final TabNavigator tabNavigator;

  public LeaderboardNavigator(FragmentNavigator fragmentNavigator, TabNavigator tabNavigator){

    this.fragmentNavigator = fragmentNavigator;
    this.tabNavigator = tabNavigator;
  }

  @Override public void navigateToUser(Long userId) {
    fragmentNavigator.navigateTo(V8Engine.getFragmentProvider()
        .newStoreFragment(userId, "DEFAULT", StoreFragment.OpenType.GetHome));

  }
}
