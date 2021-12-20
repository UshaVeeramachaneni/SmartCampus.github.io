package app.college.smartcampus.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import app.college.smartcampus.R;
import app.college.smartcampus.databinding.ViewAttendanceItemBinding;
import app.college.smartcampus.models.Attendance;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.AttendanceViewHolder> {

    private List<Attendance> attendances;
    private LayoutInflater inflater;

    public AttendanceAdapter(Context context, List<Attendance> attendances) {
        this.attendances = attendances;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public AttendanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewAttendanceItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.view_attendance_item, parent, false);
        return new AttendanceViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AttendanceViewHolder holder, int position) {
        holder.binding.setAttend(attendances.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return attendances.size();
    }

    static class AttendanceViewHolder extends RecyclerView.ViewHolder {

        ViewAttendanceItemBinding binding;

        public AttendanceViewHolder(ViewAttendanceItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
