package cm.aptoide.pt.v8engine.social.data;

import android.content.SharedPreferences;
import cm.aptoide.pt.dataprovider.interfaces.TokenInvalidator;
import cm.aptoide.pt.dataprovider.ws.BodyInterceptor;
import cm.aptoide.pt.dataprovider.ws.v7.BaseBody;
import cm.aptoide.pt.dataprovider.ws.v7.GetUserGameInfoRequest;
import cm.aptoide.pt.dataprovider.ws.v7.post.GetUserGameInfoResponse;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import rx.Observable;
import rx.Single;

/**
 * Created by franciscocalado on 9/1/17.
 */

public class UserGameInfo {

  private BodyInterceptor<BaseBody> bodyInterceptor;
  private OkHttpClient httpClient;
  private Converter.Factory converterFactory;
  private TokenInvalidator tokenInvalidator;
  private SharedPreferences sharedPreferences;

  public UserGameInfo(BodyInterceptor<BaseBody> bodyBodyInterceptor, OkHttpClient okHttpClient, Converter.Factory converterFactory,
      TokenInvalidator tokenInvalidator, SharedPreferences sharedPreferences){

    this.bodyInterceptor = bodyBodyInterceptor;
    this.httpClient = okHttpClient;
    this.converterFactory = converterFactory;
    this.tokenInvalidator = tokenInvalidator;
    this.sharedPreferences = sharedPreferences;
  }

  public Observable<GetUserGameInfoResponse.User> getCurrentUser(String url, BodyInterceptor bodyInterceptor, OkHttpClient httpClient,
      Converter.Factory converterFactory, TokenInvalidator tokenInvalidator, SharedPreferences sharedPreferences){
    return GetUserGameInfoRequest.of("", bodyInterceptor, httpClient,
        converterFactory, tokenInvalidator, sharedPreferences)
        .observe(true)
        .flatMap(getUserGameInfoResponse -> Observable.just(getUserGameInfoResponse.getData().getCurrent()));
  }
}
