package com.example.iconicjewelry;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder> {

    Context context;
    ArrayList<ProductModel> allProducts;
    String product = "";
    String type = "";
    FirebaseUser user;

    ProductsAdapter(Context context, ArrayList<ProductModel> allProducts, String product, String type, FirebaseUser user) {
        this.context = context;
        this.allProducts = allProducts;
        this.product = product;
        this.type = type;
        this.user = user;
    }

    @NonNull
    @NotNull
    @Override
    public ProductsViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_product, parent, false);
        return new ProductsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ProductsViewHolder holder, int position) {

        Glide.with(context).load(allProducts.get(position).getImage()).into(holder.image);

        holder.name.setText(allProducts.get(position).getName());
        holder.price.setText(allProducts.get(position).getPrice());

        holder.cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (user != null) {
                    DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("user");

                    FirebaseAuth mAuth = FirebaseAuth.getInstance();

                    FirebaseUser currentUser = mAuth.getCurrentUser();

                    String i = allProducts.get(position).getImage();
                    String n = allProducts.get(position).getName();
                    String p = allProducts.get(position).getPrice();

                    if (currentUser != null) {

                        CustomProductModel newProduct = new CustomProductModel("", i, n, p);
                        db.child(currentUser.getUid()).child("cart").push().setValue(newProduct);

                        Toast.makeText(context, "Product Added to Cart.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(context, "Please sign in first.!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        holder.wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user != null) {
                    DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("user");

                    FirebaseAuth mAuth = FirebaseAuth.getInstance();

                    FirebaseUser currentUser = mAuth.getCurrentUser();

                    String i = allProducts.get(position).getImage();
                    String n = allProducts.get(position).getName();
                    String p = allProducts.get(position).getPrice();

                    if (currentUser != null) {

                        CustomProductModel newProduct = new CustomProductModel("", i, n, p);
                        db.child(currentUser.getUid()).child("wishlist").push().setValue(newProduct);

                        Toast.makeText(context, "Product Added to Wishlist.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(context, "Please sign in first.!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return allProducts.size();
    }

    public static class ProductsViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name, price;
        Button cart, wishlist;

        public ProductsViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.product_image);
            name = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.product_price);

            cart = itemView.findViewById(R.id.addToCart);
            wishlist = itemView.findViewById(R.id.addToWishlist);

        }
    }

}
