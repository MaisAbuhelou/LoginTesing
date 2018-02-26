package com.example.m_7el.logintesing.net.login;


import android.provider.CalendarContract;
import android.support.annotation.NonNull;

import com.example.m_7el.logintesing.modules.ResponseData;
import com.example.m_7el.logintesing.modules.LoginInfo;
import com.example.m_7el.logintesing.net.ApiCallback;
import com.example.m_7el.logintesing.net.RetrofitInterface;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginApiImp implements LoginApi {
    private static final String BASE_URL = "http://api.harridev.com/";
    private RetrofitInterface mApis = initiateRetrofit();


    @Override
    public void getUserInfo(LoginInfo loginInfo, final ApiCallback<ResponseData, String> callbacks) {
        enqueueCall(mApis.login(loginInfo), callbacks);
    }

   public RetrofitInterface initiateRetrofit() {
        Retrofit retrofit;
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();


        return retrofit.create(RetrofitInterface.class);
    }


    private void enqueueCall(@NonNull Call<ResponseData> call, @NonNull final ApiCallback<ResponseData, String> callback) {
        call.enqueue(new Callback<ResponseData>() {


            @Override
            public void onResponse(@NonNull Call<ResponseData> call, @NonNull Response<ResponseData> response) {
                callback.onSuccess(response.body());

            }

            @Override
            public void onFailure(@NonNull Call<ResponseData> call, @NonNull Throwable t) {
                callback.onError(t.toString());
                t.printStackTrace();

            }
        });

    }

}