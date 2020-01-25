package com.example.foodiesta.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.foodiesta.R;
import com.example.foodiesta.adapter.DishAdapter;
import com.example.foodiesta.adapter.RestaurentAdapter;
import com.example.foodiesta.model.Dish;
import com.example.foodiesta.utils.Constants;
import com.example.foodiesta.utils.Utils;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.sql.Array;
import java.util.ArrayList;

public class RestaurentDishActivity extends BaseActivity implements DishAdapter.ActionListener{


    RecyclerView recyclerView;
    DishAdapter adapter;
    ArrayList<Dish> dishes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurent_dish);

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new DishAdapter(this, dishes, this);
        recyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new RecyclerView.LayoutManager(this);

            recyclerView.setLayoutManager(layoutManager);
        }

    @Override
    protected void onResume() {
        super.onResume();
        loadDishes();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menuRefresh) {
            loadDishes();
        }else if(item.getItemId() == R.id.menuSignout) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            preferences.edit()
                    .putInt("userId", 0)
                    .putString("userName", "")
                    .putString("emailId", "")
                    .putBoolean("login_status", false)
                    .commit();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        else if(item.getItemId() == R.id.menuCart) {
            Intent intent = new Intent(this, RestaurentListActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadDishes() {
        dishes.clear();
        String url = Utils.createUrl(Constants.ROUTE_DISH);

        Ion.with(this)
                .load("GET", url)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject response) {

                        String status = response.get("status").getAsString();
                        if(status.equals("success"))
                        {

                            JsonArray array = response.get("data").getAsJsonArray();
                            for(int index = 0; index < array.size(); index++)
                            {
                                JsonObject object = array.get(index).getAsJsonObject();

                                int id = object.get("dishId").getAsInt();
                                String name = object.get("dishName").getAsString();
                                String image = object.get("dishImage").getAsString();
                                String price = object.get("dishPrice").getAsString();
                                String description = object.get("dishDescription").getAsString();
                                int restaurentId = object.get("restaurentId").getAsInt();

                                dishes.add(new Dish(id, name, image, price, description, restaurentId));

                            }

                            adapter.notifyDataSetChanged();
                        }
                    }
                })  ;
    }

    @Override
    public void onClick(int position) {

    }
}


