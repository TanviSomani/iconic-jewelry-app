package com.example.iconicjewelry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
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

public class ShowProductsActivity extends AppCompatActivity {

    TextView title;
    ImageView back;
    RecyclerView products_recycler_view;
    ArrayList<ProductModel> productModels;
    ProductsAdapter adapter;

    FirebaseDatabase database;

    FirebaseAuth mAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_products);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        String type = getIntent().getStringExtra("type");
        String product = getIntent().getStringExtra("product");

        products_recycler_view = findViewById(R.id.products_recycler_view);
        products_recycler_view.setHasFixedSize(true);
        products_recycler_view.setLayoutManager(new LinearLayoutManager(this));

        productModels = new ArrayList<ProductModel>();

        adapter = new ProductsAdapter(this, productModels, product, type, user);

        products_recycler_view.setAdapter(adapter);

        database = FirebaseDatabase.getInstance();
        DatabaseReference productRef = database.getReference().child("category/" + type + "/" + product);

        productRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ProductModel model = new ProductModel();
                    model.setImage(Objects.requireNonNull(dataSnapshot.child("image").getValue()).toString());
                    model.setName(Objects.requireNonNull(dataSnapshot.child("name").getValue()).toString());
                    model.setPrice(Objects.requireNonNull(dataSnapshot.child("price").getValue()).toString());
                    model.setId(Objects.requireNonNull(dataSnapshot.getRef().getKey()).toString());

                    productModels.add(model);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        title = findViewById(R.id.general_text);
        back = findViewById(R.id.btnBack);
        products_recycler_view = findViewById(R.id.products_recycler_view);

        title.setText(capitalizeString(product));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SelectProductTypeActivity.class);
                i.putExtra("type", type);
                startActivity(i);
            }
        });

    }

    private static String capitalizeString(String name) {
        String capitalizedString = "";
        if (!name.trim().equals("")) {
            capitalizedString = name.substring(0, 1).toUpperCase() + name.substring(1);
        }
        return capitalizedString;
    }

}