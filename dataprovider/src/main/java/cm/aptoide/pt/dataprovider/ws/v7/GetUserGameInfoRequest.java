package cm.aptoide.pt.dataprovider.ws.v7;

import android.content.SharedPreferences;
import cm.aptoide.pt.dataprovider.interfaces.TokenInvalidator;
import cm.aptoide.pt.dataprovider.ws.BodyInterceptor;
import cm.aptoide.pt.dataprovider.ws.v7.post.GetUserGameInfoResponse;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import rx.Observable;

/**
 * Created by franciscocalado on 8/30/17.
 */

public class GetUserGameInfoRequest
    extends V7<GetUserGameInfoResponse, GetUserGameInfoRequest.Body> {

  private String url = "http://192.168.1.100:5000/api/7/user/timeline/game/getUserInfo/";

  GetUserGameInfoRequest(String url, GetUserGameInfoRequest.Body body,
      BodyInterceptor<BaseBody> bodyInterceptor, OkHttpClient httpClient,
      Converter.Factory converterFactory, TokenInvalidator tokenInvalidator,
      SharedPreferences sharedPreferences) {
    super(body, getHost(sharedPreferences), httpClient, converterFactory, bodyInterceptor,
        tokenInvalidator);
  }

  public static GetUserGameInfoRequest of(String url,
      BodyInterceptor<BaseBody> bodyInterceptor, OkHttpClient httpClient,
      Converter.Factory converterFactory, TokenInvalidator tokenInvalidator,
      SharedPreferences sharedPreferences) {

    return new GetUserGameInfoRequest(url, new GetUserGameInfoRequest.Body(sharedPreferences),
        bodyInterceptor, httpClient, converterFactory, tokenInvalidator, sharedPreferences);
  }

  @Override protected Observable<GetUserGameInfoResponse> loadDataFromNetwork(Interfaces interfaces,
      boolean bypassCache) {
    return interfaces.getUserGameInfo(url, body, bypassCache);
  }

  public static class Body extends BaseBodyWithAlphaBetaKey {

    protected Body(SharedPreferences sharedPreferences) {
      super(sharedPreferences);
    }

  }
}
