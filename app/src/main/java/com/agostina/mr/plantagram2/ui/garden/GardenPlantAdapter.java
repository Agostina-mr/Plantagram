package com.agostina.mr.plantagram2.ui.garden;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agostina.mr.plantagram2.R;
import com.agostina.mr.plantagram2.model.plants.PlantPost;
import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class GardenPlantAdapter extends FirebaseRecyclerAdapter<PlantPost, GardenPlantAdapter.ViewHolder> {

    public GardenPlantAdapter(@NonNull FirebaseRecyclerOptions<PlantPost> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull GardenPlantAdapter.ViewHolder holder, int position, @NonNull PlantPost model) {
        holder.name.setText(model.getUserName());
        holder.description.setText(model.getPlantDescription());
        Glide.with(holder.context).load(model.getUserPicture()).into(holder.userImage);
        Glide.with(holder.context).load(model.getPicture()).override(600,600).into(holder.picture);
        holder.authorsComment.setText(model.getAuthorComment());
        holder.plantName.setText(model.getPlantName());
    }

    @NonNull
    @Override
    public GardenPlantAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.plant_list_item, parent, false);

        return new GardenPlantAdapter.ViewHolder(view);
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView description;
        private final ImageView picture;
        private final Context context;
        private final ImageView userImage;
        private final TextView plantName;
        private final TextView authorsComment;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            name = itemView.findViewById(R.id.name);
            picture = itemView.findViewById(R.id.image);
            plantName = itemView.findViewById(R.id.plant_name_post);
            description = itemView.findViewById(R.id.description);
            userImage = itemView.findViewById(R.id.user_image);
            authorsComment = itemView.findViewById(R.id.authors_comment);
        }
    }
}
