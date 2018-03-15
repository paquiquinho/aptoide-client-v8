package cm.aptoide.pt.home.apps;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import cm.aptoide.pt.R;
import cm.aptoide.pt.networking.image.ImageLoader;
import rx.subjects.PublishSubject;

/**
 * Created by filipegoncalves on 3/9/18.
 */

class UpdateAppViewHolder extends AppsViewHolder {

  private TextView appName;
  private ImageView appIcon;
  private PublishSubject<App> updateApp;
  private ImageView updateAppButton;
  private TextView appVersion;

  public UpdateAppViewHolder(View itemView, PublishSubject<App> updateApp) {
    super(itemView);
    appName = (TextView) itemView.findViewById(R.id.apps_updates_app_name);
    appIcon = (ImageView) itemView.findViewById(R.id.apps_updates_app_icon);
    updateAppButton = (ImageView) itemView.findViewById(R.id.apps_updates_update_button);
    appVersion = (TextView) itemView.findViewById(R.id.apps_updates_app_version);
    this.updateApp = updateApp;
  }

  @Override public void setApp(App app) {
    appName.setText(((UpdateApp) app).getName());
    ImageLoader.with(itemView.getContext())
        .load(((UpdateApp) app).getIcon(), appIcon);
    updateAppButton.setOnClickListener(install -> updateApp.onNext(app));
    appVersion.setText(((UpdateApp) app).getVersion());
  }
}
