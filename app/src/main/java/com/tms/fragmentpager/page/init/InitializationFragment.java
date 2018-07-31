package com.tms.fragmentpager.page.init;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tms.fragmentpager.MainActivity;
import com.tms.fragmentpager.R;


/**
 * Created by TsungMu on 2018/04/19. A fragment to show initialization page.
 */
public class InitializationFragment extends Fragment implements MainActivity.PageActionListener {
    private final String TAG = getClass().getSimpleName() + "@" + Integer.toHexString(hashCode());
    private ActionListener mListener;
    private final Handler mHandler = new Handler();
    private final EscapeRunnable mEscapeRunnable = new EscapeRunnable();

    public InitializationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of this fragment.
     *
     * @return A new instance of fragment InitializationFragment.
     */
    public static InitializationFragment newInstance() {
        InitializationFragment fragment = new InitializationFragment();
        Bundle args = new Bundle();
        // TODO: Init the setup here.
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Implement and register this interface (using {@link #setActionListener(ActionListener)} to catch the feedback in UI thread.
     */
    public interface ActionListener {
        void onInitFail();
        void onInitReady();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            // TODO: Load the setup here.
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_initialization, container, false);
        initViewIDs(rootView);
        initViews();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (!mEscapeRunnable.isFinish()) {
            mHandler.removeCallbacks(mEscapeRunnable);
            mHandler.postDelayed(mEscapeRunnable, 3000L);
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        mHandler.removeCallbacks(mEscapeRunnable);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return false;
    }

    @Override
    public boolean onBackPressed() {
        return true;
    }

    @Override
    public void onPageLeft() {
        mEscapeRunnable.setFinish(true);
        mHandler.removeCallbacks(mEscapeRunnable);
    }

    @Override
    public void onPageSelected() {
        mEscapeRunnable.setFinish(false);
        mHandler.removeCallbacks(mEscapeRunnable);
        mHandler.postDelayed(mEscapeRunnable, 3000L);
    }

    public void setActionListener(ActionListener listener) {
        mListener = listener;
    }

    private void initViewIDs(@NonNull View rootView) {
    }

    private void initViews() {
    }

    private class EscapeRunnable implements Runnable {
        private boolean bFinish = true;

        @Override
        public void run() {
            bFinish = true;
            if (mListener != null && false) {
                mListener.onInitFail();
            } else if (mListener != null) {
                mListener.onInitReady();
            }
        }

        public void setFinish(boolean flag) {
            bFinish = flag;
        }

        public boolean isFinish() {
            return bFinish;
        }
    }
}
