package app.college.smartcampus;

import android.app.Application;
import androidx.appcompat.app.AppCompatDelegate;

import com.facebook.stetho.Stetho;

public class SmartApp extends Application {

    private static SmartApp appInstance;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    public static SmartApp getAppInstance() {
        return appInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
        Stetho.initializeWithDefaults(this);
    }
}
