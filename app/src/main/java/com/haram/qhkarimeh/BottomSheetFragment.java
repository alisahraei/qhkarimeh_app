package com.haram.qhkarimeh;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetFragment extends BottomSheetDialogFragment {
    listenerBottomSheet mListener;

    public static BottomSheetFragment newInstance() {
        BottomSheetFragment fragment = new BottomSheetFragment();
        return fragment;
    }

    public void setListener(listenerBottomSheet listener) {
        mListener = listener;
    }

    public BottomSheetFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_bottom_sheet, container, false);
        view.findViewById(R.id.refreshbox).setOnClickListener(view1 -> mListener.refresh());
        return view;
    }

    public interface listenerBottomSheet{
        void refresh();
    }
}