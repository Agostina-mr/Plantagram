package com.agostina.mr.plantagram2.ui.request;

import android.os.Bundle;
import android.os.Environment;
import android.os.FileUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.agostina.mr.plantagram2.R;

import com.agostina.mr.plantagram2.databinding.FragmentCameraBinding;
import com.google.common.util.concurrent.ListenableFuture;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

import okhttp3.MediaType;
import okhttp3.RequestBody;


public class CameraFragment extends Fragment
{
    private ImageCapture imageCapture;
    private PreviewView previewView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        com.agostina.mr.plantagram2.databinding.FragmentCameraBinding binding = FragmentCameraBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        previewView = root.findViewById(R.id.preview_view);
        Button buttonTakePicture = root.findViewById(R.id.button_take_picture);

        buttonTakePicture.setOnClickListener(v-> {
            try {
                capturePhoto(root);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });


        ListenableFuture<ProcessCameraProvider> cameraProviderFuture = ProcessCameraProvider.getInstance(this.getContext());
        cameraProviderFuture.addListener(() ->{
            try {
                ProcessCameraProvider cameraProvider = ProcessCameraProvider.getInstance(this.getContext()).get();
                //cameraProviderFuture.get();
                startCameraX(cameraProvider);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }

        }, getExecutor());

        return root;
    }
    private Executor getExecutor() {
        return ContextCompat.getMainExecutor(this.requireContext());
    }

    private void startCameraX(ProcessCameraProvider cameraProvider) {


        cameraProvider.unbindAll();
        CameraSelector cameraSelector =
                new CameraSelector.Builder()
                        .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                        .build();
        //setting the preview
        Preview preview = new Preview.Builder().build();
        // preview.setSurfaceProvider(previewView.createSurfaceProvider(previewView.get);

        preview.setSurfaceProvider(previewView.getSurfaceProvider());
        //setting the image capture
        imageCapture = new ImageCapture.Builder().
                setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY).
                build();

        cameraProvider.bindToLifecycle((LifecycleOwner) this, cameraSelector, preview, imageCapture);

    }
    private void capturePhoto(View view) throws FileNotFoundException {
        File photoDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        if (!photoDir.exists())
            photoDir.mkdir();

        Date date = new Date();
        String timestamp = String.valueOf(date.getTime());
        String photoFilePath = photoDir.getAbsolutePath() + "/" + timestamp + ".jpg";

        FileOutputStream photoFile = new FileOutputStream(photoFilePath);

        imageCapture.takePicture(
                new ImageCapture.OutputFileOptions.Builder(photoFile).build(),
                this.getExecutor(), new ImageCapture.OnImageSavedCallback() {
                    @Override
                    public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {

                        CameraFragmentDirections.ActionTestToCreatePost action = CameraFragmentDirections.actionTestToCreatePost();
                        action.setPath(photoFilePath);


                        Navigation.findNavController(view).navigate(action);
                        Toast.makeText(getContext(), "Photo successfully saved", Toast.LENGTH_SHORT).show();
                        try {
                            photoFile.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(@NonNull ImageCaptureException exception) {
                        Toast.makeText(getContext(), "Not saved", Toast.LENGTH_SHORT).show();
                    }

                }
        );
    }


}
