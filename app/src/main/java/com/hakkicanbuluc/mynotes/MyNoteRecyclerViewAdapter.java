package com.hakkicanbuluc.mynotes;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hakkicanbuluc.mynotes.placeholder.PlaceholderContent.PlaceholderItem;
import com.hakkicanbuluc.mynotes.databinding.FragmentNoteBinding;

import java.text.SimpleDateFormat;
import java.util.List;

public class MyNoteRecyclerViewAdapter extends RecyclerView.Adapter<MyNoteRecyclerViewAdapter.ViewHolder> {

    private final List<Note> mValues;
    private final NoteFragment.OnNoteListInteractionListener mListener;

    public MyNoteRecyclerViewAdapter(List<Note> notes, NoteFragment.
            OnNoteListInteractionListener listener) {
        this.mValues = notes;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public MyNoteRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_note,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyNoteRecyclerViewAdapter.ViewHolder holder, int
            position) {
        holder.mItem = mValues.get(position);
        holder.mHeaderView.setText(mValues.get(position).getHeader());
        holder.mDateView.setText((new SimpleDateFormat("yyyy-MM-dd")).format(mValues.get(
                position).getDate()));
        holder.mView.setOnClickListener(view -> {
            if (mListener != null) {
                mListener.onNoteSelected(holder.mItem);
            }
        });
        if (position % 2 == 1) {
            holder.itemView.setBackgroundColor(Color.YELLOW);
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mHeaderView;
        public final TextView mDateView;
        public Note mItem;

        public ViewHolder(@NonNull View view) {
            super(view);
            this.mView = view;
            mHeaderView = view.findViewById(R.id.note_header);
            mDateView = view.findViewById(R.id.note_date);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mHeaderView.getText() + "'";
        }
    }
}