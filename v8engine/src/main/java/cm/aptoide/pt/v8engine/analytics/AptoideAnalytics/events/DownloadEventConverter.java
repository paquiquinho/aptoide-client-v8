package cm.aptoide.pt.v8engine.analytics.AptoideAnalytics.events;

import cm.aptoide.accountmanager.AptoideAccountManager;
import cm.aptoide.pt.dataprovider.repository.IdsRepositoryImpl;
import cm.aptoide.pt.dataprovider.ws.v7.BodyDecorator;
import cm.aptoide.pt.dataprovider.ws.v7.analyticsbody.DownloadInstallAnalyticsBaseBody;
import cm.aptoide.pt.v8engine.BaseBodyDecorator;

/**
 * Created by trinkes on 05/01/2017.
 */

public class DownloadEventConverter extends DownloadInstallEventConverter<DownloadEvent> {

  private final BodyDecorator bodyDecorator;

  public DownloadEventConverter(BodyDecorator bodyDecorator) {
    this.bodyDecorator = bodyDecorator;
  }

  @Override
  protected DownloadInstallAnalyticsBaseBody.Data convertSpecificFields(DownloadEvent report,
      DownloadInstallAnalyticsBaseBody.Data data) {
    data.getApp().setMirror(report.getMirrorApk());
    for (int i = 0; data.getObb() != null && i < data.getObb().size(); i++) {
      if (i == 0) {
        data.getObb().get(0).setMirror(report.getMirrorObbMain());
      } else {
        data.getObb().get(1).setMirror(report.getMirrorObbPatch());
      }
    }
    return data;
  }

  @Override protected DownloadEvent createEventObject(DownloadInstallBaseEvent.Action action,
      DownloadInstallBaseEvent.Origin origin, String packageName, String url, String obbUrl,
      String patchObbUrl, DownloadInstallBaseEvent.AppContext context, int versionCode) {
    return new DownloadEvent(action, origin, packageName, url, obbUrl, patchObbUrl, context,
        versionCode, this, bodyDecorator);
  }
}
