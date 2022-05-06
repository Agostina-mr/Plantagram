package com.agostina.mr.plantagram2.ui.saveplants;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agostina.mr.plantagram2.R;
import com.agostina.mr.plantagram2.model.plants.Images;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class SavePlantImagesAdapter extends RecyclerView.Adapter<SavePlantImagesAdapter.ViewHolder> {


    private List<Images> imagesArrayList = new ArrayList<>();

    public SavePlantImagesAdapter(List<Images> plantImages) {
        this.imagesArrayList = plantImages;

    }

    @NonNull
    @Override
    public SavePlantImagesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_plant_image, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SavePlantImagesAdapter.ViewHolder holder, int position) {
     Glide.with(holder.context).load( imagesArrayList.get(position).getUrl()).override(800, 800).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if (imagesArrayList !=null){
            return imagesArrayList.size();
        }
        return 0;
    }


    public void updatePlantImages(List<Images> plantImages) {
        this.imagesArrayList.clear();
        this.imagesArrayList = plantImages;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private Context context;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            imageView = itemView.findViewById(R.id.plant_image_);
        }
    }
}
