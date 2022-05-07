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
import com.agostina.mr.plantagram2.model.plants.PlantPost;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class PlantAdapter extends FirebaseRecyclerAdapter<PlantPost, PlantAdapter.ViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public PlantAdapter(@NonNull FirebaseRecyclerOptions<PlantPost> options) {
        super(options);
    }
    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull PlantPost model) {
        holder.name.setText(model.getUserName());
        holder.description.setText(model.getPlantName());

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

}
