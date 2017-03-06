package cm.aptoide.pt.dataprovider.ws.v7;

import cm.aptoide.pt.model.v7.GetFollowers;
import rx.Observable;

/**
 * Created by trinkes on 16/12/2016.
 */

public class GetFollowingRequest extends V7<GetFollowers, GetFollowersRequest.Body> {

  protected GetFollowingRequest(GetFollowersRequest.Body body, String baseHost) {
    super(body, baseHost);
  }

  public static GetFollowingRequest of(BodyDecorator bodyDecorator) {

    return new GetFollowingRequest(
        ((GetFollowersRequest.Body) bodyDecorator.decorate(new GetFollowersRequest.Body())), BASE_HOST);
  }

  @Override protected Observable<GetFollowers> loadDataFromNetwork(Interfaces interfaces,
      boolean bypassCache) {
    return interfaces.getTimelineGetFollowing(body, bypassCache);
  }
}
