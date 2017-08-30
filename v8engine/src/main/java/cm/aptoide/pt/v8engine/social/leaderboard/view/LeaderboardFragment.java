package cm.aptoide.pt.v8engine.social.leaderboard.view;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import cm.aptoide.pt.dataprovider.WebService;
import cm.aptoide.pt.dataprovider.interfaces.TokenInvalidator;
import cm.aptoide.pt.dataprovider.ws.BodyInterceptor;
import cm.aptoide.pt.dataprovider.ws.v7.BaseBody;
import cm.aptoide.pt.dataprovider.ws.v7.post.GetLeaderboardEntriesResponse;
import cm.aptoide.pt.dataprovider.ws.v7.post.GetUserGameInfoResponse;
import cm.aptoide.pt.v8engine.R;
import cm.aptoide.pt.v8engine.V8Engine;
import cm.aptoide.pt.v8engine.crashreports.CrashReport;
import cm.aptoide.pt.v8engine.social.leaderboard.data.Leaderboard;
import cm.aptoide.pt.v8engine.social.leaderboard.data.LeaderboardEntry;
import cm.aptoide.pt.v8engine.social.leaderboard.presenter.LeaderboardPresenter;
import cm.aptoide.pt.v8engine.view.BackButton;
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
  private ImageView userIcon;
  private TextView userRank;
  private TextView userScore;
  private TextView userName;
  private TokenInvalidator tokenInvalidator;
  private SharedPreferences sharedPreferences;
  private Toolbar toolbar;
  private BackButton backButton;


  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.adapter = new LeaderboardAdapter(Collections.emptyList(), null);
    this.tokenInvalidator = ((V8Engine) getContext().getApplicationContext()).getTokenInvalidator();
    this.sharedPreferences  = ((V8Engine) getContext().getApplicationContext()).getDefaultSharedPreferences();

  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);
    return inflater.inflate(R.layout.fragment_leaderboard, container, false);

  }

  @Override public void showLeaderboardEntries(List<LeaderboardEntry> entries) {
    adapter.updateLeaderboardEntries(entries);
  }

  @Override public void showCurrentUser(GetUserGameInfoResponse.User user){
    userIcon.setImageResource(R.drawable.fake_app);
    userRank.setText(String.valueOf(user.getPosition()));
    userScore.setText(String.valueOf(user.getScore()));
    userName.setText(user.getName());
  }

  @Override public void onViewCreated(android.view.View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    bindViews(view);
    setupViews();

    final BodyInterceptor<BaseBody> baseBodyInterceptorV7 =
        ((V8Engine) getContext().getApplicationContext()).getBaseBodyInterceptorV7();
    final OkHttpClient defaultClient =
        ((V8Engine) getContext().getApplicationContext()).getDefaultClient();
    final Converter.Factory defaultConverter = WebService.getDefaultConverter();

    userIcon = (ImageView) view.findViewById(R.id.user_icon);
    userRank = (TextView) view.findViewById(R.id.rank_value);
    userScore = (TextView) view.findViewById(R.id.score_value);
    userName = (TextView) view.findViewById(R.id.user_name);

    list = (RecyclerView) view.findViewById(R.id.fragment_leaderboard_list);
    list.setLayoutManager(new LinearLayoutManager(getContext()));
    list.setAdapter(adapter);
    attachPresenter(new LeaderboardPresenter(this, new Leaderboard(baseBodyInterceptorV7, defaultClient, defaultConverter,
        tokenInvalidator, sharedPreferences), CrashReport.getInstance()), savedInstanceState);

  }

  @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    return super.onOptionsItemSelected(item);
  }

  @Override public void onDestroy() {
    super.onDestroy();
    toolbar = null;
  }

  protected boolean hasToolbar() {
    return toolbar != null;
  }

  protected boolean displayHomeUpAsEnabled() {
    return true;
  }

  protected void setupToolbarDetails(Toolbar toolbar) {
    // does nothing. placeholder method.
  }

  public void setupToolbar() {
    if (hasToolbar()) {
      ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
      boolean showUp = displayHomeUpAsEnabled();

      ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
      actionBar.setDisplayHomeAsUpEnabled(showUp);
      actionBar.setTitle("Leaderboard");
      setupToolbarDetails(toolbar);
    }
  }

  public void setupViews() {
    setupToolbar();
  }

  public void bindViews(View view){

    this.toolbar = (Toolbar) view.findViewById(R.id.toolbar);
    setHasOptionsMenu(true);
  }
}
