package app.college.smartcampus.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import app.college.smartcampus.R;
import app.college.smartcampus.adapters.StudyCornerAdapter;
import app.college.smartcampus.data.DataManager;
import app.college.smartcampus.databinding.ActivityStudyCornerBinding;
import app.college.smartcampus.models.Placement;
import app.college.smartcampus.models.Response;
import app.college.smartcampus.models.StudyCorner;
import app.college.smartcampus.network.NetworkService;
import app.college.smartcampus.network.NetworkServiceBuilder;
import retrofit2.Call;
import retrofit2.Callback;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class StudyCornerActivity extends AppCompatActivity {

    ActivityStudyCornerBinding binding;
    private ProgressDialog dialog;
    private List<StudyCorner> studyCorners = new ArrayList<>();
    StudyCornerAdapter adapter;
    private String year, sem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_study_corner);

        setSupportActionBar(binding.toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Study Corner");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        binding.year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                year = ((TextView) view).getText().toString();
                if (!year.equals("Select Year") && !sem.equals("Select Semseter")) {
                    loadData();
                } else {
                    showMessage("Please select the year");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                year = "0";
            }
        });

        binding.sem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sem = ((TextView) view).getText().toString();
                if (!year.equals("Select Year") && !sem.equals("Select Semseter")) {
                    loadData();
                } else {
                    showMessage("Please select the sem");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                sem = "0";
            }
        });

        loadData();
        binding.list.setLayoutManager(new LinearLayoutManager(this));
        adapter = new StudyCornerAdapter(this, studyCorners);
        binding.list.setAdapter(adapter);
    }

    private void loadData() {
        showLoading("Fetching..");
        NetworkService service = NetworkServiceBuilder.buildMain();
        service.getStudyMaterials(DataManager.newInstance(this).getBranch(), getYear(), getSem()).enqueue(new Callback<Response<List<StudyCorner>>>() {
            @Override
            public void onResponse(Call<Response<List<StudyCorner>>> call, retrofit2.Response<Response<List<StudyCorner>>> response) {
                hideLoading();
                if (response.body() != null) {
                    Response<List<StudyCorner>> bodyResponse = response.body();
                    if (bodyResponse.isStatus()) {
                        List<StudyCorner> studyCorners = bodyResponse.getData();
                        updateUI(studyCorners);
                    }
                    else {
                        binding.list.setVisibility(View.GONE);
                        showMessage(bodyResponse.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<Response<List<StudyCorner>>> call, Throwable t) {
                hideLoading();
                showMessage("Failed to place a call " + t.getLocalizedMessage());
            }
        });
    }

    public String getYear() {
        return year != null ? year : "0";
    }

    public String getSem() {
        return sem != null ? sem : "0";
    }

    private void updateUI(List<StudyCorner> studyCorners) {
        binding.list.setVisibility(View.VISIBLE);
        this.studyCorners.clear();
        this.studyCorners.addAll(studyCorners);
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
                    dialog = new ProgressDialog(StudyCornerActivity.this);
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
