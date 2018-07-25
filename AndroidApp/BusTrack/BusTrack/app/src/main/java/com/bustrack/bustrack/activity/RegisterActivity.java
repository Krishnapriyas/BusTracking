package com.bustrack.bustrack.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bustrack.bustrack.R;
import com.bustrack.bustrack.model.RegisterModel;
import com.bustrack.bustrack.webservice.MyRetrofitClient;
import com.bustrack.bustrack.webservice.WebServiceAPI;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    EditText nameRegisterActivity;
    EditText emailRegisterActivity;
    EditText mobileNumberRegisterActivity;
    EditText passwordRegisterActivity;
    EditText reenterPasswordRegisterActivity;
    boolean flag;
    private AlertDialog progressDialog;

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new SpotsDialog(this, R.style.Custom);
        setTitle("Register");
        setContentView(R.layout.activity_register);
        nameRegisterActivity = findViewById(R.id.nameRegisterActivity);
        emailRegisterActivity = findViewById(R.id.emailRegisterActivity);
        mobileNumberRegisterActivity = findViewById(R.id.mobileNumberRegisterActivity);
        passwordRegisterActivity = findViewById(R.id.passwordRegisterActivity);
        reenterPasswordRegisterActivity = findViewById(R.id.reenterPasswordRegisterActivity);
        findViewById(R.id.registerButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.registerButton) {
            flag = true;
            if (nameRegisterActivity.getText().toString().equals("")) {
                nameRegisterActivity.setError("Required");
                flag = false;
            }
            if (mobileNumberRegisterActivity.getText().toString().equals("")) {
                mobileNumberRegisterActivity.setError("Required");
                flag = false;
            }
            if (passwordRegisterActivity.getText().toString().equals("")) {
                passwordRegisterActivity.setError("Required");
                flag = false;
            }
            if (reenterPasswordRegisterActivity.getText().toString().equals("")) {
                reenterPasswordRegisterActivity.setError("Required");
                flag = false;
            }
            if (!reenterPasswordRegisterActivity.getText().toString().equals(passwordRegisterActivity.getText().toString())) {
                passwordRegisterActivity.setError("Didn't match");
                reenterPasswordRegisterActivity.setError("Didn't match");
                flag = false;
            }
            if (!isValidEmail(emailRegisterActivity.getText().toString())) {
                emailRegisterActivity.setError("Enter Valid Email");
                flag = false;
            }
            if (flag) {
                progressDialog.show();
                RegisterModel registerModel = new RegisterModel();
                registerModel.setName(nameRegisterActivity.getText().toString());
                registerModel.setEmail(emailRegisterActivity.getText().toString());
                registerModel.setMobile(mobileNumberRegisterActivity.getText().toString());
                registerModel.setPassword(passwordRegisterActivity.getText().toString());
                Retrofit retrofit = new MyRetrofitClient().getRetrofit();
                WebServiceAPI webServiceAPI = retrofit.create(WebServiceAPI.class);
                webServiceAPI.registerUser(registerModel).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        progressDialog.dismiss();
                        if (response.message().equals("OK")) {
                            if (response.body().equals("success")) {
                                Toast.makeText(RegisterActivity.this, "Registration Successfull", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                finish();
                            } else {
                                Toast.makeText(RegisterActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(RegisterActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        progressDialog.dismiss();
                        t.printStackTrace();
                    }
                });
            }
        }
    }
}
