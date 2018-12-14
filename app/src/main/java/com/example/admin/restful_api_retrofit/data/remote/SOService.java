package com.example.admin.restful_api_retrofit.data.remote;

import com.example.admin.restful_api_retrofit.data.model.SOAnswersRespone;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SOService {

    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
    Call<List<SOAnswersRespone>> getAnswers();

    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
    Call<List<SOAnswersRespone>> getAnswers(@Query("tagged") String tags);
}
