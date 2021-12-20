package app.college.smartcampus.activity;

import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import app.college.smartcampus.R;
import app.college.smartcampus.data.DataManager;
import app.college.smartcampus.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);

        if (DataManager.newInstance(this).isLogin()) {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            SplashActivity.this.finish();
        }

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                SplashActivity.this.finish();
            }
        });

        binding.guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataManager.newInstance(SplashActivity.this).setRole("GUEST");
                startActivity(new Intent(SplashActivity.this, GuestMainActivity.class));
                SplashActivity.this.finish();
            }
        });
    }
}
