package com.agostina.mr.plantagram2.ui.request;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.FileUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.agostina.mr.plantagram2.MainActivity;
import com.agostina.mr.plantagram2.R;
import com.agostina.mr.plantagram2.databinding.FragmentCreateRequestBinding;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class CreateRequestFragment extends Fragment {
    private FragmentCreateRequestBinding binding;
    private CreateRequestViewModel viewModel;
    private String path;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCreateRequestBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        ImageView imageView = root.findViewById(R.id.new_image);
        Button cancelRequest = root.findViewById(R.id.cancel_b);
        Button approveRequest = root.findViewById(R.id.approve_b);
        viewModel = new ViewModelProvider(this).get(CreateRequestViewModel.class);
        path = CreateRequestFragmentArgs.fromBundle(getArguments()).getPath();
        Glide.with(root.getContext()).load(path).apply(new RequestOptions().override(1200, 2000)).into(imageView);

        cancelRequest.setOnClickListener(v-> {
            Navigation.findNavController(root).navigate(R.id.test);
        });

        approveRequest.setOnClickListener(v->{
            ///create the parts for post request

            viewModel.plantIdentification(path);
            TextView plantName = root.findViewById(R.id.name);
            viewModel.getIdentifiedPlant().observe((LifecycleOwner) this.requireContext(), plant -> {
               // plantName.setText(plant.getSugestions().getPlant_name());
            });

        });
        //getting the picture path from previous fragment and displaying


        return root;
    }
}
