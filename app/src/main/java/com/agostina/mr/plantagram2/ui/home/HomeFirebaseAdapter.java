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
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class HomeFirebaseAdapter extends FirebaseRecyclerAdapter<Plant, HomeFirebaseAdapter.viewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public HomeFirebaseAdapter(@NonNull FirebaseRecyclerOptions<Plant> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull viewHolder holder, int position, @NonNull Plant model) {
        holder.name.setText(model.getSuggestions().get(0).getPlant_name());
        System.out.println(">>>>+++++"+model.getSuggestions().get(0).getPlant_name());
        holder.description.setText(model.getSuggestions().get(0).getPlant_details().getWiki_description().getValue());
        Glide.with(holder.context).load(model.getImages().get(0).getUrl()).into(holder.picture);


    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.plant_list_item, parent, false);
        return new HomeFirebaseAdapter.viewHolder(view);
    }

    class viewHolder extends RecyclerView.ViewHolder{
        private final TextView name;
        private final TextView description;
        private final ImageView picture;
        private final Context context;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            name = itemView.findViewById(R.id.name);
            picture = itemView.findViewById(R.id.image);
            description = itemView.findViewById(R.id.description);
        }
    }

}
