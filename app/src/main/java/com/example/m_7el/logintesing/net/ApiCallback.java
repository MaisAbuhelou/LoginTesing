package com.example.m_7el.logintesing.net;

public interface ApiCallback<Response, Error> {
    void onSuccess(Response response);
    void onError(Error error);
}
