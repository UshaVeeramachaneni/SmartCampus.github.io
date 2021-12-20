package app.college.smartcampus.data;

import android.content.Context;
import android.content.SharedPreferences;

public class DataManager {

    private static final String PREFERENCES = "com.image.authentifier.data";
    private static final String IS_LOGIN = "IS_LOGIN";
    private static final String ROLE = "ROLE";

    private static DataManager instance;
    private static final String NAME = "NAME";
    private static final String ROLLNO = "ROLLNO";
    private static final String EMAIL = "EMAIL";
    private static final String PHONE = "PHONE";
    private static final String BRANCH = "BRANCH";
    private static final String AADHAR = "AADHAR";
    private SharedPreferences preferences;

    private DataManager(Context context) {
        preferences = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
    }

    public static DataManager newInstance(Context context) {
        if (instance == null) {
            instance = new DataManager(context);
        }
        return instance;
    }

    public void setIsLogin(boolean flag) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(IS_LOGIN, flag);
        editor.apply();
    }

    public boolean isLogin() {
        return preferences.getBoolean(IS_LOGIN, false);
    }

    public String getName() {
        return preferences.getString(NAME, "");
    }

    public void setName(String username) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(NAME, username);
        editor.apply();
    }

    public String getRollno() {
        return preferences.getString(ROLLNO, "");
    }

    public void setRollno(String rollno) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(ROLLNO, rollno);
        editor.apply();
    }

    public String getEmail() {
        return preferences.getString(EMAIL, "");
    }

    public void setEmail(String email) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(EMAIL, email);
        editor.apply();
    }

    public String getPhone() {
        return preferences.getString(PHONE, "");
    }

    public void setPhone(String phone) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(PHONE, phone);
        editor.apply();
    }

    public String getBranch() {
        return preferences.getString(BRANCH, "");
    }

    public void setBranch(String branch) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(BRANCH, branch);
        editor.apply();
    }

    public String getAadhar() {
        return preferences.getString(AADHAR, "");
    }

    public void setAadhar(String aadhar) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(AADHAR, aadhar);
        editor.apply();
    }

    public String getRole() {
        return preferences.getString(ROLE, "");
    }

    public void setRole(String role) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(ROLE, role);
        editor.apply();
    }


}
