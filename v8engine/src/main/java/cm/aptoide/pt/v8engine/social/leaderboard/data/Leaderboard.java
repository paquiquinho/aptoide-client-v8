package cm.aptoide.pt.v8engine.social.leaderboard.data;

import android.content.SharedPreferences;
import cm.aptoide.pt.dataprovider.interfaces.TokenInvalidator;
import cm.aptoide.pt.dataprovider.ws.BodyInterceptor;
import cm.aptoide.pt.dataprovider.ws.v7.BaseBody;
import cm.aptoide.pt.dataprovider.ws.v7.GetLeaderboardEntriesRequest;
import cm.aptoide.pt.dataprovider.ws.v7.post.GetLeaderboardEntriesResponse;
import java.util.List;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import rx.Observable;
import rx.Single;

/**
 * Created by franciscocalado on 8/17/17.
 */

public class Leaderboard {
  private BodyInterceptor<BaseBody> bodyInterceptor;
  private OkHttpClient httpClient;
  private Converter.Factory converterFactory;
  private TokenInvalidator tokenInvalidator;
  private SharedPreferences sharedPreferences;
  private LeaderboardEntryMapper mapper;

  public Leaderboard(BodyInterceptor<BaseBody> bodyBodyInterceptor, OkHttpClient okHttpClient, Converter.Factory converterFactory,
      TokenInvalidator tokenInvalidator, SharedPreferences sharedPreferences){
    this.bodyInterceptor = bodyBodyInterceptor;
    this.httpClient = okHttpClient;
    this.converterFactory = converterFactory;
    this.tokenInvalidator = tokenInvalidator;
    this.sharedPreferences = sharedPreferences;
    mapper = new LeaderboardEntryMapper();
  }

  public Observable<List<LeaderboardEntry>> getLeaderboardEntries() {
    return GetLeaderboardEntriesRequest.of("", "", 0, 10, bodyInterceptor, httpClient,
        converterFactory, tokenInvalidator, sharedPreferences)
        .observe(true)
        .flatMap(getLeaderboardEntriesResponse -> Observable.just(getLeaderboardEntriesResponse.getData()
            .getLeaderboard())).map(users -> mapper.map(users));
  }

  public Observable<GetLeaderboardEntriesResponse.User> getCurrentUser(){
    return GetLeaderboardEntriesRequest.of("", "", 0, 10, bodyInterceptor, httpClient,
        converterFactory, tokenInvalidator, sharedPreferences)
        .observe(true)
        .flatMap(getLeaderboardEntriesResponse -> Observable.just(getLeaderboardEntriesResponse.getData().getCurrent()));
  }
}
