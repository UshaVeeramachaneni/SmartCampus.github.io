package app.college.smartcampus.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import app.college.smartcampus.R;
import app.college.smartcampus.data.DataManager;
import app.college.smartcampus.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setSupportActionBar(binding.toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Home");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        toggle = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, R.string.drawer_open, R.string.drawer_close);
        binding.drawerLayout.addDrawerListener(toggle);
        ((TextView) binding.navView.getHeaderView(0).findViewById(R.id.name)).setText("Welcome " + DataManager.newInstance(this).getName());
        binding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                binding.drawerLayout.closeDrawer(GravityCompat.START);
                switch (item.getItemId()) {
                    case R.id.attendance:
                        startActivity(new Intent(MainActivity.this, AttendanceActivity.class));
                        break;
                    case R.id.marks:
                        startActivity(new Intent(MainActivity.this, MarksActivity.class));
                        break;
                    case R.id.study:
                        startActivity(new Intent(MainActivity.this, StudyCornerActivity.class));
                        break;
                    case R.id.management:
                        startActivity(new Intent(MainActivity.this, ManagementActivity.class));
                        break;
                    case R.id.courses:
                        startActivity(new Intent(MainActivity.this, CoursesActivity.class));
                        break;
                    case R.id.fee:
                        startActivity(new Intent(MainActivity.this, FeePaymentActivity.class));
                        break;
                    case R.id.placements:
                        startActivity(new Intent(MainActivity.this, PlacementsActivity.class));
                        break;
                    case R.id.gallery:
                        startActivity(new Intent(MainActivity.this, GalleryActivity.class));
                        break;
                    case R.id.about:
                        startActivity(new Intent(MainActivity.this, AboutActivity.class));
                        break;
                    case R.id.contact:
                        startActivity(new Intent(MainActivity.this, ContactActivity.class));
                        break;
                    case R.id.logout:
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        DataManager.newInstance(MainActivity.this).setRole("");
                        DataManager.newInstance(MainActivity.this).setIsLogin(false);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        MainActivity.this.finish();
                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        if (item.getItemId() == R.id.notifications) {
            Intent intent = new Intent(this, NotificationActivity.class);
            intent.putExtra("type", "General");
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }
}
