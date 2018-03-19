

package com.example.m_7el.logintesing.net;

import android.content.Context;

import com.example.m_7el.logintesing.R;
import com.example.m_7el.logintesing.modules.LoginInfo;
import com.example.m_7el.logintesing.modules.ResponseData;
import com.example.m_7el.logintesing.modules.UserInformation;
import com.example.m_7el.logintesing.net.RetrofitInterface;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import retrofit2.Call;

public class MockLoginApi implements RetrofitInterface {
    private final Context mContext;

    public MockLoginApi(Context context) {
        mContext=context;
    }

    @Override
    public Call<ResponseData<UserInformation>> login(LoginInfo loginInfo) {
        String validLogin=readRawValues(R.raw.valid_login);

        ResponseData responseData = new Gson().fromJson(validLogin,ResponseData.class);

        return (Call<ResponseData<UserInformation>>) responseData;

    }
    private String readRawValues(int value) {

        InputStream raw =mContext.getApplicationContext().getResources().openRawResource(value);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int i;
        try {
            i = raw.read();
            while (i != -1) {
                byteArrayOutputStream.write(i);
                i = raw.read();
            }
            raw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block

            e.printStackTrace();
        }


        return byteArrayOutputStream.toString();

    }
}
