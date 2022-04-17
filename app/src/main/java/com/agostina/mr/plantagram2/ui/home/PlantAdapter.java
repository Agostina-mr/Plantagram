package com.agostina.mr.plantagram2.ui.home;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agostina.mr.plantagram2.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.ViewHolder> {
    private ArrayList<PlantPost> plants;
    private OnClickListener onClickListener;

    public PlantAdapter(ArrayList<PlantPost> plants) {
        this.plants = plants;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.plant_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(plants.get(position).getUser().getUserName());
        //holder.description.setText(plants.get(position).getPlant().getSugestions().getPlant_name());
        //holder.picture.setImageResource(plants.get(position).getPlant().);
        holder.userImage.setImageResource(plants.get(position).getUser().getImageId());
    }

    @Override
    public int getItemCount() {
        return plants.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView location;
        private final TextView description;
        private final ImageView picture;
        private final CircleImageView userImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            picture = itemView.findViewById(R.id.image);
            description= itemView.findViewById(R.id.description);
            location= itemView.findViewById(R.id.location);
            userImage = itemView.findViewById(R.id.user_image);

        }
    }

    interface OnClickListener{
        void onClick();
    }
}
