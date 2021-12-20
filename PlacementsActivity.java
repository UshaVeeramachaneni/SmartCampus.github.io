package app.college.smartcampus.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import app.college.smartcampus.R;
import app.college.smartcampus.adapters.ManagementAdapter;
import app.college.smartcampus.adapters.PlacementAdapter;
import app.college.smartcampus.databinding.ActivityPlacementsBinding;
import app.college.smartcampus.models.Placement;
import app.college.smartcampus.models.Response;
import app.college.smartcampus.network.NetworkService;
import app.college.smartcampus.network.NetworkServiceBuilder;
import retrofit2.Call;
import retrofit2.Callback;

public class PlacementsActivity extends AppCompatActivity {

    ActivityPlacementsBinding binding;
    private List<Placement> placements = new ArrayList<>();
    private PlacementAdapter adapter;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_placements);

        setSupportActionBar(binding.toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Placements");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        getPlacementData();
        binding.list.setHasFixedSize(true);
        binding.list.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PlacementAdapter(this, placements);
        binding.list.setAdapter(adapter);
    }

    private void getPlacementData() {
        showLoading("Fetching..");
        NetworkService service = NetworkServiceBuilder.buildMain();
        service.getPlacements().enqueue(new Callback<Response<List<Placement>>>() {
            @Override
            public void onResponse(Call<Response<List<Placement>>> call, retrofit2.Response<Response<List<Placement>>> response) {
                hideLoading();
                if (response.body() != null) {
                    Response<List<Placement>> bodyResponse = response.body();
                    if (bodyResponse.isStatus()) {
                        List<Placement> placements = bodyResponse.getData();
                        updateUI(placements);
                    }
                    else {
                        showMessage(bodyResponse.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<Response<List<Placement>>> call, Throwable t) {
                hideLoading();
                showMessage("Failed to place a call " + t.getLocalizedMessage());
            }
        });
    }

    private void updateUI(List<Placement> placements) {
        this.placements.clear();
        this.placements.addAll(placements);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
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
                    dialog = new ProgressDialog(PlacementsActivity.this);
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
