package app.college.smartcampus.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import app.college.smartcampus.R;
import app.college.smartcampus.data.DataManager;
import app.college.smartcampus.databinding.ActivityRegisterBinding;
import app.college.smartcampus.models.Response;
import app.college.smartcampus.models.User;
import app.college.smartcampus.network.NetworkService;
import app.college.smartcampus.network.NetworkServiceBuilder;
import app.college.smartcampus.utils.CommonUtils;
import retrofit2.Call;
import retrofit2.Callback;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;
    User user = new User();
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);

        binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

        binding.branch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                user.setBranch(((TextView) view).getText().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void registerUser() {
        user.setName(binding.name.getText().toString().trim());
        user.setRollno(binding.rollNo.getText().toString().trim());
        user.setPhone(binding.phone.getText().toString().trim());
        user.setEmail(binding.email.getText().toString().trim());
        user.setAadhar(binding.aadhar.getText().toString().trim());
        user.setPassword(binding.password.getText().toString().trim());
        validateUser();
    }

    private void validateUser() {
        if (!CommonUtils.isStringNotNull(user.getName())) {
            showMessage("Please enter Name");
        } else if (!CommonUtils.isStringNotNull(user.getRollno())) {
            showMessage("Please enter Roll Number");
        } else if (!CommonUtils.isStringNotNull(user.getPhone())) {
            showMessage("Please enter Phone Number");
        } else if (!CommonUtils.isStringNotNull(user.getEmail())) {
            showMessage("Please enter Email Address");
        } else if (!CommonUtils.isStringNotNull(user.getAadhar())) {
            showMessage("Please enter Aadhar Number");
        } else if (!CommonUtils.isStringNotNull(user.getPassword())) {
            showMessage("Please enter password");
        } else if (!CommonUtils.isStringNotNull(user.getBranch()) || (CommonUtils.isStringNotNull(user.getBranch()) && user.getBranch().equals("Select Branch"))) {
            showMessage("Please select a branch");
        } else {
            sendDataToServer();
        }
    }

    private void sendDataToServer() {
        showLoading("Registering..");
        Map<String, String> userMap = new HashMap<>();
        userMap.put("name", user.getName());
        userMap.put("rollno", user.getRollno());
        userMap.put("phone", user.getPhone());
        userMap.put("email", user.getEmail());
        userMap.put("aadhar", user.getAadhar());
        userMap.put("password", user.getPassword());
        userMap.put("branch", user.getBranch());
        NetworkService service = NetworkServiceBuilder.buildMain();
        service.registerUser(userMap).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {
                hideLoading();
                if (response.body() != null) {
                    Response bodyResponse = response.body();
                    showMessage(bodyResponse.getMessage());
                    if (bodyResponse.isStatus()) {
                        RegisterActivity.this.finish();
                    }
                } else {
                    showMessage("Unable to connect, Please try again");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {
                hideLoading();
                showMessage("Failed to place a call "+ t.getLocalizedMessage());
            }
        });
    }

    private void showMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    public void showLoading(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialog == null) {
                    dialog = new ProgressDialog(RegisterActivity.this);
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
