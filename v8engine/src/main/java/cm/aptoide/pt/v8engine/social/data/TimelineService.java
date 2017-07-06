package cm.aptoide.pt.v8engine.social.data;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import cm.aptoide.pt.dataprovider.interfaces.TokenInvalidator;
import cm.aptoide.pt.dataprovider.ws.BodyInterceptor;
import cm.aptoide.pt.dataprovider.ws.v7.BaseBody;
import cm.aptoide.pt.dataprovider.ws.v7.GetTimelineStatsRequest;
import cm.aptoide.pt.dataprovider.ws.v7.GetUserTimelineRequest;
import cm.aptoide.pt.dataprovider.ws.v7.LikeCardRequest;
import cm.aptoide.pt.dataprovider.ws.v7.ShareCardRequest;
import cm.aptoide.pt.dataprovider.ws.v7.V7;
import cm.aptoide.pt.v8engine.PackageRepository;
import cm.aptoide.pt.v8engine.link.LinksHandlerFactory;
import cm.aptoide.pt.v8engine.repository.exception.RepositoryIllegalArgumentException;
import java.util.Collections;
import java.util.List;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import rx.Completable;
import rx.Observable;
import rx.Single;

/**
 * Created by jdandrade on 31/05/2017.
 */

public class TimelineService {
  private final String url;
  private final Long userId;
  private final BodyInterceptor<BaseBody> bodyInterceptor;
  private final OkHttpClient okhttp;
  private final Converter.Factory converterFactory;
  private final PackageRepository packageRepository;
  private final int latestPackagesCount;
  private final int randomPackagesCount;
  private final TimelineResponseCardMapper mapper;
  private final LinksHandlerFactory linksHandlerFactory;
  private final int limit;
  private final int initialOffset;
  private int currentOffset;
  private boolean loading;
  private int total;
  private TokenInvalidator tokenInvalidator;
  private SharedPreferences sharedPreferences;

  public TimelineService(String url, Long userId, BodyInterceptor<BaseBody> bodyInterceptor,
      OkHttpClient okhttp, Converter.Factory converterFactory, PackageRepository packageRepository,
      int latestPackagesCount, int randomPackagesCount, TimelineResponseCardMapper mapper,
      LinksHandlerFactory linksHandlerFactory, int limit, int initialOffset, int initialTotal,
      TokenInvalidator tokenInvalidator, SharedPreferences sharedPreferences) {
    this.url = url;
    this.userId = userId;
    this.bodyInterceptor = bodyInterceptor;
    this.okhttp = okhttp;
    this.converterFactory = converterFactory;
    this.packageRepository = packageRepository;
    this.latestPackagesCount = latestPackagesCount;
    this.randomPackagesCount = randomPackagesCount;
    this.mapper = mapper;
    this.linksHandlerFactory = linksHandlerFactory;
    this.limit = limit;
    this.initialOffset = initialOffset;
    this.currentOffset = initialOffset;
    this.total = initialTotal;
    this.tokenInvalidator = tokenInvalidator;
    this.sharedPreferences = sharedPreferences;
  }

  public Single<List<Post>> getNextCards() {
    return getCards(limit, currentOffset);
  }

  @NonNull private Single<List<Post>> getCards(int limit, int initialOffset) {
    if (loading || (currentOffset >= total)) {
      return Single.just(Collections.emptyList());
    }
    return getPackages().doOnSuccess(packages -> loading = true)
        .flatMap(packages -> GetUserTimelineRequest.of(url, limit, initialOffset, packages,
            bodyInterceptor, okhttp, converterFactory, null, tokenInvalidator, sharedPreferences)
            .observe()
            .toSingle()
            .flatMap(timelineResponse -> {
              if (timelineResponse.isOk()) {
                this.currentOffset = timelineResponse.getNextSize();
                this.total = timelineResponse.getTotal();
                loading = false;
                return Single.just(timelineResponse);
              }
              return Single.error(
                  new IllegalStateException("Could not obtain timeline from server."));
            }))
        .map(timelineResponse -> mapper.map(timelineResponse, linksHandlerFactory));
  }

  private Single<List<String>> getPackages() {
    return Observable.concat(packageRepository.getLatestInstalledPackages(latestPackagesCount),
        packageRepository.getRandomInstalledPackages(randomPackagesCount))
        .toList()
        .toSingle();
  }

  public Single<List<Post>> getCards() {
    return getCards(limit, initialOffset);
  }

  public Completable like(String postId) {
    return LikeCardRequest.of(postId, bodyInterceptor, okhttp, converterFactory, tokenInvalidator,
        sharedPreferences)
        .observe()
        .flatMapCompletable(response -> {
          if (response.isOk()) {
            return Completable.complete();
          } else {
            return Completable.error(new IllegalStateException(V7.getErrorMessage(response)));
          }
        })
        .toCompletable();
  }

  public Single<Post> getTimelineStats() {
    return GetTimelineStatsRequest.of(bodyInterceptor, userId, okhttp, converterFactory,
        tokenInvalidator, sharedPreferences)
        .observe()
        .toSingle()
        .flatMap(timelineResponse -> {
          if (timelineResponse.isOk()) {
            return Single.just(timelineResponse);
          }
          return Single.error(
              new IllegalStateException("Could not obtain timeline stats from server."));
        })
        .map(timelineStats -> mapper.map(timelineStats));
  }

  public Completable share(Post post) {
    return ShareCardRequest.of(post.getCardId(), bodyInterceptor, okhttp, converterFactory,
        tokenInvalidator, sharedPreferences)
        .observe()
        .flatMapCompletable(response -> {
          if (response.isOk()) {
            return Completable.complete();
          }
          return Completable.error(
              new RepositoryIllegalArgumentException(V7.getErrorMessage(response)));
        })
        .toCompletable();
  }
}
