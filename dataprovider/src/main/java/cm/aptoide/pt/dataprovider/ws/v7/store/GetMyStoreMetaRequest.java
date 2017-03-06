package cm.aptoide.pt.dataprovider.ws.v7.store;

import cm.aptoide.pt.dataprovider.ws.v7.BaseBody;
import cm.aptoide.pt.dataprovider.ws.v7.BodyDecorator;
import cm.aptoide.pt.dataprovider.ws.v7.V7;
import cm.aptoide.pt.model.v7.store.GetStoreMeta;
import rx.Observable;

/**
 * Created by trinkes on 12/12/2016.
 */

public class GetMyStoreMetaRequest extends V7<GetStoreMeta, BaseBody> {

  public GetMyStoreMetaRequest(BaseBody body, String baseHost) {
    super(body, baseHost);
  }

  public static GetMyStoreMetaRequest of(BodyDecorator bodyDecorator) {
    return new GetMyStoreMetaRequest(bodyDecorator.decorate(new BaseBody()), BASE_HOST);
  }

  @Override protected Observable<GetStoreMeta> loadDataFromNetwork(Interfaces interfaces,
      boolean bypassCache) {
    return interfaces.getMyStoreMeta(body, bypassCache);
  }
}
