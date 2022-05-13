package com.agostina.mr.plantagram2.ui.community;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agostina.mr.plantagram2.R;
import com.agostina.mr.plantagram2.model.post.Comment;

import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {
   private List<Comment> commentList;

    public CommentsAdapter(List<Comment> commentList) {
        this.commentList = commentList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.comments_item, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      holder.author.setText(commentList.get(position).getCommentsAuthorName());
      holder.comment.setText(commentList.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public void updateComments(List<Comment> comments) {
        //this.commentList.clear();
        this.commentList = comments;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView author;
        private final TextView comment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.comments_author_tv);
            comment = itemView.findViewById(R.id.comments_body);
        }
    }
}
