package com.bustrack.bustrack.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.bustrack.bustrack.R;
import com.bustrack.bustrack.adapter.BusListAdapter;
import com.bustrack.bustrack.model.BusDetailsModel;
import com.bustrack.bustrack.webservice.MyRetrofitClient;
import com.bustrack.bustrack.webservice.WebServiceAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BusListActivity extends AppCompatActivity {
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;
    String source;
    String destination;
    List<BusDetailsModel> busDetailsModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        source = getIntent().getStringExtra("source");
        destination = getIntent().getStringExtra("destination");
        retrofit = new MyRetrofitClient().getRetrofit();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
        webServiceAPI.getBusDetails(source, destination).enqueue(new Callback<List<BusDetailsModel>>() {
            @Override
            public void onResponse(Call<List<BusDetailsModel>> call, Response<List<BusDetailsModel>> response) {
                if (response.message().equals("OK")) {
                    busDetailsModels = response.body();
                    setContentView(R.layout.activity_bus_list);
                    ListView listView = findViewById(R.id.busListView);
                    BusListAdapter busListAdapter = new BusListAdapter(BusListActivity.this, busDetailsModels);
                    listView.setAdapter(busListAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<BusDetailsModel>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
