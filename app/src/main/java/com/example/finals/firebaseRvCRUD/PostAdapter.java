package com.example.finals.firebaseRvCRUD;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finals.R;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private List<Post> postList;
    private FirebaseRvCRUD fragment;

    public PostAdapter(List<Post> postList, FirebaseRvCRUD fragment) {
        this.postList = postList;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_post, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = postList.get(position);
        holder.tvTitle.setText(post.getTitle());
        holder.tvBody.setText(post.getBody());

        holder.itemView.setOnClickListener(v -> {
            // Update post
            AlertDialog.Builder builder = new AlertDialog.Builder(fragment.requireContext());
            builder.setTitle("Update Post");

            View dialogView = LayoutInflater.from(fragment.requireContext())
                    .inflate(R.layout.dialog_post, null);

            EditText etTitle = dialogView.findViewById(R.id.etTitle);
            EditText etBody = dialogView.findViewById(R.id.etBody);
            etTitle.setText(post.getTitle());
            etBody.setText(post.getBody());

            builder.setView(dialogView);
            builder.setPositiveButton("Update", (dialog, which) -> {
                String newTitle = etTitle.getText().toString();
                String newBody = etBody.getText().toString();
                fragment.updatePost(post, newTitle, newBody);
            });
            builder.setNegativeButton("Cancel", null);
            builder.show();
        });

        holder.itemView.setOnLongClickListener(v -> {
            // Delete post
            AlertDialog.Builder builder = new AlertDialog.Builder(fragment.requireContext());
            builder.setTitle("Delete Post")
                    .setMessage("Are you sure you want to delete this post?")
                    .setPositiveButton("Yes", (dialog, which) -> fragment.deletePost(post))
                    .setNegativeButton("No", null)
                    .show();
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvBody;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle2);
            tvBody = itemView.findViewById(R.id.tvBody);
        }
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
        notifyDataSetChanged();
    }
}
