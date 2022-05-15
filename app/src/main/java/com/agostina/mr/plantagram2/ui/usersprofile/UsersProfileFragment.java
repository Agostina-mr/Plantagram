package com.agostina.mr.plantagram2.ui.usersprofile;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.agostina.mr.plantagram2.R;
import com.agostina.mr.plantagram2.databinding.FragmentUsersProfileBinding;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseUser;

public class UsersProfileFragment extends Fragment {

    private FragmentUsersProfileBinding binding;
    private UsersProfileViewModel viewModel;
    private ImageView userProfileImage;
    private Uri uri = null;
    private FirebaseUser userLiveData;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentUsersProfileBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(UsersProfileViewModel.class);
        View root = binding.getRoot();
        userProfileImage = root.findViewById(R.id.users_profile_picture);
        EditText userName = root.findViewById(R.id.users_profile_name);
        Button changeProfile = root.findViewById(R.id.change_profile);


        viewModel.getCurrentUser().observe(getViewLifecycleOwner(), firebaseUser -> {
            userName.setText(firebaseUser.getDisplayName());
            if (uri==null){
                Glide.with(this).load(firebaseUser.getPhotoUrl())
                        .into(userProfileImage);
            }
            this.userLiveData = firebaseUser;
        });


        changeProfile.setOnClickListener(view -> {
            String newUserName = userName.getText().toString();
            changeProfile(uri, newUserName ) ;

            viewModel.setProfilePictureToFB(uri,  userLiveData.getUid() );

        });


        userProfileImage.setOnClickListener(view -> {
            Intent openGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(openGallery,1000);
        });


        return  root;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000){
            if (resultCode == Activity.RESULT_OK){
                if (data != null) {
                    uri = data.getData();
                }
                Glide.with(this).load(uri)
                        .into(userProfileImage);
            }
        }
    }

    private void changeProfile(Uri uri, String userName)
    {
      viewModel.setProfilePicture(uri,userName);
    }
}