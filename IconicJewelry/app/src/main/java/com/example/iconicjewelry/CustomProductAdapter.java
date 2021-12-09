package com.example.iconicjewelry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CustomProductAdapter extends RecyclerView.Adapter<CustomProductAdapter.CustomProductViewHolder> {

    Context context;
    ArrayList<CustomProductModel> fullCart;
    String c = "";

    DatabaseReference db;
    FirebaseAuth mAuth;
    FirebaseUser user;

    public CustomProductAdapter(Context context, ArrayList<CustomProductModel> fullCart, String c) {
        this.context = context;
        this.fullCart = fullCart;
        this.c = c;
    }

    @NonNull
    @NotNull
    @Override
    public CustomProductAdapter.CustomProductViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.custom_layout, parent, false);
        return new CustomProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CustomProductAdapter.CustomProductViewHolder holder, int position) {

        Glide.with(context).load(fullCart.get(position).getProductImage()).into(holder.image);

        holder.name.setText(fullCart.get(position).getProductName());
        holder.price.setText(fullCart.get(position).getProductPrice());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAuth = FirebaseAuth.getInstance();
                user = mAuth.getCurrentUser();

                if (user != null) {
                    db = FirebaseDatabase.getInstance().getReference().child("user").child(user.getUid()).child("cart").child(fullCart.get(position).getKey());
                    db.setValue(null);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return fullCart.size();
    }

    public static class CustomProductViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name, price;
        Button delete;

        public CustomProductViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.product_image);
            name = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.product_price);

            delete = itemView.findViewById(R.id.deleteProduct);

        }
    }
}
