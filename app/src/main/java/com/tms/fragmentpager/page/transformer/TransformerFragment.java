package com.tms.fragmentpager.page.transformer;


import android.os.Bundle;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tms.fragmentpager.MainActivity;
import com.tms.fragmentpager.R;


/**
 * Created by TsungMu on 2018/04/19. A fragment to show each function page.
 */
public class TransformerFragment extends Fragment implements MainActivity.PageActionListener {
    public enum TYPE {
        NONE, EXAMPLE, CONFIG, ROOT,
    }
    private final String TAG = getClass().getSimpleName() + "@" + Integer.toHexString(hashCode());
    private TYPE mType = TYPE.NONE;
    private TBundle mTBundle = null;
    private ActionListener mListener;
    private boolean mTransformerSelected = false;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private SectionsPagerAdapter mPagerAdapter;
    private PageActionListener mPageActionListener;

    public TransformerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of this fragment.
     *
     * @return A new instance of fragment TransformerFragment.
     */
    public static TransformerFragment newInstance() {
        TransformerFragment fragment = new TransformerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Implement and register this interface (using {@link #setActionListener(ActionListener)} to catch the feedback in UI thread.
     */
    public interface ActionListener {
        void onHideSystemUI();
    }

    /**
     * Interface definition for a callback to be invoked when active specified page, e.g. hard key is clicked (android back key).
     */
    public interface PageActionListener {
        /**
         * Called when {@link TransformerFragment#dispatchKeyEvent(KeyEvent)}, return true if consumed, else false.
         */
        boolean dispatchKeyEvent(KeyEvent event);

        /**
         * Called when {@link TransformerFragment#onBackPressed()}, return true if consumed, else false.
         */
        boolean onBackPressed();

        /**
         * Called when page left ({@link TransformerFragment.SectionsPagerAdapter#onPageSelected(int)}).
         */
        void onPageLeft();

        /**
         * Called when page selected ({@link TransformerFragment.SectionsPagerAdapter#onPageSelected(int)}).
         */
        void onPageSelected();
    }

    public class TBundle {
        private TBundle() {}
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_transformer, container, false);
        initViewIDs(rootView);
        initViews();
        return rootView;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (mPageActionListener != null && mPageActionListener.dispatchKeyEvent(event)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean onBackPressed() {
        if (mPageActionListener != null && mPageActionListener.onBackPressed()) {
            return true;
        }
        return false;
    }

    @Override
    public void onPageLeft() {
        mTransformerSelected = false;
    }

    @Override
    public void onPageSelected() {
        mTransformerSelected = true;
    }

    public void setActionListener(ActionListener listener) {
        mListener = listener;
    }

    public void transform(@NonNull TYPE type, boolean animation) {
        transform(type, null, animation);
    }

    public void transform(@NonNull TYPE type, TBundle tBundle, boolean animation) {
        Log.d(TAG, "transform(): type=" + type + ", " + tBundle + ", animation=" + animation);

        mType = type;
        mTBundle = tBundle;
        int pageIndex =
                (mType == TYPE.EXAMPLE) ? SectionsPagerAdapter.PAGE_EXAMPLE_01 :
                SectionsPagerAdapter.PAGE_NONE;

        mViewPager.setCurrentItem(pageIndex, animation);
    }

    private void transform(@IntRange(from = 0) int pageIndex, boolean animation) {
        transform(pageIndex, null, animation);
    }

    private void transform(@IntRange(from = 0) int pageIndex, @NonNull TBundle tBundle, boolean animation) {
        Log.d(TAG, "transform(): page=" + pageIndex + ", " + tBundle + ", animation=" + animation);

        mTBundle = tBundle;
        mViewPager.setCurrentItem(pageIndex, animation);
    }

    private void initViewIDs(@NonNull View rootView) {
        mViewPager = (ViewPager) rootView.findViewById(R.id.transformerContainer);
    }

    private void initViews() {
        /* Create the adapter that will return a fragment for each fragment page. */
        FragmentManager fragmentManager = getChildFragmentManager();
        mPagerAdapter = new SectionsPagerAdapter(fragmentManager);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(mPagerAdapter);
    }

    private void setPageActionListener(PageActionListener listener) {
        mPageActionListener = listener;
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the sections/tabs/pages.
     */
    private class SectionsPagerAdapter extends FragmentStatePagerAdapter implements ViewPager.OnPageChangeListener {
        public static final int PAGE_TRANSITION = 0;
        public static final int PAGE_NONE = 0;
        public static final int PAGE_EXAMPLE_01 = 2;
        public static final int PAGE_EXAMPLE_02 = 4;
        public static final int PAGE_EXAMPLE_03 = 6;
        public static final int PAGE_CONFIG = 8;
        public static final int PAGE_ROOT = 10;
        private final int[] mPages = new int[] {
                PAGE_NONE,
                PAGE_TRANSITION, PAGE_EXAMPLE_01,
                PAGE_TRANSITION, PAGE_EXAMPLE_02,
                PAGE_TRANSITION, PAGE_EXAMPLE_03,
                PAGE_TRANSITION, PAGE_CONFIG,
                PAGE_TRANSITION, PAGE_ROOT,
        };
        private final String[] mTitles = new String[] {
                "PAGE_NONE",
                "", "PAGE_EXAMPLE_01",
                "", "PAGE_EXAMPLE_02",
                "", "PAGE_EXAMPLE_03",
                "", "PAGE_CONFIG",
                "", "PAGE_ROOT",
        };
        private int lastPosition = PAGE_NONE;
        private Fragment mSelectedFragment = null;

        private SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            Log.w(TAG, "destroyItem(): " + position + " @ " + object);
            super.destroyItem(container, position, object);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case PAGE_EXAMPLE_01: {
                    ExampleFragment exampleFragment = ExampleFragment.newInstance(0xA0FF0000, "Red Fragment");
                    exampleFragment.setActionListener(new ExampleActionListener(PAGE_EXAMPLE_02));
                    fragment = mSelectedFragment = exampleFragment;
                } break;

                case PAGE_EXAMPLE_02: {
                    ExampleFragment exampleFragment = ExampleFragment.newInstance(0xA000FF00, "Green Fragment");
                    exampleFragment.setActionListener(new ExampleActionListener(PAGE_EXAMPLE_03));
                    fragment = mSelectedFragment = exampleFragment;
                } break;

                case PAGE_EXAMPLE_03: {
                    ExampleFragment exampleFragment = ExampleFragment.newInstance(0xA00000FF, "Blue Fragment");
                    exampleFragment.setActionListener(new ExampleActionListener(PAGE_EXAMPLE_01));
                    fragment = mSelectedFragment = exampleFragment;
                } break;

                default: {
                    fragment = TransitionFragment.newInstance();
                } break;
            }

            Log.d(TAG, "getItem(" + position + "): " + fragment);
            return fragment;
        }

        @Override
        public int getCount() {
            return mPages.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "[" + position + "]" + mTitles[position];
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            Log.d(TAG, "onPageSelected(): " + mTitles[lastPosition] + " >> " + mTitles[position]);

            if (mPageActionListener != null) {
                mPageActionListener.onPageLeft();
            }

            if (position == PAGE_NONE) {
                mSelectedFragment = null;
            }
            setPageActionListener(
                    (position == PAGE_NONE) ? null :
                    (mSelectedFragment instanceof PageActionListener) ? (PageActionListener) mSelectedFragment :
                    null);

            if (mPageActionListener != null) {
                mPageActionListener.onPageSelected();
            }

            lastPosition = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }

    private class ExampleActionListener implements ExampleFragment.ActionListener {
        private int mNextPage;

        private ExampleActionListener(int nextPage) {
            this.mNextPage = nextPage;
        }

        @Override
        public void onClickNext() {
            transform(mNextPage, true);
        }
    }
}
