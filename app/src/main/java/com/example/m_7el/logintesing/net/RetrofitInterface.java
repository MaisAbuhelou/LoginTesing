package com.example.m_7el.logintesing.net;


import com.example.m_7el.logintesing.modules.ResponseData;
import com.example.m_7el.logintesing.modules.LoginInfo;
import com.example.m_7el.logintesing.modules.UserInformation;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;

public interface RetrofitInterface {

    @PUT("api/v1/login")
     Call<ResponseData<UserInformation>> login(@Body LoginInfo loginInfo);


}
