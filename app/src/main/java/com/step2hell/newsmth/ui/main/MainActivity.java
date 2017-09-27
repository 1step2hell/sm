package com.step2hell.newsmth.ui.main;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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
        mNavigationView = (NavigationView) findViewById(R.id.main_navigation);

//        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, Gravity.START);
//        mDrawerLayout.setScrimColor(Color.TRANSPARENT);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.string.drawer_open,           /* "open drawer" description */
                R.string.drawer_close           /* "close drawer" description */) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                ActivityCompat.invalidateOptionsMenu(MainActivity.this);// creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                ActivityCompat.invalidateOptionsMenu(MainActivity.this);// creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
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
                    mDrawerLayout.closeDrawer(mNavigationView);
                    return;
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


    private boolean mIsExit;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mIsExit) {
                finish();
            } else {
                Toast.makeText(this, getString(R.string.toast_exit), Toast.LENGTH_SHORT).show();
                mIsExit = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mIsExit = false;
                    }
                }, 2000);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
