package com.agostina.mr.plantagram2.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.agostina.mr.plantagram2.R;
import com.agostina.mr.plantagram2.databinding.FragmentCreateRequestBinding;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class CreateRequestFragment extends Fragment {
    private FragmentCreateRequestBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCreateRequestBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        ImageView imageView = root.findViewById(R.id.new_image);
        String path = CreateRequestFragmentArgs.fromBundle(getArguments()).getPath();
        Glide.with(root.getContext()).load(path).apply(new RequestOptions().override(1200, 2000)).into(imageView);

        return root;
    }
}
