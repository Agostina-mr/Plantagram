package com.agostina.mr.plantagram2.ui.saveplants;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agostina.mr.plantagram2.R;
import com.agostina.mr.plantagram2.model.plants.Images;
import com.agostina.mr.plantagram2.model.plants.Suggestions;

import java.util.ArrayList;

public class SavePlantSuggestionsAdapter extends RecyclerView.Adapter<SavePlantSuggestionsAdapter.ViewHolder>{
    private ArrayList<Suggestions> suggestionsArrayList = new ArrayList<>();

    public SavePlantSuggestionsAdapter(ArrayList<Suggestions> suggestionsArrayList) {
        this.suggestionsArrayList = suggestionsArrayList;
    }

    @NonNull
    @Override
    public SavePlantSuggestionsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_suggestion, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SavePlantSuggestionsAdapter.ViewHolder holder, int position) {
   holder.suggestionName.setText(suggestionsArrayList.get(position).getPlant_name());
   holder.suggestionDescription.setText(suggestionsArrayList.get(position).getPlant_details().getWiki_description().getValue());

    }

    @Override
    public int getItemCount() {
        if (suggestionsArrayList !=null){
            return suggestionsArrayList.size();
        }
        return 0;
    }
    public void updatePlantSuggestions(ArrayList<Suggestions> plantSuggestions) {
        this.suggestionsArrayList.clear();
        this.suggestionsArrayList = plantSuggestions;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private Context context;
        private final TextView suggestionName;
        private final TextView suggestionDescription;
        private TextView userComment;
        private Button saveButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            suggestionName = itemView.findViewById(R.id.plant_name_saving);
            suggestionDescription = itemView.findViewById(R.id.plant_description_saving);
            userComment = itemView.findViewById(R.id.plant_comment_input);
            saveButton= itemView.findViewById(R.id.save_plant_b);
        }
    }
}
