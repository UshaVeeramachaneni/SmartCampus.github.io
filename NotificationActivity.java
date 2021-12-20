package app.college.smartcampus.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import app.college.smartcampus.R;
import app.college.smartcampus.adapters.NotificationAdapter;
import app.college.smartcampus.databinding.ActivityNotificationBinding;
import app.college.smartcampus.models.Notification;
import app.college.smartcampus.models.Response;
import app.college.smartcampus.network.NetworkService;
import app.college.smartcampus.network.NetworkServiceBuilder;
import retrofit2.Call;
import retrofit2.Callback;

public class NotificationActivity extends AppCompatActivity {

    ActivityNotificationBinding binding;
    private NotificationAdapter adapter;
    private ProgressDialog dialog;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_notification);

        setSupportActionBar(binding.toolbar);

        type = getIntent().getStringExtra("type");

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(type + " Notifications");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        getNotificationData();
    }

    private void getNotificationData() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(type + " Notifications");
        }
        showLoading("Fetching..");
        NetworkService service = NetworkServiceBuilder.buildMain();
        service.getNotifications(type).enqueue(new Callback<Response<List<Notification>>>() {
            @Override
            public void onResponse(Call<Response<List<Notification>>> call, retrofit2.Response<Response<List<Notification>>> response) {
                hideLoading();
                if (response.body() != null) {
                    Response<List<Notification>> bodyResponse = response.body();
                    if (bodyResponse.isStatus()) {
                        binding.list.setVisibility(View.VISIBLE);
                        List<Notification> notifications = bodyResponse.getData();
                        updateUI(notifications);
                    } else {
                        showMessage(bodyResponse.getMessage());
                        binding.list.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<Response<List<Notification>>> call, Throwable t) {
                hideLoading();
                showMessage("Failed to place a call " + t.getLocalizedMessage());
            }
        });
    }

    private void updateUI(List<Notification> notifications) {
        binding.list.setHasFixedSize(true);
        binding.list.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NotificationAdapter(this, notifications);
        binding.list.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_notification, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else if (item.getItemId() == R.id.technical) {
            type = "Technical";
            getNotificationData();
            return true;
        } else if (item.getItemId() == R.id.general) {
            type = "General";
            getNotificationData();
            return true;
        } else if (item.getItemId() == R.id.cultural) {
            type = "Cultural";
            getNotificationData();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    public void showLoading(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialog == null) {
                    dialog = new ProgressDialog(NotificationActivity.this);
                    dialog.setMessage(message);
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.setCancelable(false);
                    if (!isFinishing()) {
                        dialog.show();
                    }
                } else {
                    hideLoading();
                    showLoading(message);
                }

            }
        });
    }

    public void hideLoading() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                    dialog = null;
                }
            }
        });
    }
}
