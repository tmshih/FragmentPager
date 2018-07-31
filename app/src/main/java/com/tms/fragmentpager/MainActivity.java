package com.tms.fragmentpager;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.tms.fragmentpager.page.home.HomeFragment;
import com.tms.fragmentpager.page.init.InitializationFragment;
import com.tms.fragmentpager.page.transformer.TransformerFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private final String TAG = getClass().getSimpleName() + "@" + Integer.toHexString(hashCode());

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide fragments for each of the sections.
     * We use a {@link FragmentPagerAdapter} derivative, which will keep every loaded fragment in memory.
     * If this becomes too memory intensive, it may be best to switch to a {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private PageActionListener mPageActionListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* Create the adapter that will return a fragment for each primary sections of the activity. */
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(mSectionsPagerAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.setFitsSystemWindows(false);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        hideSystemUI();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (hasFocus) {
            hideSystemUI();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            return;
        }

        if (mPageActionListener != null) {
            /* Fragment consumed onBackPressed(), it can be caused by wizard page changing, etc. */
            if (mPageActionListener.onBackPressed()) {return;}
        }

        super.onBackPressed();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (mPageActionListener != null) {
            /* Fragment consumed dispatchKeyEvent(KeyEvent). */
            if (mPageActionListener.dispatchKeyEvent(event)) {
                return true;
            }
        }

        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Hiding the SystemUI (status & navigation bar).
     */
    private void hideSystemUI() {
        Log.d(TAG, "hideSystemUI():");
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    private void setPageActionListener(PageActionListener listener) {
        mPageActionListener = listener;
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {
        public static final int PAGE_HOME = 0;
        public static final int PAGE_INITIALIZATION = 1;
        public static final int PAGE_TRANSFORMER = 2;
        private static final int INDEX_PAGE_NUMBER = 0;
        private static final int INDEX_PAGE_TITLE = 1;
        private static final int INDEX_PAGE_FRAGMENT = 2;
        private final Object[][] mPages = new Object[][] {
                {PAGE_HOME, "PAGE_HOME", null},
                {PAGE_INITIALIZATION, "PAGE_INITIALIZATION", null},
                {PAGE_TRANSFORMER, "PAGE_TRANSFORMER", null}
        };
        private int lastPosition = PAGE_HOME;

        private SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
            Log.w(TAG, "destroyItem(): " + mPages[position][INDEX_PAGE_TITLE] + " @ " + mPages[position][INDEX_PAGE_FRAGMENT]);
            mPages[position][INDEX_PAGE_FRAGMENT] = null;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;

            switch (position) {
                case PAGE_HOME: {
                    HomeFragment homeFragment = HomeFragment.newInstance(3000L);
                    homeFragment.setActionListener(new HomeActionListener());
                    fragment = homeFragment;
                } break;

                case PAGE_INITIALIZATION: {
                    InitializationFragment initFragment = InitializationFragment.newInstance();
                    initFragment.setActionListener(new InitActionListener());
                    fragment = initFragment;
                } break;

                case PAGE_TRANSFORMER: {
                    TransformerFragment transformerFragment = TransformerFragment.newInstance();
                    transformerFragment.setActionListener(new TransformerActionListener());
                    fragment = transformerFragment;
                } break;

                default: {
                    Log.e(TAG, "Undefine page in SectionsPagerAdapter!");
                } break;
            }
            mPages[position][INDEX_PAGE_FRAGMENT] = fragment;

            return fragment;
        }

        @Override
        public int getCount() {
            return mPages.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mPages[position][INDEX_PAGE_TITLE] + " " + position;
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            Log.d(TAG, "onPageSelected(): " + mPages[lastPosition][INDEX_PAGE_TITLE] + " >> " + mPages[position][INDEX_PAGE_TITLE]);

            if (mPageActionListener != null) {
                mPageActionListener.onPageLeft();
            }

            setPageActionListener((mPages[position][INDEX_PAGE_FRAGMENT] instanceof PageActionListener) ?
                    (PageActionListener) mPages[position][INDEX_PAGE_FRAGMENT] : null);

            if (mPageActionListener != null) {
                mPageActionListener.onPageSelected();
            }

            lastPosition = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }

        public void setupTransformerToExample() {
            if (mPages[PAGE_TRANSFORMER][INDEX_PAGE_FRAGMENT] instanceof TransformerFragment) {
                TransformerFragment transformer = (TransformerFragment) mPages[PAGE_TRANSFORMER][INDEX_PAGE_FRAGMENT];
                transformer.transform(TransformerFragment.TYPE.EXAMPLE, false);
            }
        }

        public void setupTransformerToConfig() {
            if (mPages[PAGE_TRANSFORMER][INDEX_PAGE_FRAGMENT] instanceof TransformerFragment) {
                TransformerFragment transformer = (TransformerFragment) mPages[PAGE_TRANSFORMER][INDEX_PAGE_FRAGMENT];
                transformer.transform(TransformerFragment.TYPE.CONFIG, false);
            }
        }

        public void setupTransformerToRoot() {
            if (mPages[PAGE_TRANSFORMER][INDEX_PAGE_FRAGMENT] instanceof TransformerFragment) {
                TransformerFragment transformer = (TransformerFragment) mPages[PAGE_TRANSFORMER][INDEX_PAGE_FRAGMENT];
                transformer.transform(TransformerFragment.TYPE.ROOT, false);
            }
        }
    }

    private class HomeActionListener implements HomeFragment.ActionListener {
        @Override
        public void onShowEnd() {
            mViewPager.setCurrentItem(SectionsPagerAdapter.PAGE_INITIALIZATION);
        }
    }

    private class InitActionListener implements InitializationFragment.ActionListener {
        @Override
        public void onInitFail() {
            mSectionsPagerAdapter.setupTransformerToConfig();
            mViewPager.setCurrentItem(SectionsPagerAdapter.PAGE_TRANSFORMER);
        }

        @Override
        public void onInitReady() {
            mSectionsPagerAdapter.setupTransformerToRoot();
            mViewPager.setCurrentItem(SectionsPagerAdapter.PAGE_TRANSFORMER);
        }
    }

    private class TransformerActionListener implements TransformerFragment.ActionListener {
        @Override
        public void onHideSystemUI() {
            hideSystemUI();
        }
    }

    /**
     * Interface definition for a callback to be invoked when active specified page, e.g. hard key is clicked (android back key).
     */
    public interface PageActionListener {
        /**
         * Called when {@link MainActivity#dispatchKeyEvent(KeyEvent)}, return true if consumed, else false.
         */
        boolean dispatchKeyEvent(KeyEvent event);

        /**
         * Called when {@link MainActivity#onBackPressed()}, return true if consumed, else false.
         */
        boolean onBackPressed();

        /**
         * Called when page left ({@link MainActivity.SectionsPagerAdapter#onPageSelected(int)}).
         */
        void onPageLeft();

        /**
         * Called when page selected ({@link MainActivity.SectionsPagerAdapter#onPageSelected(int)}).
         */
        void onPageSelected();
    }
}
