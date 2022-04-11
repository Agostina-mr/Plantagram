package com.agostina.mr.plantagram2.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.agostina.mr.plantagram2.databinding.FragmentCreateRequestBinding;

public class CreatePostFragment extends Fragment {
    private FragmentCreateRequestBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCreateRequestBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }
}
