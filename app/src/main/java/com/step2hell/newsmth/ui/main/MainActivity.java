package com.step2hell.newsmth.ui.main;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.step2hell.newsmth.R;
import com.step2hell.newsmth.ui.BaseActivity;

/**
 * Todo: MVVM, Design main page.
 */
public class MainActivity extends BaseActivity {

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        initDrawerNavigation();
    }

    private void initDrawerNavigation() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
//        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, Gravity.START);
//        mDrawerLayout.setScrimColor(Color.TRANSPARENT);

        mDrawerToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.main_navigation);
        mNavigationView.setNavigationItemSelectedListener(new NavigationItemSelectedListener());
    }

    /* The click listner for item of NavigationView */
    private class NavigationItemSelectedListener implements NavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            selectItem(item.getItemId());
            return true;
        }

        private void selectItem(int id) {
            String title;
            switch (id) {
                case R.id.navigation_preference:
                    title = getString(R.string.navigation_preference);
                    break;
                case R.id.navigation_menu1:
                    title = getString(R.string.navigation_menu1);
                    break;
                case R.id.navigation_menu2:
                    title = getString(R.string.navigation_menu2);
                    break;
                case R.id.navigation_menu3:
                    title = getString(R.string.navigation_menu3);
                    break;
                case R.id.navigation_menu4:
                    title = getString(R.string.navigation_menu4);
                    break;
                case R.id.navigation_about:
                    title = getString(R.string.navigation_about);
                    break;
                default:
                    title = null;
                    break;
            }
            Log.e("Bob", "title:" + title);
            mDrawerLayout.closeDrawers();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(mNavigationView)) {
            mDrawerLayout.closeDrawer(mNavigationView);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {
            boolean isExit = intent.getBooleanExtra(TAG_EXIT, false);
            if (isExit) {
                firstTapTime = 0;
                this.finish();
            }
        }
    }

}
