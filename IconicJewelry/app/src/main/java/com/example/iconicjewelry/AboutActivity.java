package com.example.iconicjewelry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageView menu, close, login, cart, wishlist;
    TextView toolbar_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        drawerLayout = findViewById(R.id.about_drawer);
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

        toolbar_title.setText("About");

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
        Intent i = new Intent(getApplicationContext(), ProductsActivity.class);
        startActivity(i);
    }

    public void goToCustomize(View view) {
        Intent i = new Intent(getApplicationContext(), CustomizeActivity.class);
        startActivity(i);
    }

    public void goToAbout(View view) {

        closeDrawer(drawerLayout);
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