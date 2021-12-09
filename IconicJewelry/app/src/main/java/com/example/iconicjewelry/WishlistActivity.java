package com.example.iconicjewelry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class WishlistActivity extends AppCompatActivity {

    TextView title;
    ImageView back;

    FirebaseUser user;
    FirebaseDatabase database;
    FirebaseAuth mAuth;

    ArrayList<CustomProductModel> wishList;

    RecyclerView wishListRecycler;

    CustomProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        wishListRecycler = findViewById(R.id.wishlistRecyclerView);
        wishListRecycler.setHasFixedSize(true);
        wishListRecycler.setLayoutManager(new LinearLayoutManager(this));

        wishList = new ArrayList<>();

        adapter = new CustomProductAdapter(this, wishList);

        wishListRecycler.setAdapter(adapter);

        mAuth = FirebaseAuth.getInstance();

        user = mAuth.getCurrentUser();

        back = findViewById(R.id.btnBack);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(i);
            }
        });

        title = findViewById(R.id.general_text);

        title.setText("Wishlist");

        database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference().child("user");

        if (user != null) {
            ref = ref.child(user.getUid()).child("wishlist");

            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        CustomProductModel model = new CustomProductModel();
                        model.setProductImage(Objects.requireNonNull(dataSnapshot.child("productImage").getValue()).toString());
                        model.setProductName(Objects.requireNonNull(dataSnapshot.child("productName").getValue()).toString());
                        model.setProductPrice(Objects.requireNonNull(dataSnapshot.child("productPrice").getValue()).toString());

                        wishList.add(model);
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