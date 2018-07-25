package com.bustrack.driver.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bustrack.driver.R;
import com.bustrack.driver.model.LoginModel;
import com.bustrack.driver.webservice.MyRetrofitClient;
import com.bustrack.driver.webservice.WebServiceAPI;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText usernameEditText;
    EditText passwordEditText;
    boolean flag;
    WebServiceAPI webServiceAPI;
    private AlertDialog progressDialog;
    String busId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new SpotsDialog(this, R.style.Custom);
        setContentView(R.layout.activity_login);
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        findViewById(R.id.loginButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.loginButton) {
            flag = true;
            if (usernameEditText.getText().toString().equals("")) {
                usernameEditText.setError("Enter username");
                flag = false;
            }
            if (passwordEditText.getText().toString().equals("")) {
                passwordEditText.setError("Enter password");
                flag = false;
            }
            if (flag) {
                progressDialog.show();
                Retrofit retrofit = new MyRetrofitClient().getRetrofit();
                webServiceAPI = retrofit.create(WebServiceAPI.class);
                LoginModel loginModel = new LoginModel();
                loginModel.setUsername(usernameEditText.getText().toString());
                loginModel.setPassword(passwordEditText.getText().toString());
                webServiceAPI.loginUser(loginModel).enqueue(new Callback<LoginModel>() {
                    @Override
                    public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                        progressDialog.dismiss();
                        if (response.message().equals("OK")) {
                            LoginModel loginModel1 = response.body();
                            if (!loginModel1.getUser_id().equals("fail")) {
                                if (loginModel1.getType().equals("driver")) {
                                    SharedPreferences sharedPreferences = getSharedPreferences("mypref", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("user_id", loginModel1.getUser_id());
                                    editor.putString("bus_id", loginModel1.getBus_id());
                                    editor.putString("status", loginModel1.getStatus());
                                    editor.apply();
                                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                                }
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginModel> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }

    }
}
