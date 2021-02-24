package io.github.adarshasapkota.notes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private final ArrayList<Note> arrayListNotes;
    private final Context context;
    private final RecyclerView recyclerView;

    public NoteAdapter(ArrayList<Note> arrayListNotes, Context context, RecyclerView recyclerView) {
        this.arrayListNotes = arrayListNotes;
        this.context = context;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.note, parent, false);
        view.setOnClickListener(v -> {
            Intent intent=new Intent(context, NoteActivity.class);
            intent.putExtra("note", arrayListNotes.get(recyclerView.getChildLayoutPosition(view)));
            context.startActivity(intent);
        });
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getNote().setText(arrayListNotes.get(position).getNote());
        holder.getLastModifiedDate().setText(String.valueOf(arrayListNotes.get(position).getLastModifiedDate()));
    }

    @Override
    public int getItemCount() {
        return arrayListNotes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView note;
        private final TextView lastModifiedDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            note=itemView.findViewById(R.id.tv_note);
            lastModifiedDate=itemView.findViewById(R.id.tv_last_modified_date);
        }

        public TextView getLastModifiedDate() {
            return lastModifiedDate;
        }

        public TextView getNote() {
            return note;
        }
    }
}
