package com.example.foodiesta.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.foodiesta.R;
import com.example.foodiesta.model.Restaurent;
import com.example.foodiesta.utils.Utils;

import java.util.ArrayList;

public class RestaurentAdapter extends RecyclerView.Adapter<RestaurentAdapter.ViewHolder> {


    private final Context context;
    private final ArrayList<Restaurent> restaurents;

    public RestaurentAdapter(Context context, ArrayList<Restaurent> restaurents)
    {
        this.context = context;
        this.restaurents = restaurents;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.recyclerview_restaurent, null);
        return new RestaurentAdapter.ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurentAdapter.ViewHolder holder, final int position) {
        Restaurent restaurent = restaurents.get(position);

        holder.textName.setText(restaurent.getRestaurentName());
        holder.textAddress.setText(restaurent.getRestaurentAddress());
    }

    @Override
    public int getItemCount() {
        return restaurents.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        View itemView;

        TextView textName, textAddress;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;

            textName = itemView.findViewById(R.id.textName);
            textAddress = itemView.findViewById(R.id.textAddress);
        }
    }
}
