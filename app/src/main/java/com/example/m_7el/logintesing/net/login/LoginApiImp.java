package com.example.m_7el.logintesing.net.login;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.m_7el.logintesing.modules.LoginInfo;
import com.example.m_7el.logintesing.modules.ResponseData;
import com.example.m_7el.logintesing.modules.UserInformation;
import com.example.m_7el.logintesing.net.ApiCallback;
import com.example.m_7el.logintesing.net.RetrofitInterface;

import java.net.HttpURLConnection;

import okhttp3.OkHttpClient;
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
    public void login(LoginInfo loginInfo, final ApiCallback<UserInformation, String> callback) {
        enqueueCall(mApis.login(loginInfo), callback);
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


    protected final <R> void enqueueCall(@NonNull final Call<ResponseData<R>> call, @NonNull final ApiCallback<R, String> callback) {
        call.enqueue(new Callback<ResponseData<R>>() {

            @Override
            public void onResponse(@NonNull Call<ResponseData<R>> call, @NonNull Response<ResponseData<R>> response) {
                ResponseData<R> responseBody = response.body();
                if (!response.isSuccessful() || responseBody == null) {
                    handleError(responseBody);
                    return;
                }
                if (responseBody.getStatusCode() == HttpURLConnection.HTTP_OK) {
                    callback.onSuccess(responseBody.getData());
                } else {
                    handleError(responseBody);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseData<R>> call, @NonNull Throwable t) {
                handleError(null);
            }

            private void handleError(@Nullable ResponseData<R> responseBody) {
                if (responseBody != null && responseBody.getStatusCode() == HttpURLConnection.HTTP_UNAUTHORIZED) {//Unauthorized user, session is lost/disconnected.
                    callback.onError("error");
                } else {//Unknown error.
                    callback.onError("error");
                }
            }
        });
    }

}