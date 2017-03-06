/*
 * Copyright (c) 2016.
 * Modified by Neurophobic Animal on 06/07/2016.
 */

package cm.aptoide.pt.dataprovider.ws.v7.store;

import cm.aptoide.pt.annotation.Partners;
import cm.aptoide.pt.dataprovider.ws.v7.BaseBodyWithStore;
import cm.aptoide.pt.dataprovider.ws.v7.BaseRequestWithStore;
import cm.aptoide.pt.dataprovider.ws.v7.BodyDecorator;
import cm.aptoide.pt.dataprovider.ws.v7.V7Url;
import cm.aptoide.pt.model.v7.GetStoreWidgets;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import rx.Observable;

/**
 * Created by neuro on 22-04-2016.
 */
@Data @EqualsAndHashCode(callSuper = true) public class GetStoreWidgetsRequest
    extends BaseRequestWithStore<GetStoreWidgets, GetStoreWidgetsRequest.Body> {

  private final String url;

  private GetStoreWidgetsRequest(String url, String baseHost, Body body) {
    super(body, baseHost);
    this.url = url;
  }

  private GetStoreWidgetsRequest(String url, OkHttpClient httpClient,
      Converter.Factory converterFactory, String baseHost, Body body) {
    super(body, httpClient, converterFactory, baseHost);
    this.url = url;
  }

  @Partners public static GetStoreWidgetsRequest ofActionFirstInstall(String url,
      StoreCredentials storeCredentials, String accessToken, String storeName,
      BodyDecorator bodyDecorator) {

    final Body body =
        new Body(storeCredentials, WidgetsArgs.createDefault(), StoreContext.first_install,
            storeName);

    return new GetStoreWidgetsRequest(new V7Url(url).remove("getStoreWidgets").get(), BASE_HOST,
        (Body) bodyDecorator.decorate(body));
  }

  public static GetStoreWidgetsRequest ofAction(String url, StoreCredentials storeCredentials,
      String accessToken, BodyDecorator bodyDecorator) {

    final Body body = new Body(storeCredentials, WidgetsArgs.createDefault());

    return new GetStoreWidgetsRequest(new V7Url(url).remove("getStoreWidgets").get(), BASE_HOST,
        (Body) bodyDecorator.decorate(body));
  }

  @Override protected Observable<GetStoreWidgets> loadDataFromNetwork(Interfaces interfaces,
      boolean bypassCache) {
    return interfaces.getStoreWidgets(url, body, bypassCache);
  }

  @EqualsAndHashCode(callSuper = true) public static class Body extends BaseBodyWithStore {

    @Getter private WidgetsArgs widgetsArgs;
    @Getter private StoreContext context;
    @Getter private String storeName;

    public Body(StoreCredentials storeCredentials, WidgetsArgs widgetsArgs) {
      super(storeCredentials);
      this.widgetsArgs = widgetsArgs;
    }

    public Body(StoreCredentials storeCredentials, WidgetsArgs widgetsArgs,
        StoreContext storeContext, String storeName) {
      super(storeCredentials);
      this.widgetsArgs = widgetsArgs;
      this.context = storeContext;
      this.storeName = storeName;
    }
  }
}
