package com.agostina.mr.plantagram2.ui.takepicture;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.agostina.mr.plantagram2.R;
import com.agostina.mr.plantagram2.databinding.FragmentTakePictureBinding;

public class TakePicture extends Fragment {
    private FragmentTakePictureBinding binding;
    public TakePicture() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTakePictureBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //implementation

        return root;
    }



}