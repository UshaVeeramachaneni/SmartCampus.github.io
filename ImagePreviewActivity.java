package app.college.smartcampus.activity;

import android.os.Bundle;
import android.view.MenuItem;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;
import app.college.smartcampus.R;
import app.college.smartcampus.databinding.ActivityImagePreviewBinding;
import app.college.smartcampus.utils.Constants;

public class ImagePreviewActivity extends AppCompatActivity {

    ActivityImagePreviewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_image_preview);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Image Preview");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        String link = getIntent().getStringExtra("link");
        CircularProgressDrawable drawable = new CircularProgressDrawable(this);
        drawable.setStrokeWidth(5f);
        drawable.setCenterRadius(30f);
        drawable.start();
        Glide.with(this)
                .load(Constants.API_MAIN_URL + "gallery/" + link)
                .apply(new RequestOptions().placeholder(drawable))
                .into(binding.imageView);
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
