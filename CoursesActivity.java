package app.college.smartcampus.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import app.college.smartcampus.R;
import app.college.smartcampus.adapters.CoursesAdapter;
import app.college.smartcampus.databinding.ActivityCoursesBinding;
import app.college.smartcampus.models.Course;

public class CoursesActivity extends AppCompatActivity {

    ActivityCoursesBinding binding;
    private CoursesAdapter madapter, badapter, dadapter;
    private List<Course> mcourses = new ArrayList<>();
    private List<Course> bcourses = new ArrayList<>();
    private List<Course> dcourses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_courses);

        setSupportActionBar(binding.toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Courses");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        buildMtechData();
        buildBtechData();
        buildDiplomaData();

        binding.mlist.setHasFixedSize(true);
        binding.mlist.setLayoutManager(new LinearLayoutManager(this));
        binding.mlist.setNestedScrollingEnabled(false);
        madapter = new CoursesAdapter(this, mcourses);
        binding.mlist.setAdapter(madapter);

        binding.blist.setHasFixedSize(true);
        binding.blist.setLayoutManager(new LinearLayoutManager(this));
        binding.blist.setNestedScrollingEnabled(false);
        badapter = new CoursesAdapter(this, bcourses);
        binding.blist.setAdapter(badapter);

        binding.dlist.setHasFixedSize(true);
        binding.dlist.setLayoutManager(new LinearLayoutManager(this));
        binding.dlist.setNestedScrollingEnabled(false);
        dadapter = new CoursesAdapter(this, dcourses);
        binding.dlist.setAdapter(dadapter);
    }

    private void buildMtechData() {
        Course cse = new Course("Computer Science & Engineering", 24);
        Course vlsi = new Course("VLSI Design", 24);
        mcourses.add(vlsi);
        mcourses.add(cse);
    }

    private void buildBtechData() {
        Course civil = new Course("Civil Engineering", 120);
        Course eee = new Course("Electrical & Electronics Engineering", 60);
        Course me = new Course("Mechanical Engineering", 120);
        Course ece = new Course("Electronics & Communication Engineering", 120);
        Course cse = new Course("Computer Science & Engineering", 120);
        bcourses.add(civil);
        bcourses.add(eee);
        bcourses.add(me);
        bcourses.add(ece);
        bcourses.add(cse);
    }

    private void buildDiplomaData() {
        Course civil = new Course("Civil Engineering", 60);
        Course eee = new Course("Electrical & Electronics Engineering", 60);
        Course me = new Course("Mechanical Engineering", 60);
        Course ece = new Course("Electronics & Communication Engineering", 60);
        dcourses.add(civil);
        dcourses.add(eee);
        dcourses.add(me);
        dcourses.add(ece);
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
