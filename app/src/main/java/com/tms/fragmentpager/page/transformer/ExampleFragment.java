package com.tms.fragmentpager.page.transformer;


import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tms.fragmentpager.R;


/**
 * Created by TsungMu on 2018/04/23. A fragment to show an example page.
 */
public class ExampleFragment extends Fragment implements TransformerFragment.PageActionListener {
    private static final String ARG_BACKGROUND_COLOR = "arg_background_color";
    private static final String ARG_CONTENT_TEXT = "arg_content_text";
    private final String TAG = getClass().getSimpleName() + "@" + Integer.toHexString(hashCode());
    private int mBackgroundColor;
    private String mContentText;

    private View transformerExampleRoot;
    private TextView transformerExampleText;
    private View transformerExampleNextButton;
    private ActionListener mActionListener;
    private ClickListener mClickListener;

    public ExampleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of this fragment using the provided parameters.
     *
     * @param colorBackground background color of fragment.
     * @param textContent content description of fragment.
     * @return A new instance of fragment ExampleFragment.
     */
    protected static ExampleFragment newInstance(@ColorInt int colorBackground, String textContent) {
        ExampleFragment fragment = new ExampleFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_BACKGROUND_COLOR, colorBackground);
        args.putString(ARG_CONTENT_TEXT, textContent);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Implement and register this interface (using {@link #setActionListener(ActionListener)} to catch the feedback in UI thread.
     */
    public interface ActionListener {
        void onClickNext();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mBackgroundColor = getArguments().getInt(ARG_BACKGROUND_COLOR, 0xFFFFFF);
            mContentText = getArguments().getString(ARG_CONTENT_TEXT, "Null");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_transformer_example, container, false);
        initViewIDs(rootView);
        initViews();
        return rootView;
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
    }

    @Override
    public void onPageSelected() {
    }

    public void setActionListener(ActionListener listener) {
        mActionListener = listener;
    }

    private void initViewIDs(@NonNull View rootView) {
        transformerExampleRoot = rootView.findViewById(R.id.transformerExampleRoot);
        transformerExampleText = (TextView) rootView.findViewById(R.id.transformerExampleText);
        transformerExampleNextButton = rootView.findViewById(R.id.transformerExampleNextButton);
    }

    private void initViews() {
        mClickListener = new ClickListener();
        transformerExampleRoot.setBackgroundColor(mBackgroundColor);
        transformerExampleText.setText(mContentText);
        transformerExampleNextButton.setOnClickListener(mClickListener);
    }

    private class ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (mActionListener != null && view.getId() == R.id.transformerExampleNextButton) {
                mActionListener.onClickNext();
            }
        }
    }
}
