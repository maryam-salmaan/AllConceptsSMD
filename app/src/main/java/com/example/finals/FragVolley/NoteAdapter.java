package com.example.finals.FragVolley;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finals.R;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    public interface OnNoteActionListener {
        void onTap(Note note, int position);
        void onLongPress(Note note, int position);
    }

    private final List<Note> noteList;
    private OnNoteActionListener listener;

    public NoteAdapter(List<Note> noteList) {
        this.noteList = noteList;
    }

    public void setListener(OnNoteActionListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note note = noteList.get(position);

        holder.title.setText(note.getTitle());
        holder.desc.setText(note.getDescription());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null)
                listener.onTap(note, position);
        });

        holder.itemView.setOnLongClickListener(v -> {
            if (listener != null)
                listener.onLongPress(note, position);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, desc;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvTitle3);
            desc = itemView.findViewById(R.id.tvDesc2);
        }
    }
}
