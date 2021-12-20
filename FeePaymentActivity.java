package app.college.smartcampus.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import app.college.smartcampus.R;
import app.college.smartcampus.data.DataManager;
import app.college.smartcampus.databinding.ActivityFeePaymentBinding;
import app.college.smartcampus.models.Response;
import app.college.smartcampus.network.NetworkService;
import app.college.smartcampus.network.NetworkServiceBuilder;
import retrofit2.Call;
import retrofit2.Callback;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class FeePaymentActivity extends AppCompatActivity {

    ActivityFeePaymentBinding binding;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_fee_payment);

        setSupportActionBar(binding.toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Fee Payment");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        binding.regno.setText(DataManager.newInstance(this).getRollno());
        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                payFee();
            }
        });
    }

    private void payFee() {
        if (!binding.year.getSelectedItem().toString().equalsIgnoreCase("--Select--")) {
            showLoading("Paying..");
            NetworkService service = NetworkServiceBuilder.buildMain();
            service.payFee(DataManager.newInstance(this).getRollno(), binding.year.getSelectedItem().toString()).enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                    hideLoading();
                    if (response.body() != null) {
                        Response bodyResponse = response.body();
                        showMessage(bodyResponse.getMessage());
                        if (bodyResponse.isStatus()) {
                            FeePaymentActivity.this.finish();
                        }
                    } else {
                        showMessage("Unable to connect, Please try again");
                    }
                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {
                    hideLoading();
                    showMessage("Failed to place a call "+ t.getLocalizedMessage());
                }
            });
        }
        else {
            showMessage("Please select year");
        }
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
                    dialog = new ProgressDialog(FeePaymentActivity.this);
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
