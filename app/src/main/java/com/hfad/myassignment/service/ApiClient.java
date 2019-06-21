package com.hfad.myassignment.service;

import com.hfad.myassignment.model.LoanData;
import com.hfad.myassignment.model.UserData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiClient {

    @POST("dev/search")
    Call<LoanData> getApiDetails(@Body UserData keyword, @Header("x-api-key") String header);
}
