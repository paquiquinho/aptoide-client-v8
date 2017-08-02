/*
 * Copyright (c) 2016.
 * Modified by pedroribeiro on 19/01/2017
 */

package cm.aptoide.pt.v8engine.util;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.database.Cursor;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import cm.aptoide.pt.utils.design.ShowMessage;
import cm.aptoide.pt.v8engine.R;
import cm.aptoide.pt.v8engine.V8Engine;
import cm.aptoide.pt.v8engine.crashreports.CrashReport;
import cm.aptoide.pt.v8engine.search.websocket.SearchAppsWebSocket;
import cm.aptoide.pt.v8engine.view.navigator.FragmentNavigator;
import cm.aptoide.pt.v8engine.view.search.SearchActivity;

/**
 * Created by neuro on 01-06-2016.
 */
// FIXME: this call could leak the calling fragment
public class SearchUtils {

  private static String SEARCH_WEBSOCKET = "9000";

  public static void setupGlobalSearchView(Menu menu, Context context,
      FragmentNavigator fragmentNavigator) {
    setupSearchView(menu.findItem(R.id.action_search), context, fragmentNavigator,
        s -> V8Engine.getFragmentProvider()
            .newSearchFragment(s));
  }

  public static void setupInsideStoreSearchView(Menu menu, Context context,
      FragmentNavigator fragmentNavigator, String storeName) {
    setupSearchView(menu.findItem(R.id.action_search), context, fragmentNavigator,
        s -> V8Engine.getFragmentProvider()
            .newSearchFragment(s, storeName));
  }

  private static void setupSearchView(MenuItem searchItem, Context context,
      FragmentNavigator fragmentNavigator,
      CreateQueryFragmentInterface createSearchFragmentInterface) {

    if (searchItem == null) {
      CrashReport.getInstance()
          .log(new NullPointerException("MenuItem to create search is null"));
      return;
    }

    if (context == null) {
      CrashReport.getInstance()
          .log(new NullPointerException("Context to create search is null"));
      return;
    }

    if (fragmentNavigator == null) {
      CrashReport.getInstance()
          .log(new NullPointerException("FragmentNavigator to create search is null"));
      return;
    }

    // Get the SearchView and set the searchable configuration
    final SearchManager searchManager =
        (SearchManager) context.getSystemService(Context.SEARCH_SERVICE);

    if (searchManager == null) {
      CrashReport.getInstance()
          .log(new NullPointerException("SearchManager service to create search is null"));
      return;
    }

    final SearchView searchView = (SearchView) searchItem.getActionView();
    ComponentName cn = new ComponentName(context.getApplicationContext(), SearchActivity.class);
    searchView.setSearchableInfo(searchManager.getSearchableInfo(cn));
    SearchAppsWebSocket searchAppsWebSocket = new SearchAppsWebSocket();

    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override public boolean onQueryTextSubmit(String s) {
        MenuItemCompat.collapseActionView(searchItem);

        boolean validQueryLength = s.length() > 1;

        if (validQueryLength) {
          fragmentNavigator.navigateTo(createSearchFragmentInterface.create(s));
        } else {
          ShowMessage.asToast(context.getApplicationContext(), R.string.search_minimum_chars);
        }

        return true;
      }

      @Override public boolean onQueryTextChange(String s) {
        return false;
      }
    });

    searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
      @Override public boolean onSuggestionSelect(int position) {
        return false;
      }

      @Override public boolean onSuggestionClick(int position) {
        Cursor item = (Cursor) searchView.getSuggestionsAdapter()
            .getItem(position);

        fragmentNavigator.navigateTo(createSearchFragmentInterface.create(item.getString(1)));

        return true;
      }
    });

    searchView.setOnQueryTextFocusChangeListener((v, hasFocus) -> {
      if (!hasFocus) {
        MenuItemCompat.collapseActionView(searchItem);
        searchAppsWebSocket.disconnect();
      }
    });
    searchView.setOnSearchClickListener(v -> searchAppsWebSocket.connect(SEARCH_WEBSOCKET));
  }
}
