/*
 * Copyright (c) 2017.
 * Modified by Marcelo Benites on 09/02/2017.
 */

package cm.aptoide.pt.v8engine.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import cm.aptoide.pt.preferences.Application;
import cm.aptoide.pt.v8engine.R;

/**
 * Created by trinkes on 4/18/16.
 */
public abstract class AccountBaseActivity extends AppCompatActivity {

  protected static final int LOGGED_IN_SECOND_STEP_CODE = 126;
  private static final String TAG = AccountBaseActivity.class.getSimpleName();

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTitle(getActivityTitle());
    getTheme().applyStyle(Application.getConfiguration().getDefaultThemeRes(), true);
  }

  public abstract String getActivityTitle();

  @LayoutRes public abstract int getLayoutId();

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    int i = item.getItemId();
    if (i == android.R.id.home || i == R.id.home || i == 0) {
      finish();
    }
    return super.onOptionsItemSelected(item);
  }
}
