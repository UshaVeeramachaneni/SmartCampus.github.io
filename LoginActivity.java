package app.college.smartcampus.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import app.college.smartcampus.R;
import app.college.smartcampus.data.DataManager;
import app.college.smartcampus.databinding.ActivityLoginBinding;
import app.college.smartcampus.models.Response;
import app.college.smartcampus.models.User;
import app.college.smartcampus.network.NetworkService;
import app.college.smartcampus.network.NetworkServiceBuilder;
import app.college.smartcampus.utils.CommonUtils;
import retrofit2.Call;
import retrofit2.Callback;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    User user = new User();
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        user.setRollno(binding.rollNo.getText().toString().trim());
        user.setPassword(binding.password.getText().toString().trim());
        validateUser();
    }

    private void validateUser() {
        if (!CommonUtils.isStringNotNull(user.getRollno())) {
            showMessage("Please enter Roll Number");
        } else if (!CommonUtils.isStringNotNull(user.getPassword())) {
            showMessage("Please enter password");
        } else {
            sendDataToServer();
        }
    }

    private void sendDataToServer() {
        showLoading("Logging..");
        NetworkService service = NetworkServiceBuilder.buildMain();
        service.loginUser(user.getRollno(), user.getPassword()).enqueue(new Callback<Response<User>>() {
            @Override
            public void onResponse(Call<Response<User>> call, retrofit2.Response<Response<User>> response) {
                hideLoading();
                if (response.body() != null) {
                    Response bodyResponse = response.body();
                    if (bodyResponse.isStatus()) {
                        User user = (User) bodyResponse.getData();
                        persistData(user);
                        loginSuccess();
                    } else {
                        showMessage(bodyResponse.getMessage());
                    }
                } else {
                    showMessage("Unable to connect, Please try again");
                }
            }

            @Override
            public void onFailure(Call<Response<User>> call, Throwable t) {
                hideLoading();
                showMessage("Failed to place a call "+ t.getLocalizedMessage());
            }
        });
    }

    private void persistData(User user) {
        DataManager.newInstance(this).setRole("STUDENT");
        DataManager.newInstance(this).setName(user.getName());
        DataManager.newInstance(this).setEmail(user.getEmail());
        DataManager.newInstance(this).setPhone(user.getPhone());
        DataManager.newInstance(this).setRollno(user.getRollno());
        DataManager.newInstance(this).setAadhar(user.getAadhar());
        DataManager.newInstance(this).setBranch(user.getBranch());
    }

    private void loginSuccess() {
        DataManager.newInstance(this).setIsLogin(true);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void moveToRegister(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    private void showMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    public void showLoading(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialog == null) {
                    dialog = new ProgressDialog(LoginActivity.this);
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
