package com.bustrack.bustrack.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.bustrack.bustrack.R;
import com.bustrack.bustrack.model.SourceModel;
import com.bustrack.bustrack.webservice.MyRetrofitClient;
import com.bustrack.bustrack.webservice.WebServiceAPI;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    AutoCompleteTextView sourceTextView;
    AutoCompleteTextView destinationTextView;
    List<String> sources;
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;
    boolean flag;
    private AlertDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new SpotsDialog(this, R.style.Custom);
        progressDialog.show();
        retrofit = new MyRetrofitClient().getRetrofit();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
        webServiceAPI.getSources().enqueue(new Callback<List<SourceModel>>() {
            @Override
            public void onResponse(Call<List<SourceModel>> call, Response<List<SourceModel>> response) {
                progressDialog.dismiss();
                if (response.message().equals("OK")) {
                    List<SourceModel> sourceModels = response.body();
                    setContentView(R.layout.activity_home);
                    sourceTextView = findViewById(R.id.autoCompleteSource);
                    destinationTextView = findViewById(R.id.autoCompleteDestination);
                    sources = new ArrayList<String>();
                    for (SourceModel sourceModel : sourceModels) {
                        sources.add(sourceModel.getName());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(HomeActivity.this, android.R.layout.simple_dropdown_item_1line, sources);
                    sourceTextView.setAdapter(adapter);
                    destinationTextView.setAdapter(adapter);
                    findViewById(R.id.getBusButton).setOnClickListener(HomeActivity.this);
                } else {
                    Toast.makeText(HomeActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<SourceModel>> call, Throwable t) {
                t.printStackTrace();
                progressDialog.dismiss();
                Toast.makeText(HomeActivity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.getBusButton) {
            String source = sourceTextView.getText().toString();
            String destination = destinationTextView.getText().toString();
            flag = true;
            if (!sources.contains(source)) {
                sourceTextView.setError("No source found");
                flag = false;
            }
            if (!sources.contains(destination)) {
                destinationTextView.setError("No destination found");
                flag = false;
            }
            if (flag) {
                Intent intent = new Intent(this, BusListActivity.class);
                intent.putExtra("source", source);
                intent.putExtra("destination", destination);
                startActivity(intent);
            }
        }
    }
}
