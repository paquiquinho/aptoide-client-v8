/*
 * Copyright (c) 2016.
 * Modified by Marcelo Benites on 29/09/2016.
 */

package cm.aptoide.pt.v8engine.install.installer;

import rx.Observable;

/**
 * Created by marcelobenites on 7/25/16.
 */
public interface InstallationProvider {

  Observable<? extends RollbackInstallation> getInstallation(String md5);
}
