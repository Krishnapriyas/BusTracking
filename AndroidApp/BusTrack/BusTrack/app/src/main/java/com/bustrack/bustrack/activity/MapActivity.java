package com.bustrack.bustrack.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.bustrack.bustrack.R;
import com.bustrack.bustrack.model.LocationModel;
import com.bustrack.bustrack.webservice.MyRetrofitClient;
import com.bustrack.bustrack.webservice.WebServiceAPI;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    String busId;
    Marker marker;
    WebServiceAPI webServiceAPI;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        busId = getIntent().getStringExtra("bus_id");
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        }
        mMap.setMyLocationEnabled(true);
        Retrofit retrofit = new MyRetrofitClient().getRetrofit();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
        webServiceAPI.getLocation(busId).enqueue(new Callback<LocationModel>() {
            @Override
            public void onResponse(Call<LocationModel> call, Response<LocationModel> response) {
                if (response.message().equals("OK")) {
                    LocationModel locationModel = response.body();
                    if (locationModel != null) {
                        if (locationModel.getLat() != null) {
                            LatLng latLng = new LatLng(Double.parseDouble(locationModel.getLat()), Double.parseDouble(locationModel.getLng()));
                            marker = mMap.addMarker(new MarkerOptions().position(latLng).title("Here").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<LocationModel> call, Throwable t) {
                t.printStackTrace();
            }
        });

        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                webServiceAPI.getLocation(busId).enqueue(new Callback<LocationModel>() {
                    @Override
                    public void onResponse(Call<LocationModel> call, Response<LocationModel> response) {
                        if (response.message().equals("OK")) {
                            LocationModel locationModel = response.body();
                            if (locationModel != null) {
                                if (locationModel.getLat() != null) {
                                    LatLng latLng = new LatLng(Double.parseDouble(locationModel.getLat()), Double.parseDouble(locationModel.getLng()));
                                    if (marker != null) {
                                        marker.remove();
                                        marker = mMap.addMarker(new MarkerOptions().position(latLng).title("Here").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
                                    }
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<LocationModel> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        }, 0, 2000);
    }
}
