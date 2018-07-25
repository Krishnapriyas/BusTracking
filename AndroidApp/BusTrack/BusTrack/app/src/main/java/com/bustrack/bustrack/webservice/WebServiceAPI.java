package com.bustrack.bustrack.webservice;


import com.bustrack.bustrack.model.BusDetailsModel;
import com.bustrack.bustrack.model.LocationModel;
import com.bustrack.bustrack.model.LoginModel;
import com.bustrack.bustrack.model.RegisterModel;
import com.bustrack.bustrack.model.SourceModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface WebServiceAPI {
    @POST("LoginServlet")
    Call<LoginModel> loginUser(@Body LoginModel loginModel);

    @POST("RegisterServlet")
    Call<String> registerUser(@Body RegisterModel registerModel);

    @POST("GetStopsServlet")
    Call<List<SourceModel>> getSources();

    @POST("BusListServlet")
    Call<List<BusDetailsModel>> getBusDetails(@Query("source") String source, @Query("destination") String destination);

    @POST("BusTrackServlet")
    Call<LocationModel> getLocation(@Query("bus_id") String busId);

}
