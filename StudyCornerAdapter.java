package app.college.smartcampus.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import app.college.smartcampus.R;
import app.college.smartcampus.databinding.ViewStudyCornerItemBinding;
import app.college.smartcampus.models.StudyCorner;

public class StudyCornerAdapter extends RecyclerView.Adapter<StudyCornerAdapter.StudyCornerViewHolder> {

    private Context context;
    private List<StudyCorner> studyCorners;
    private LayoutInflater inflater;

    public StudyCornerAdapter(Context context, List<StudyCorner> studyCorners) {
        this.context = context;
        this.studyCorners = studyCorners;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public StudyCornerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewStudyCornerItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.view_study_corner_item, parent, false);
        return new StudyCornerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final StudyCornerViewHolder holder, int position) {
        holder.binding.name.setText(studyCorners.get(position).getName());

        holder.binding.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(studyCorners.get(holder.getAdapterPosition()).getLink()));
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return studyCorners.size();
    }

    static class StudyCornerViewHolder extends RecyclerView.ViewHolder {

        ViewStudyCornerItemBinding binding;

        StudyCornerViewHolder(ViewStudyCornerItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
