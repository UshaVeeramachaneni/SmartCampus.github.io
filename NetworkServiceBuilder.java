package app.college.smartcampus.network;

import androidx.annotation.NonNull;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import app.college.smartcampus.utils.Constants;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkServiceBuilder {

    private static OkHttpClient okHttpClient =
            new OkHttpClient.Builder()
                    .addNetworkInterceptor(new StethoInterceptor())
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(@NonNull Chain chain) throws IOException {
                            Request original = chain.request();
                            Request request = NetworkServiceBuilder
                                    .getRequestBuilder(original)
                                    .build();
                            return chain.proceed(request);
                        }
                    })
                    .readTimeout(60 * 2, TimeUnit.SECONDS)
                    .connectTimeout(60 * 2, TimeUnit.SECONDS)
                    .build();

    public static NetworkService buildMain() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit =
                new Retrofit.Builder()
                        .baseUrl(Constants.API_MAIN_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .client(getInterceptor())
                        .build();
        return retrofit.create(NetworkService.class);
    }

    private static OkHttpClient getInterceptor() {
        return okHttpClient;
    }

    private static Request.Builder getRequestBuilder(Request request) {
        return request.newBuilder();
    }

    public static void cancelRequests() {
        okHttpClient.dispatcher().cancelAll();
    }
}
