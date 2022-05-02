package com.agostina.mr.plantagram2.ui.home;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agostina.mr.plantagram2.R;
import com.agostina.mr.plantagram2.model.plants.Plant;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.ViewHolder> {
    private List<Plant> plants;
    private OnClickListener onClickListener;

    public PlantAdapter(List<Plant> plants) {
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
        holder.name.setText(plants.get(position).getSuggestions().get(0).getPlant_name());
        holder.description.setText(plants.get(position).getSuggestions().get(0).getPlant_details().getWiki_description().getValue());
        Glide.with(holder.context).load(plants.get(position).getImages().get(0).getUrl()).into(holder.picture);


    }

    @Override
    public int getItemCount() {
        if (plants != null) {
            return plants.size();
        }
        return 0;
    }

    public void updatePlantList(final List<Plant> plants) {
        this.plants.clear();
        this.plants = plants;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
       // private final TextView location;
        private final TextView description;
        private final ImageView picture;
        private final Context context;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            name = itemView.findViewById(R.id.name);
            picture = itemView.findViewById(R.id.image);
            description = itemView.findViewById(R.id.description);
           // location = itemView.findViewById(R.id.location);
        }
    }

    interface OnClickListener{
        void onClick();
    }
}
