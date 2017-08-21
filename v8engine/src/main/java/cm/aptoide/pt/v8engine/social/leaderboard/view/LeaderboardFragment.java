package cm.aptoide.pt.v8engine.social.leaderboard.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cm.aptoide.pt.dataprovider.WebService;
import cm.aptoide.pt.dataprovider.interfaces.TokenInvalidator;
import cm.aptoide.pt.dataprovider.ws.BodyInterceptor;
import cm.aptoide.pt.dataprovider.ws.v7.BaseBody;
import cm.aptoide.pt.v8engine.R;
import cm.aptoide.pt.v8engine.V8Engine;
import cm.aptoide.pt.v8engine.crashreports.CrashReport;
import cm.aptoide.pt.v8engine.social.leaderboard.data.Leaderboard;
import cm.aptoide.pt.v8engine.social.leaderboard.data.LeaderboardEntry;
import cm.aptoide.pt.v8engine.social.leaderboard.presenter.LeaderboardPresenter;
import cm.aptoide.pt.v8engine.view.fragment.FragmentView;
import java.util.Collections;
import java.util.List;
import okhttp3.OkHttpClient;
import retrofit2.Converter;

/**
 * Created by franciscocalado on 8/17/17.
 */

public class LeaderboardFragment extends FragmentView implements LeaderboardView {

  private LeaderboardAdapter adapter;
  private RecyclerView list;
  private TokenInvalidator tokenInvalidator;
  private SharedPreferences sharedPreferences;

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.adapter = new LeaderboardAdapter(Collections.emptyList());
    this.tokenInvalidator = ((V8Engine) getContext().getApplicationContext()).getTokenInvalidator();
    this.sharedPreferences  = ((V8Engine) getContext().getApplicationContext()).getDefaultSharedPreferences();

  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_leaderboard, container, false);

  }

  @Override public void showLeaderboardEntries(List<LeaderboardEntry> entries) {
    adapter.updateLeaderboardEntries(entries);
  }

  @Override public void onViewCreated(android.view.View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    final BodyInterceptor<BaseBody> baseBodyInterceptorV7 =
        ((V8Engine) getContext().getApplicationContext()).getBaseBodyInterceptorV7();
    final OkHttpClient defaultClient =
        ((V8Engine) getContext().getApplicationContext()).getDefaultClient();
    final Converter.Factory defaultConverter = WebService.getDefaultConverter();

    list = (RecyclerView) view.findViewById(R.id.fragment_leaderboard_list);
    list.setLayoutManager(new LinearLayoutManager(getContext()));
    list.setAdapter(adapter);
    attachPresenter(new LeaderboardPresenter(this, new Leaderboard(baseBodyInterceptorV7, defaultClient, defaultConverter,
        tokenInvalidator, sharedPreferences), CrashReport.getInstance()), savedInstanceState);
  }
}
