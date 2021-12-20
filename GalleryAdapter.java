package app.college.smartcampus.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;
import app.college.smartcampus.R;
import app.college.smartcampus.activity.ImagePreviewActivity;
import app.college.smartcampus.databinding.ViewImageItemBinding;
import app.college.smartcampus.utils.Constants;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {

    private Context context;
    private List<String> images;
    private LayoutInflater inflater;

    public GalleryAdapter(Context context, List<String> images) {
        this.context = context;
        this.images = images;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewImageItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.view_image_item, parent, false);
        return new GalleryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final GalleryViewHolder holder, int position) {
        CircularProgressDrawable drawable = new CircularProgressDrawable(context);
        drawable.setStrokeWidth(5f);
        drawable.setCenterRadius(30f);
        drawable.start();
        Glide.with(context)
                .load(Constants.API_MAIN_URL + "gallery/" + images.get(position))
                .apply(new RequestOptions().placeholder(drawable))
                .into(holder.binding.image);

        holder.binding.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ImagePreviewActivity.class);
                intent.putExtra("link", images.get(holder.getAdapterPosition()));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    static class GalleryViewHolder extends RecyclerView.ViewHolder {
        ViewImageItemBinding binding;

        public GalleryViewHolder(ViewImageItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
