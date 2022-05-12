package com.agostina.mr.plantagram2.ui.community;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.agostina.mr.plantagram2.R;
import com.agostina.mr.plantagram2.databinding.FragmentSinglePostBinding;
import com.agostina.mr.plantagram2.model.post.PlantPost;
import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;

public class SinglePostFragment extends Fragment {
    private FragmentSinglePostBinding binding;
    private CommunityViewModel viewModel;
    private RecyclerView commentsRecyclerView;
    private PlantPost plantPost;

    private  TextView name;
    private  ImageView picture;
    private  ImageView userImage;
    private  TextView commentsCount;
    private TextInputEditText inputLayout;
    private ImageButton sendCommentButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSinglePostBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        viewModel = new ViewModelProvider(this).get(CommunityViewModel.class);


        name = root.findViewById(R.id.name1);
        picture = root.findViewById(R.id.image1);
        userImage = root.findViewById(R.id.user_image1);
        commentsCount = root.findViewById(R.id.comments_count1);
        inputLayout = root.findViewById(R.id.comment_users_input);
        sendCommentButton = root.findViewById(R.id.sent_comment_button);



       viewModel.getSinglePlantPost().observe(getViewLifecycleOwner(), plantPost1 -> {
           this.plantPost = plantPost1;
           name.setText(plantPost.getUserName());
           Glide.with(this).load(plantPost.getPicture()).override(600, 600).into(picture);
           Glide.with(this).load(plantPost.getUserPicture()).into(userImage);
           commentsCount.setText(R.string.comments);

           sendCommentButton.setOnClickListener(view -> {
               String comment = inputLayout.getText().toString();
               addComment(plantPost1, comment);
           });
       });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void addComment(PlantPost plantPost, String comment)
    {
        viewModel.addComment(plantPost, comment);
    }
}