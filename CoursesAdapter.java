package app.college.smartcampus.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import app.college.smartcampus.R;
import app.college.smartcampus.databinding.ViewCourseItemBinding;
import app.college.smartcampus.models.Course;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.CourseViewHolder> {

    private LayoutInflater inflater;
    private List<Course> courses;

    public CoursesAdapter(Context context, List<Course> courses) {
        this.courses = courses;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewCourseItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.view_course_item, parent, false);
        return new CourseViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        holder.binding.branch.setText(courses.get(position).getBranch());
        holder.binding.intake.setText(String.format("Intake - %d", courses.get(position).getSeats()));
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    static class CourseViewHolder extends RecyclerView.ViewHolder {
        ViewCourseItemBinding binding;

        public CourseViewHolder(ViewCourseItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
