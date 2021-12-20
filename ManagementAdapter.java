package app.college.smartcampus.adapters;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import app.college.smartcampus.R;
import app.college.smartcampus.databinding.ViewManagementItemBinding;
import app.college.smartcampus.models.Management;

public class ManagementAdapter extends RecyclerView.Adapter<ManagementAdapter.ManagementViewHolder> {

    private List<Management> managements;
    private LayoutInflater inflater;

    public ManagementAdapter(Context context, List<Management> managements) {
        this.managements = managements;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ManagementViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewManagementItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.view_management_item, viewGroup, false);
        return new ManagementViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ManagementViewHolder holder, int position) {
        holder.binding.title.setText(managements.get(position).getTitle());
        holder.binding.description.setText(managements.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return managements.size();
    }

    static class ManagementViewHolder extends RecyclerView.ViewHolder {
        ViewManagementItemBinding binding;

        ManagementViewHolder(ViewManagementItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
