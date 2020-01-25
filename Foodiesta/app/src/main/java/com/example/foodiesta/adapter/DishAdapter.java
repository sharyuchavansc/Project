package com.example.foodiesta.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.foodiesta.R;
import com.example.foodiesta.model.Dish;
import com.example.foodiesta.utils.Utils;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.ViewHolder> {

    public interface ActionListener {
        void onClick(int position);
    }


    private final Context context;
    private final ArrayList<Dish> dishes;
    private final ActionListener listener;

    public DishAdapter(Context context, ArrayList<Dish> dishes, ActionListener listener) {
        this.context = context;
        this.dishes = dishes;
        this.listener = listener;
    }


    @NonNull
    @Override
    public DishAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.recyclerview_dish,null);
        return new DishAdapter.ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull DishAdapter.ViewHolder holder, final int position) {
        Dish dish = dishes.get(position);

        String url = Utils.createUrl(dish.getDishImage());

        Ion.with(this.context)
                .load(url)
                .withBitmap()
                .intoImageView(holder.imageView);

        holder.textName.setText(dish.getDishName());
        holder.textPrice.setText("₹"+dish.getDishPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                listener.onClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dishes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        View itemView;

        ImageView imageView;

        TextView textName, textPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.itemView = itemView;

            //imageView = itemView.findViewById(R.id.imageView);

            textName = itemView.findViewById(R.id.textName);
            //textPrice = itemView.findViewById(R.id.textPrice);
        }
    }

}


