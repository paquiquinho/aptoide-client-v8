/*
 * Copyright (c) 2016.
 * Modified by Marcelo Benites on 11/08/2016.
 */

package cm.aptoide.pt.v8engine.billing.repository;

import android.support.annotation.NonNull;
import cm.aptoide.accountmanager.AptoideAccountManager;
import cm.aptoide.pt.database.accessors.PaymentConfirmationAccessor;
import cm.aptoide.pt.dataprovider.NetworkOperatorManager;
import cm.aptoide.pt.dataprovider.ws.BodyInterceptor;
import cm.aptoide.pt.dataprovider.ws.v3.BaseBody;
import cm.aptoide.pt.dataprovider.ws.v3.InAppBillingAvailableRequest;
import cm.aptoide.pt.dataprovider.ws.v3.InAppBillingConsumeRequest;
import cm.aptoide.pt.dataprovider.ws.v3.InAppBillingPurchasesRequest;
import cm.aptoide.pt.dataprovider.ws.v3.InAppBillingSkuDetailsRequest;
import cm.aptoide.pt.dataprovider.ws.v3.V3;
import cm.aptoide.pt.model.v3.ErrorResponse;
import cm.aptoide.pt.model.v3.InAppBillingPurchasesResponse;
import cm.aptoide.pt.model.v3.InAppBillingSkuDetailsResponse;
import cm.aptoide.pt.v8engine.billing.inapp.SKU;
import cm.aptoide.pt.v8engine.repository.exception.RepositoryIllegalArgumentException;
import cm.aptoide.pt.v8engine.repository.exception.RepositoryItemNotFoundException;
import java.util.Collections;
import java.util.List;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import rx.Completable;
import rx.Observable;
import rx.Single;

/**
 * Created by marcelobenites on 8/11/16.
 */
public class InAppBillingRepository {

  private final NetworkOperatorManager operatorManager;
  private final PaymentConfirmationAccessor confirmationAccessor;
  private final AptoideAccountManager accountManager;
  private final BodyInterceptor<BaseBody> bodyInterceptorV3;
  private final OkHttpClient httpClient;
  private final Converter.Factory converterFactory;

  public InAppBillingRepository(NetworkOperatorManager operatorManager,
      PaymentConfirmationAccessor confirmationAccessor, AptoideAccountManager accountManager,
      BodyInterceptor<BaseBody> bodyInterceptorV3, OkHttpClient httpClient,
      Converter.Factory converterFactory) {
    this.operatorManager = operatorManager;
    this.confirmationAccessor = confirmationAccessor;
    this.accountManager = accountManager;
    this.bodyInterceptorV3 = bodyInterceptorV3;
    this.httpClient = httpClient;
    this.converterFactory = converterFactory;
  }

  public Observable<Void> getInAppBilling(int apiVersion, String packageName, String type) {
    return InAppBillingAvailableRequest.of(apiVersion, packageName, type, bodyInterceptorV3,
        httpClient, converterFactory).observe().flatMap(response -> {
      if (response != null && response.isOk()) {
        if (response.getInAppBillingAvailable().isAvailable()) {
          return Observable.just(null);
        } else {
          return Observable.error(
              new RepositoryItemNotFoundException(V3.getErrorMessage(response)));
        }
      } else {
        return Observable.error(
            new RepositoryIllegalArgumentException(V3.getErrorMessage(response)));
      }
    });
  }

  public Observable<List<SKU>> getSKUs(int apiVersion, String packageName, List<String> skuList,
      String type) {
    return getSKUListDetails(apiVersion, packageName, skuList, type).flatMapObservable(
        details -> Observable.from(details.getPublisherResponse().getDetailList())
            .map(detail -> new SKU(detail.getProductId(), detail.getType(), detail.getPrice(),
                detail.getCurrency(), (long) (detail.getPriceAmount() * 1000000), detail.getTitle(),
                detail.getDescription()))
            .toList());
  }

  private Single<InAppBillingSkuDetailsResponse> getSKUListDetails(int apiVersion,
      String packageName, List<String> skuList, String type) {
    return InAppBillingSkuDetailsRequest.of(apiVersion, packageName, skuList, operatorManager, type,
        bodyInterceptorV3, httpClient, converterFactory)
        .observe()
        .first()
        .toSingle()
        .flatMap(response -> {
          if (response != null && response.isOk()) {
            return Single.just(response);
          } else {
            final List<InAppBillingSkuDetailsResponse.PurchaseDataObject> detailList =
                response.getPublisherResponse().getDetailList();
            if (detailList.isEmpty()) {
              return Single.error(
                  new RepositoryItemNotFoundException(V3.getErrorMessage(response)));
            }
            return Single.error(
                new RepositoryIllegalArgumentException(V3.getErrorMessage(response)));
          }
        });
  }

  public Observable<InAppBillingPurchasesResponse.PurchaseInformation> getInAppPurchaseInformation(
      int apiVersion, String packageName, String type) {
    return InAppBillingPurchasesRequest.of(apiVersion, packageName, type, bodyInterceptorV3,
        httpClient, converterFactory).observe().flatMap(response -> {
      if (response != null && response.isOk()) {
        return Observable.just(response.getPurchaseInformation());
      }
      return Observable.error(new RepositoryIllegalArgumentException(V3.getErrorMessage(response)));
    });
  }

  public Completable deleteInAppPurchase(int apiVersion, String packageName, String purchaseToken) {
    return InAppBillingConsumeRequest.of(apiVersion, packageName, purchaseToken, bodyInterceptorV3,
        httpClient, converterFactory).observe().first().toSingle().flatMapCompletable(response -> {
      if (response != null && response.isOk()) {
        // TODO sync all payment confirmations instead. For now there is no web service for that.
        confirmationAccessor.removeAll();
        return Completable.complete();
      }
      if (isDeletionItemNotFound(response.getErrors())) {
        return Completable.error(new RepositoryItemNotFoundException(V3.getErrorMessage(response)));
      }
      return Completable.error(
          new RepositoryIllegalArgumentException(V3.getErrorMessage(response)));
    });
  }

  @NonNull private boolean isDeletionItemNotFound(List<ErrorResponse> errors) {
    for (ErrorResponse error : errors) {
      if (error.code.equals("PRODUCT-201")) {
        return true;
      }
    }
    return false;
  }

  public Single<InAppBillingSkuDetailsResponse> getSKUDetails(int apiVersion, String packageName,
      String sku, String type) {
    return getSKUListDetails(apiVersion, packageName, Collections.singletonList(sku), type);
  }
}
