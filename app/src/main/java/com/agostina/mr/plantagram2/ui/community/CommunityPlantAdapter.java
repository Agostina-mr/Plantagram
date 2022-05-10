package com.agostina.mr.plantagram2.ui.community;


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

public class CommunityPlantAdapter extends FirebaseRecyclerAdapter<PlantPost, CommunityPlantAdapter.ViewHolder> {

    private OnClickListener onClickListener;

    public CommunityPlantAdapter(@NonNull FirebaseRecyclerOptions<PlantPost> options) {
        super(options);
    }
    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull PlantPost model) {
        holder.name.setText(model.getUserName());
        holder.description.setText(model.getPlantDescription());
        Glide.with(holder.context).load(model.getUserPicture()).into(holder.userImage);
        Glide.with(holder.context).load(model.getPicture()).override(600,600).into(holder.picture);
        holder.authorsComment.setText(model.getAuthorComment());
        holder.plantName.setText(model.getPlantName());
        holder.likesCount.setText(String.valueOf(model.getLikes().size()));

        if (model.doesUserLikeIt(model.getViewBy())){
            holder.likesImage.setImageResource(R.drawable.heart);
        }
        else {
            holder.likesImage.setImageResource(R.drawable.heart_empty);
        }


    }

    public void setOnClickListener(OnClickListener listener) {
        this.onClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.plant_list_item, parent, false);
        return new ViewHolder(view);
    }

     class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView description;
        private final ImageView picture;
        private final Context context;
        private final ImageView userImage;
        private final TextView plantName;
        private final TextView authorsComment;
        private final ImageView likesImage;
        private final TextView likesCount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            name = itemView.findViewById(R.id.name);
            picture = itemView.findViewById(R.id.image);
            plantName = itemView.findViewById(R.id.plant_name_post);
            description = itemView.findViewById(R.id.description);
            userImage = itemView.findViewById(R.id.user_image);
            authorsComment = itemView.findViewById(R.id.authors_comment);
            likesCount = itemView.findViewById(R.id.idTVLikes);
            likesImage = itemView.findViewById(R.id.likes_heart);

            likesImage.setOnClickListener(v->{
                onClickListener.onClick(getItem(getBindingAdapterPosition()));
            });

        }
    }
    public interface OnClickListener{
        void onClick(PlantPost plantPost);

    }

}
