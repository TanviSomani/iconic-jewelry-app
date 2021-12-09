package com.example.iconicjewelry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductsActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageView menu, close, login, cart, wishlist;
    TextView toolbar_title;
    CardView diamond, silver, gold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        drawerLayout = findViewById(R.id.products_drawer);
        menu = findViewById(R.id.btnMenu);
        close = findViewById(R.id.closeDrawer);
        toolbar_title = findViewById(R.id.textView);
        login = findViewById(R.id.btnLogin);

        cart = findViewById(R.id.btnCart);
        wishlist = findViewById(R.id.btnWishlist);

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(i);
            }
        });

        wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), WishlistActivity.class);
                startActivity(i);
            }
        });

        diamond = findViewById(R.id.diamondCard);
        silver = findViewById(R.id.silverCard);
        gold = findViewById(R.id.goldCard);

        setListener(diamond, "diamond");
        setListener(silver, "silver");
        setListener(gold, "gold");

        toolbar_title.setText("Products");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeDrawer(drawerLayout);
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer(drawerLayout);
            }
        });

    }

    public void setListener(View view, String type) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SelectProductTypeActivity.class);
                i.putExtra("type", type);
                startActivity(i);
            }
        });
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void goToHome(View view) {
        Intent i = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(i);
    }

    public void goToProducts(View view) {
        closeDrawer(drawerLayout);
    }

    public void goToCustomize(View view) {
        Intent i = new Intent(getApplicationContext(), CustomizeActivity.class);
        startActivity(i);
    }

    public void goToAbout(View view) {
        Intent i = new Intent(getApplicationContext(), AboutActivity.class);
        startActivity(i);
    }

    public void goToContact(View view) {
        Intent i = new Intent(getApplicationContext(), ContactActivity.class);
        startActivity(i);
    }

    public void goToReturn(View view) {
        Intent i = new Intent(getApplicationContext(), ReturnsActivity.class);
        startActivity(i);
    }

    public void goToTermsOfUse(View view) {
        Intent i = new Intent(getApplicationContext(), TermsOfUseActivity.class);
        startActivity(i);
    }

    public void goToPrivacyPolicy(View view) {
        Intent i = new Intent(getApplicationContext(), PrivacyPolicyActivity.class);
        startActivity(i);
    }

}