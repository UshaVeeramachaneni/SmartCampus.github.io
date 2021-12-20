package app.college.smartcampus.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import app.college.smartcampus.R;
import app.college.smartcampus.adapters.MarksAdapter;
import app.college.smartcampus.data.DataManager;
import app.college.smartcampus.databinding.ActivityMarksBinding;
import app.college.smartcampus.models.Mark;
import app.college.smartcampus.models.Response;
import app.college.smartcampus.network.NetworkService;
import app.college.smartcampus.network.NetworkServiceBuilder;
import retrofit2.Call;
import retrofit2.Callback;

public class MarksActivity extends AppCompatActivity {

    ActivityMarksBinding binding;
    private ProgressDialog dialog;
    private String year = "0", sem = "0";
    private MarksAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_marks);

        setSupportActionBar(binding.toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Marks");
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
    }

    private void loadData() {
        showLoading("Fetching..");
        NetworkService service = NetworkServiceBuilder.buildMain();
        service.getMarks(DataManager.newInstance(this).getRollno(), getYear(), getSem()).enqueue(new Callback<Response<List<Mark>>>() {
            @Override
            public void onResponse(Call<Response<List<Mark>>> call, retrofit2.Response<Response<List<Mark>>> response) {
                hideLoading();
                if (response.body() != null) {
                    Response<List<Mark>> bodyResponse = response.body();
                    if (bodyResponse.isStatus()) {
                        initTable(bodyResponse.getData());
                    } else {
                        showMessage(bodyResponse.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<Response<List<Mark>>> call, Throwable t) {
                hideLoading();
                showMessage("Failed to place a call " + t.getLocalizedMessage());
            }
        });
    }

    public String getYear() {
        return year != null && !year.equals("All") ? year : "0";
    }

    public String getSem() {
        return sem != null && !sem.equals("All") ? sem : "0";
    }

    private void initTable(List<Mark> marks) {
        binding.list.setLayoutManager(new LinearLayoutManager(this));
        binding.list.setHasFixedSize(true);
        adapter = new MarksAdapter(this, marks);
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
                    dialog = new ProgressDialog(MarksActivity.this);
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
