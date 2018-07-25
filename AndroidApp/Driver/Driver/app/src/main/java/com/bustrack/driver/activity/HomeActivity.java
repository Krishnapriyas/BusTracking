package com.bustrack.driver.activity;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bustrack.driver.R;
import com.bustrack.driver.webservice.MyRetrofitClient;
import com.bustrack.driver.webservice.WebServiceAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeActivity extends AppCompatActivity implements LocationListener, View.OnClickListener {
    LocationManager locationManager;
    boolean flag = true;
    Button button;
    String busId;
    String status;
    Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        SharedPreferences sharedPreferences = getSharedPreferences("mypref", MODE_PRIVATE);
        busId = sharedPreferences.getString("bus_id", "");
        status = sharedPreferences.getString("status", "");
        button = findViewById(R.id.startButton);
        if (status.equals("start")) {
            button.setText("Stop");
            flag = false;
        } else {
            button.setText("Start");
            flag = true;
        }
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            }
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        button.setOnClickListener(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        this.location = location;
        Toast.makeText(this, location.getLatitude() + "," + location.getLatitude(), Toast.LENGTH_SHORT).show();
        Retrofit retrofit = new MyRetrofitClient().getRetrofit();
        WebServiceAPI webServiceAPI = retrofit.create(WebServiceAPI.class);
        webServiceAPI.sendLocation(busId, "start", location.getLatitude() + "", location.getLongitude() + "").enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.message().equals("OK")) {
                    Log.e("location", " updated");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.startButton) {
            if (flag) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                Retrofit retrofit = new MyRetrofitClient().getRetrofit();
                WebServiceAPI webServiceAPI = retrofit.create(WebServiceAPI.class);
                webServiceAPI.sendLocation(busId, "start", null, null).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.message().equals("OK")) {
                            Log.e("location", " updated");
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
                button.setText("STOP");
                flag = false;
            } else {
                locationManager.removeUpdates(this);
                button.setText("START");
                flag = true;
                Retrofit retrofit = new MyRetrofitClient().getRetrofit();
                WebServiceAPI webServiceAPI = retrofit.create(WebServiceAPI.class);
                webServiceAPI.sendLocation(busId, "stop", null, null).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.message().equals("OK")) {
                            Log.e("location", " updated");
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        }
    }
}
