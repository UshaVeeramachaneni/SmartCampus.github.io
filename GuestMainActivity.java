package app.college.smartcampus.activity;

import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;

import app.college.smartcampus.R;
import app.college.smartcampus.data.DataManager;
import app.college.smartcampus.databinding.ActivityGuestMainBinding;

public class GuestMainActivity extends AppCompatActivity {

    ActivityGuestMainBinding binding;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_guest_main);
        setSupportActionBar(binding.toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Home");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        toggle = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, R.string.drawer_open, R.string.drawer_close);
        binding.drawerLayout.addDrawerListener(toggle);

        binding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                binding.drawerLayout.closeDrawer(GravityCompat.START);
                switch (item.getItemId()) {
                    case R.id.management:
                        startActivity(new Intent(GuestMainActivity.this, ManagementActivity.class));
                        break;
                    case R.id.courses:
                        startActivity(new Intent(GuestMainActivity.this, CoursesActivity.class));
                        break;
                    case R.id.placements:
                        startActivity(new Intent(GuestMainActivity.this, PlacementsActivity.class));
                        break;
                    case R.id.gallery:
                        startActivity(new Intent(GuestMainActivity.this, GalleryActivity.class));
                        break;
                    case R.id.about:
                        startActivity(new Intent(GuestMainActivity.this, AboutActivity.class));
                        break;
                    case R.id.contact:
                        startActivity(new Intent(GuestMainActivity.this, ContactActivity.class));
                        break;
                    case R.id.logout:
                        Intent intent = new Intent(GuestMainActivity.this, LoginActivity.class);
                        DataManager.newInstance(GuestMainActivity.this).setRole("");
                        DataManager.newInstance(GuestMainActivity.this).setIsLogin(false);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        GuestMainActivity.this.finish();
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
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
