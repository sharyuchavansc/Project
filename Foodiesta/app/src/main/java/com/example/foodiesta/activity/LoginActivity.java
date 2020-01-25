package com.example.foodiesta.activity;

import android.content.Intent;
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

public class LoginActivity extends AppCompatActivity {

    EditText editEmail, editPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
    }

   public void onLogin(View v) {
        String emailId = editEmail.getText().toString();
        String password = editPassword.getText().toString();

        if (emailId.length() == 0) {
            editEmail.setError("Email is mandatory");
        } else if (password.length() == 0) {
            editPassword.setError("Password is mandatory");
        } else {
            final String url = Utils.getUrl(Constants.PATH_USER + "/login");

            Log.d("loginactivity","url"+url);

            final JsonObject body = new JsonObject();
            body.addProperty("emailId", emailId);
            body.addProperty("password", password);



            Ion.with(this)
                    .load("POST",url)
                    .setJsonObjectBody(body)
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {
                            String status = result.get("status").getAsString();
                            if (status.equals("success")) {
                                Intent intent = new Intent(LoginActivity.this, RestaurentListActivity.class);
                                startActivity(intent);
                            } else {
                                String error = String.valueOf(result.get("error"));
                                Toast.makeText(LoginActivity.this, error, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }
    }
        public void onRegister(View v) {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
    }
}
