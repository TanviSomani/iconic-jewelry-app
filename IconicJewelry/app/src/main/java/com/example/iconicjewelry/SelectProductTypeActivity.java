package com.example.iconicjewelry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SelectProductTypeActivity extends AppCompatActivity {

    CardView necklace, earring, ring, bracelet, giftSet;
    ImageView back;
    String type = "";
    String product = "";
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_product_type);

        type = getIntent().getStringExtra("type");

        title = findViewById(R.id.general_text);

        title.setText(capitalizeString(type));

        back = findViewById(R.id.btnBack);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ProductsActivity.class);
                i.putExtra("type", type);
                startActivity(i);
            }
        });

        necklace = findViewById(R.id.necklaceCard);
        earring = findViewById(R.id.earringCard);
        ring = findViewById(R.id.ringCard);
        bracelet = findViewById(R.id.braceletCard);
        giftSet = findViewById(R.id.giftSetCard);

        setListener(necklace, "necklace");
        setListener(earring, "earrings");
        setListener(ring, "ring");
        setListener(bracelet, "bracelets");

        giftSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ShowGiftSetActivity.class);
                startActivity(i);
            }
        });

    }

    public void setListener(View view, String product) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ShowProductsActivity.class);
                i.putExtra("type", type);
                i.putExtra("product", product);
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