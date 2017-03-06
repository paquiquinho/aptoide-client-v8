package cm.aptoide.pt.v8engine.repository.request;

import cm.aptoide.accountmanager.AptoideAccountManager;
import cm.aptoide.pt.dataprovider.ws.v7.BodyDecorator;
import cm.aptoide.pt.dataprovider.ws.v7.V7EndlessController;
import cm.aptoide.pt.dataprovider.ws.v7.store.ListStoresRequest;
import cm.aptoide.pt.interfaces.AptoideClientUUID;
import cm.aptoide.pt.model.v7.store.Store;
import cm.aptoide.pt.v8engine.BaseBodyDecorator;

/**
 * Created by neuro on 03-01-2017.
 */
class ListStoresRequestFactory {

  private final AptoideClientUUID aptoideClientUUID;
  private final AptoideAccountManager accountManager;
  private BodyDecorator bodyDecorator;

  public ListStoresRequestFactory(AptoideClientUUID aptoideClientUUID,
      AptoideAccountManager accountManager, BodyDecorator baseBodyDecorator) {
    this.aptoideClientUUID = aptoideClientUUID;
    this.accountManager = accountManager;
    this.bodyDecorator = baseBodyDecorator;
  }

  public ListStoresRequest newListStoresRequest(int offset, int limit) {
    return ListStoresRequest.ofTopStores(offset, limit, accountManager.getAccessToken(),
        bodyDecorator);
  }

  public V7EndlessController<Store> listStores(int offset, int limit) {
    return new V7EndlessController<>(
        ListStoresRequest.ofTopStores(offset, limit, accountManager.getAccessToken(),
            bodyDecorator));
  }

  public ListStoresRequest newListStoresRequest(String url) {
    return ListStoresRequest.ofAction(url, bodyDecorator);
  }
}
