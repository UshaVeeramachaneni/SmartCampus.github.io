package app.college.smartcampus.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import app.college.smartcampus.R;
import app.college.smartcampus.adapters.AttendanceAdapter;
import app.college.smartcampus.data.DataManager;
import app.college.smartcampus.databinding.ActivityAttendanceBinding;
import app.college.smartcampus.models.Attendance;
import app.college.smartcampus.models.Response;
import app.college.smartcampus.network.NetworkService;
import app.college.smartcampus.network.NetworkServiceBuilder;
import app.college.smartcampus.utils.CommonUtils;
import retrofit2.Call;
import retrofit2.Callback;

public class AttendanceActivity extends AppCompatActivity {

    ActivityAttendanceBinding binding;
    private ProgressDialog dialog;
    private AttendanceAdapter adapter;
    private String year = "0", month = "0";
    Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_attendance);

        setSupportActionBar(binding.toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Attendance");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        binding.year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                year = ((TextView) view).getText().toString();
                if (!year.equals("Select Year") && !month.equals("Select Month")) {
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

        binding.month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                month = ((TextView) view).getText().toString();
                if (!year.equals("Select Year") && !month.equals("Select Month")) {
                    loadData();
                } else {
                    showMessage("Please select the month");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                month = "0";
            }
        });
    }

    private void loadData() {
        calendar.set(Calendar.YEAR, Integer.parseInt(year));
        calendar.set(Calendar.MONTH, Integer.parseInt(month) - 1);
        showLoading("Fetching..");
        NetworkService service = NetworkServiceBuilder.buildMain();
        service.getAttendance(DataManager.newInstance(this).getRollno(), getYear(), getMonth()).enqueue(new Callback<Response<List<Attendance>>>() {
            @Override
            public void onResponse(Call<Response<List<Attendance>>> call, retrofit2.Response<Response<List<Attendance>>> response) {
                hideLoading();
                if (response.body() != null) {
                    Response<List<Attendance>> bodyResponse = response.body();
                    if (bodyResponse.isStatus()) {
                        initTable(bodyResponse.getData());
                    } else {
                        showMessage(bodyResponse.getMessage());
                        binding.card.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<Response<List<Attendance>>> call, Throwable t) {
                hideLoading();
                showMessage("Failed to place a call " + t.getLocalizedMessage());
            }
        });
    }

    private void initTable(List<Attendance> attendances) {
        binding.card.setVisibility(View.VISIBLE);
        binding.title.setText(String.format("%s - %s%%", CommonUtils.parseDate(calendar.getTimeInMillis()), getPercentage(attendances)));

        binding.list.setLayoutManager(new LinearLayoutManager(this));
        binding.list.setHasFixedSize(true);
        adapter = new AttendanceAdapter(this, attendances);
        binding.list.setAdapter(adapter);
    }

    private String getPercentage(List<Attendance> attendances) {
        DecimalFormat format = new DecimalFormat("##.##");
        Double total = (double) attendances.size();
        Double count = 0.0;
        for (Attendance attendance : attendances) {
            if (attendance.getAttend().equals("1")) {
                count++;
            }
        }
        return format.format((count / total) * 100);
    }

    public String getYear() {
        return year != null && !year.equals("All") ? year : "0";
    }

    public String getMonth() {
        return month != null && !month.equals("All") ? month : "0";
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
                    dialog = new ProgressDialog(AttendanceActivity.this);
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
