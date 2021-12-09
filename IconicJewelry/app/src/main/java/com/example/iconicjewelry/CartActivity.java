package com.example.iconicjewelry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class CartActivity extends AppCompatActivity {

    TextView title;
    ImageView back;
    Button checkout;

    FirebaseUser user;
    FirebaseDatabase database;
    FirebaseAuth mAuth;

    ArrayList<CustomProductModel> cartList;
    ArrayList<CustomizedProductModel> newCartList;

    RecyclerView cartRecycler;

    CustomProductAdapter adapter;

    String key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        checkout = findViewById(R.id.btnCheckout);

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newI = new Intent(getApplicationContext(), CheckoutActivity.class);
                startActivity(newI);
            }
        });

        cartRecycler = findViewById(R.id.cartRecyclerView);
        cartRecycler.setHasFixedSize(true);
        cartRecycler.setLayoutManager(new LinearLayoutManager(this));

        cartList = new ArrayList<>();

        adapter = new CustomProductAdapter(this, cartList, "");

        cartRecycler.setAdapter(adapter);

        back = findViewById(R.id.btnBack);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(i);
            }
        });

        title = findViewById(R.id.general_text);

        title.setText("Cart");

        database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference().child("user");

        if (user != null) {
            ref = ref.child(user.getUid()).child("cart");

            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        CustomProductModel model = new CustomProductModel();
                        model.setKey(dataSnapshot.getKey());
                        model.setProductImage(Objects.requireNonNull(dataSnapshot.child("productImage").getValue()).toString());
                        model.setProductName(Objects.requireNonNull(dataSnapshot.child("productName").getValue()).toString());
                        model.setProductPrice(Objects.requireNonNull(dataSnapshot.child("productPrice").getValue()).toString());

                        cartList.add(model);
                    }
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                }
            });
        } else {
            Toast.makeText(this, "Please sign in first.!", Toast.LENGTH_SHORT).show();
        }

        DatabaseReference newRef = database.getReference().child("user");

        if (user != null) {
            newRef = newRef.child(user.getUid()).child("customizedProducts");

            newRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        CustomizedProductModel model = new CustomizedProductModel();
                        model.setKey(dataSnapshot.getKey());
                        model.setBaseProd(Objects.requireNonNull(dataSnapshot.child("baseProd").getValue()).toString());
                        model.setCurrentSourceImage(Objects.requireNonNull(dataSnapshot.child("currentSourceImage").getValue()).toString());
                        model.setProductName(Objects.requireNonNull(dataSnapshot.child("productName").getValue()).toString());
                        model.setProductPrice(Objects.requireNonNull(dataSnapshot.child("productPrice").getValue()).toString());

                        newCartList.add(model);
                    }
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                }
            });
        } else {
            Toast.makeText(this, "Please sign in first.!", Toast.LENGTH_SHORT).show();
        }

    }

}