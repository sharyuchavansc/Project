package com.example.foodiesta.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.foodiesta.R;
import com.example.foodiesta.adapter.RestaurentAdapter;
import com.example.foodiesta.model.Restaurent;
import com.example.foodiesta.utils.Constants;
import com.example.foodiesta.utils.Utils;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

public class RestaurentListActivity extends AppCompatActivity {

    TextView textRestaurentName,textRestaurentEmailId,textRestaurentMobile,textRestaurentAddress;
     RecyclerView recyclerView;
    RestaurentAdapter adapter;
    ArrayList<Restaurent> restaurents = new ArrayList<>();

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurent_list);

        textRestaurentName = findViewById(R.id.textName);
        textRestaurentAddress = findViewById(R.id.textAddress);
        textRestaurentEmailId = findViewById(R.id.textEmail);
        textRestaurentMobile = findViewById(R.id.textMobile);
        recyclerView = findViewById(R.id.recyclerView);

        adapter = new RestaurentAdapter(this,restaurents);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadRestaurents();
    }

    private void loadRestaurents() {
        restaurents.clear();

        String url = Utils.createUrl(Constants.ROUTE_RESTAURENT);
        Ion.with(this)
                .load("GET", url)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject response) {

                        String status = response.get("status").getAsString();
                        if (status.equals("success")) {

                            JsonArray array = response.get("data").getAsJsonArray();
                            for (int index = 0; index < array.size(); index++) {
                                JsonObject object = array.get(index).getAsJsonObject();

                                int id = object.get("restaurentId").getAsInt();
                                String name = object.get("restaurentName").getAsString();
                                String address = object.get("restaurentAddress").getAsString();
                                String emailId = object.get("restaurentEmailId").getAsString();
                                String mobile = object.get("restaurentMobileNo").getAsString();


                                restaurents.add(new Restaurent(id,name, address,emailId, mobile));
                            }

                            adapter.notifyDataSetChanged();
                        }

                    }

                });

    }

    public void onClick(int position)
    {
        Restaurent restaurent = restaurents.get(position);

        Intent intent = new Intent(this, RestaurentDishActivity.class);
        intent.putExtra("restaurent",restaurent);
        startActivity(intent);
    }
}


