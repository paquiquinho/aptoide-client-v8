package cm.aptoide.pt.v8engine.presenter;

import cm.aptoide.pt.model.v7.store.GetStore;
import cm.aptoide.pt.v8engine.notification.AptoideNotification;
import java.util.List;
import rx.Observable;

public interface MyAccountView extends View {
  Observable<Void> signOutClick();

  Observable<Void> moreNotificationsClick();

  Observable<AptoideNotification> notificationSelection();

  void showNotifications(List<AptoideNotification> notifications);

  Observable<Void> editStoreClick();

  Observable<GetStore> getStore();

  Observable<Void> editUserProfileClick();

  void navigateToHome();

  void showHeader(Boolean hasNotifications);
}
