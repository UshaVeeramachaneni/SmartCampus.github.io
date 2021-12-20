package app.college.smartcampus.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import app.college.smartcampus.R;
import app.college.smartcampus.databinding.ViewMarkItemBinding;
import app.college.smartcampus.models.Mark;

public class MarksAdapter extends RecyclerView.Adapter<MarksAdapter.MarkViewHolder> {

    private List<Mark> marks;
    private LayoutInflater inflater;

    public MarksAdapter(Context context, List<Mark> marks) {
        this.marks = marks;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public MarkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewMarkItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.view_mark_item, parent, false);
        return new MarkViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MarkViewHolder holder, int position) {
        holder.setMark(marks.get(position));
    }

    @Override
    public int getItemCount() {
        return marks.size();
    }

    static class MarkViewHolder extends RecyclerView.ViewHolder {

        ViewMarkItemBinding binding;

        MarkViewHolder(ViewMarkItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void setMark(Mark mark) {
            binding.setMark(mark);
            binding.executePendingBindings();
        }
    }
}
