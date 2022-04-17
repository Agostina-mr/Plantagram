package com.agostina.mr.plantagram2.ui.request;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.agostina.mr.plantagram2.R;
import com.agostina.mr.plantagram2.databinding.FragmentCreateRequestBinding;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

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
            Navigation.findNavController(root).navigate(R.id.camera);
        });

        approveRequest.setOnClickListener(v->{
            ///create the parts for post request
            viewModel.plantIdentification(path);
            });
        //viewModel.getIdentifiedPlant().observe(getViewLifecycleOwner(), plant -> { plantName.setText(plant.getSuggestions().get(0).getPlant_name());
        // });

        return root;
    }
}
