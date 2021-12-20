package app.college.smartcampus.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import app.college.smartcampus.R;
import app.college.smartcampus.adapters.GalleryAdapter;
import app.college.smartcampus.databinding.ActivityGalleryBinding;
import app.college.smartcampus.models.Placement;
import app.college.smartcampus.models.Response;
import app.college.smartcampus.network.NetworkService;
import app.college.smartcampus.network.NetworkServiceBuilder;
import retrofit2.Call;
import retrofit2.Callback;

public class GalleryActivity extends AppCompatActivity {

    ActivityGalleryBinding binding;
    private ProgressDialog dialog;
    private GalleryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_gallery);

        setSupportActionBar(binding.toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Gallery");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        getGalleryData();
    }

    private void getGalleryData() {
        showLoading("Fetching..");
        NetworkService service = NetworkServiceBuilder.buildMain();
        service.getGallery().enqueue(new Callback<Response<List<String>>>() {
            @Override
            public void onResponse(Call<Response<List<String>>> call, retrofit2.Response<Response<List<String>>> response) {
                hideLoading();
                if (response.body() != null) {
                    Response<List<String>> bodyResponse = response.body();
                    if (bodyResponse.isStatus()) {
                        List<String> gallery = bodyResponse.getData();
                        updateUI(gallery);
                    }
                    else {
                        showMessage(bodyResponse.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<Response<List<String>>> call, Throwable t) {
                hideLoading();
                showMessage("Failed to place a call " + t.getLocalizedMessage());
            }
        });
    }

    private void updateUI(List<String> gallery) {
        binding.list.setHasFixedSize(true);
        binding.list.setLayoutManager(new GridLayoutManager(this, 3));
        adapter = new GalleryAdapter(this, gallery);
        binding.list.setAdapter(adapter);
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
                    dialog = new ProgressDialog(GalleryActivity.this);
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
