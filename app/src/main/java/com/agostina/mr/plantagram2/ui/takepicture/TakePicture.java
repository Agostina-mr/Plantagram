package com.agostina.mr.plantagram2.ui.takepicture;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.agostina.mr.plantagram2.R;
import com.agostina.mr.plantagram2.databinding.FragmentTakePictureBinding;

public class TakePicture extends Fragment {
    private FragmentTakePictureBinding binding;


    public TakePicture() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        System.out.println("---------" + 1);
        binding = FragmentTakePictureBinding.inflate(inflater, container, false);
        System.out.println("---------" + 2);
        View root = binding.getRoot();
        System.out.println("---------" + 3);
        //implementation
        return root;
    }
}