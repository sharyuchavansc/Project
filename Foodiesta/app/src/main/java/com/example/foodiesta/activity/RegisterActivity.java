package com.example.foodiesta.activity;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodiesta.R;
import com.example.foodiesta.utils.Constants;
import com.example.foodiesta.utils.Utils;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;


public class RegisterActivity extends AppCompatActivity {

    EditText editEmail, editPassword, editName, editMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editEmail);
        editMobile = findViewById(R.id.editMobile);
        editPassword = findViewById(R.id.editPassword);

    }


    public void onRegister(View v) {
        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();
        String name = editName.getText().toString();
        String mobile = editMobile.getText().toString();

        if (name.length() == 0) {
            editName.setError("Name is mandatory");
        } else if (email.length() == 0) {
            editEmail.setError("Email is mandatory");
        } else if (password.length() == 0) {
            editPassword.setError("Password is mandatory");

        } else if (mobile.length() == 0) {
            editMobile.setError("Mobile No. is mandatory");

        } else {

            final String url = Utils.getUrl(Constants.PATH_USER + "/registration");

            Log.e("RegistrationActivity","url:"+url);
            final JsonObject body = new JsonObject();
            body.addProperty("emailId", email);
            body.addProperty("password", password);
            body.addProperty("userName", name);
            body.addProperty("mobileNo", mobile);

            Ion.with(this)
                    .load("POST", url)
                    .setJsonObjectBody(body)
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {
                            String status = result.get("status").getAsString();
                            if (status.equals("success")) {
                                Toast.makeText(RegisterActivity.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                String error = result.get("error").getAsString();
                                Toast.makeText(RegisterActivity.this, error, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }
    }

    public void onCancel(View v) {
        finish();
    }
}

