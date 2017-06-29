package cm.aptoide.pt.v8engine.billing.repository.sync;

import cm.aptoide.pt.v8engine.billing.Product;
import rx.Completable;

public interface PaymentSyncScheduler {
  Completable scheduleAuthorizationSync(int paymentId);

  Completable scheduleTransactionSync(Product product);
}
