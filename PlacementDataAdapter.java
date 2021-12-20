package app.college.smartcampus.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import app.college.smartcampus.R;
import app.college.smartcampus.databinding.ViewPlacementDataItemBinding;
import app.college.smartcampus.models.Placement;

public class PlacementDataAdapter extends RecyclerView.Adapter<PlacementDataAdapter.PlacementDataViewHolder> {

    private LayoutInflater inflater;
    private List<Placement.PlacementData> dataList;

    public PlacementDataAdapter(Context context, List<Placement.PlacementData> dataList) {
        this.dataList = dataList;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public PlacementDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewPlacementDataItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.view_placement_data_item, parent, false);
        return new PlacementDataViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PlacementDataViewHolder holder, int position) {
        Placement.PlacementData data = dataList.get(position);
        holder.binding.company.setText("Company : " + data.getCompany());
        holder.binding.total.setText("Total : " + data.getStudentsCount() + " Students");
        holder.binding.ce.setText("CE : " + data.getCe());
        holder.binding.eee.setText("EEE : " + data.getEee());
        holder.binding.mech.setText("Mech : " + data.getMech());
        holder.binding.ece.setText("ECE : " + data.getEce());
        holder.binding.cse.setText("CSE : " + data.getCse());
    }

    @Override
    public int getItemCount() {
        return dataList != null ? dataList.size() : 0;
    }

    static class PlacementDataViewHolder extends RecyclerView.ViewHolder {
        ViewPlacementDataItemBinding binding;

        PlacementDataViewHolder(ViewPlacementDataItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
