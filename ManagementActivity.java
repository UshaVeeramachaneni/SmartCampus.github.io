package app.college.smartcampus.activity;

import androidx.databinding.DataBindingUtil;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import app.college.smartcampus.R;
import app.college.smartcampus.adapters.ManagementAdapter;
import app.college.smartcampus.databinding.ActivityManagementBinding;
import app.college.smartcampus.models.Management;

public class ManagementActivity extends AppCompatActivity {

    ActivityManagementBinding binding;
    private ManagementAdapter adapter;
    private List<Management> managements = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_management);

        setSupportActionBar(binding.toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Management");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        buildData();
        binding.list.setHasFixedSize(true);
        binding.list.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ManagementAdapter(this, managements);
        binding.list.setAdapter(adapter);
    }

    private void buildData() {
        Management chairman = new Management("Chairman", "Name: Dhanekula Ravindranath Tagore");
        Management secretary = new Management("Secretary", "Name: Dhanekula Bhavani Prasad");
        Management principal = new Management("Principal", "Name: Dr. Ravi Kadiyala");
        managements.add(chairman);
        managements.add(secretary);
        managements.add(principal);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
