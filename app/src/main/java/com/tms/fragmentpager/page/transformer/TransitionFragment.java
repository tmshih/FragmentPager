package com.tms.fragmentpager.page.transformer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tms.fragmentpager.R;


/**
 * Created by TsungMu on 2018/04/23. A fragment to show a transition page.
 */
public class TransitionFragment extends Fragment {

    public TransitionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of this fragment using the provided parameters.
     *
     * @return A new instance of fragment TransformerTransitionFragment.
     */
    protected static TransitionFragment newInstance() {
        TransitionFragment fragment = new TransitionFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_transformer_transition, container, false);
        return rootView;
    }

}
