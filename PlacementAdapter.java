package app.college.smartcampus.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import app.college.smartcampus.R;
import app.college.smartcampus.databinding.ViewPlacementItemBinding;
import app.college.smartcampus.models.Placement;

public class PlacementAdapter extends RecyclerView.Adapter<PlacementAdapter.PlacementViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<Placement> placements;

    public PlacementAdapter(Context context, List<Placement> placements) {
        this.context = context;
        this.placements = placements;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public PlacementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewPlacementItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.view_placement_item, parent, false);
        return new PlacementViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PlacementViewHolder holder, int position) {
        holder.binding.year.setText(placements.get(position).getYear());
        holder.binding.dataList.setNestedScrollingEnabled(false);
        holder.binding.dataList.setHasFixedSize(true);
        holder.binding.dataList.setLayoutManager(new LinearLayoutManager(context));
        PlacementDataAdapter adapter = new PlacementDataAdapter(context, placements.get(position).getData());
        holder.binding.dataList.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return placements.size();
    }

    static class PlacementViewHolder extends RecyclerView.ViewHolder {
        ViewPlacementItemBinding binding;

        public PlacementViewHolder(ViewPlacementItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
