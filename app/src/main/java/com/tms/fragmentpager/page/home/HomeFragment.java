package com.tms.fragmentpager.page.home;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tms.fragmentpager.R;
import com.tms.fragmentpager.utils.AppSetting;
import com.tms.fragmentpager.view.AtToast;


/**
 * Created by TsungMu on 2018/04/18. A fragment to show home page.
 */
public class HomeFragment extends Fragment {
    private static final String ARG_HOME_DELAY = "arg_home_delay";
    private static final long ARG_HOME_DELAY_2000_MS = 2000l;
    private static final int ENTRY_CLICK_COUNTER = 10;         // default click counter is 10 times.
    private static final long ENTRY_CLICK_TIMEOUT = 10 * 1000; // default click timeout is 10 secs.

    private final String TAG = getClass().getSimpleName() + "@" + Integer.toHexString(hashCode());
    private long mDelay = ARG_HOME_DELAY_2000_MS;
    private ActionListener mListener;
    private final Handler mHandler = new Handler();
    private final Runnable mEscapeRunnable = new EscapeRunnable();
    private final ClickListener mClickListener = new ClickListener();

    private View homeCopyRight;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of this fragment.
     *
     * @param delay for fragment change (ms).
     * @return A new instance of fragment HomeFragment.
     */
    public static HomeFragment newInstance(long delay) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_HOME_DELAY, (delay > 0l) ? delay : ARG_HOME_DELAY_2000_MS);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Implement and register this interface (using {@link #setActionListener(ActionListener)} to catch the feedback in UI thread.
     */
    public interface ActionListener {
        void onShowEnd();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mDelay = getArguments().getLong(ARG_HOME_DELAY, ARG_HOME_DELAY_2000_MS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        initViewIDs(rootView);
        initViews();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mHandler.postDelayed(mEscapeRunnable, mDelay);
    }

    @Override
    public void onStop() {
        super.onStop();
        mHandler.removeCallbacks(mEscapeRunnable);
    }

    public void setActionListener(ActionListener listener) {
        mListener = listener;
    }

    private void initViewIDs(@NonNull View rootView) {
        homeCopyRight = rootView.findViewById(R.id.homeCopyRight);
    }

    private void initViews() {
        homeCopyRight.setOnClickListener(mClickListener);
    }

    private class EscapeRunnable implements Runnable {
        @Override
        public void run() {
            if (mListener != null) {
                mListener.onShowEnd();
            }
        }
    }

    private class EntryCounterRunnable implements Runnable {
        private final int mTarget;
        private int mCounter = 0;

        public EntryCounterRunnable(int target) {
            mTarget = target;
        }

        @Override
        public void run() {
            reset();
        }

        /** Increasing the counter when click, and return the click counter. **/
        public int increase() {
            mCounter++;
            return mCounter;
        }

        /** Checking does click ready or not. **/
        public boolean isReady() {
            return mCounter >= mTarget;
        }

        /** Resetting the counter when timeout. **/
        public void reset() {
            mCounter = 0;
        }
    }

    private class ClickListener implements View.OnClickListener {
        private final EntryCounterRunnable mEntryCounterRunnable = new EntryCounterRunnable(ENTRY_CLICK_COUNTER);

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.homeCopyRight) {
                clickCopyRight();
            }
        }

        private void clickCopyRight() {
            /* Fool-proofing if it's engineering. */
            if (AppSetting.isEngineering()) {
                String message = "You are engineering already!";
                Log.i(TAG, "onClick(): " + message);
                AtToast.makeText(getContext(), message, AtToast.LENGTH_SHORT).show();
                return;
            }

            /* Shift the escape timer, and setup the entry click counter of engineering. */
            mHandler.removeCallbacks(mEscapeRunnable);
            mHandler.postDelayed(mEscapeRunnable, ENTRY_CLICK_TIMEOUT);
            mHandler.removeCallbacks(mEntryCounterRunnable);
            mHandler.postDelayed(mEntryCounterRunnable, ENTRY_CLICK_TIMEOUT);
            mEntryCounterRunnable.increase();
            if (!mEntryCounterRunnable.isReady()) {return;}

            /* Success to enable engineering. */
            mHandler.removeCallbacks(mEscapeRunnable);
            mHandler.removeCallbacks(mEntryCounterRunnable);
            mEntryCounterRunnable.reset();

            AppSetting.setMode(AppSetting.Mode.ENGINEER);
            if (mListener != null) {
                String message = "Enabling engineering!";
                Log.i(TAG, "onClick(): " + message);
                AtToast.makeText(getContext(), message, AtToast.LENGTH_LONG).show();
                mListener.onShowEnd();
            }
        }
    }
}
