package com.bustrack.driver.webservice;






import com.bustrack.driver.model.LoginModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface WebServiceAPI {
    @POST("LoginServlet")
    Call<LoginModel> loginUser(@Body LoginModel loginModel);

    @POST("BusUpdateServlet")
    Call<String> sendLocation(@Query("bus_id") String busId, @Query("status") String status, @Query("lat") String lat, @Query("lng") String lng);




}
