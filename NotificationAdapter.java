package app.college.smartcampus.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import app.college.smartcampus.R;
import app.college.smartcampus.databinding.ViewNotificationItemBinding;
import app.college.smartcampus.models.Notification;
import app.college.smartcampus.utils.CommonUtils;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

    private List<Notification> notifications;
    private LayoutInflater inflater;

    public NotificationAdapter(Context context, List<Notification> notifications) {
        this.notifications = notifications;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewNotificationItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.view_notification_item, parent, false);
        return new NotificationViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        holder.binding.title.setText(notifications.get(position).getTitle());
        holder.binding.content.setText(notifications.get(position).getContent());
        holder.binding.time.setText(CommonUtils.notificationDate(notifications.get(position).getCreatedOn()));
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    static class NotificationViewHolder extends RecyclerView.ViewHolder {
        ViewNotificationItemBinding binding;

        public NotificationViewHolder(ViewNotificationItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
