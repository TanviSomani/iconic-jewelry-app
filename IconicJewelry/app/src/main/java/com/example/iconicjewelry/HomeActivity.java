package com.example.iconicjewelry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Slide;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageView menu, close, login, cart, wishlist;
    TextView toolbar_title;
    ImageSlider imageSlider;
    List<SlideModel> photos;
    Button goToCustomize;

    CardView necklace, earring, ring, bracelet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        goToCustomize = findViewById(R.id.goToCustomize);

        goToCustomize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CustomizeActivity.class);
                startActivity(i);
            }
        });

        necklace = findViewById(R.id.goToNecklace);
        earring = findViewById(R.id.goToEarrings);
        ring = findViewById(R.id.goToRings);
        bracelet = findViewById(R.id.goToBracelets);

        necklace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ProductsActivity.class);
                startActivity(i);
            }
        });

        earring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ProductsActivity.class);
                startActivity(i);
            }
        });

        ring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ProductsActivity.class);
                startActivity(i);
            }
        });

        bracelet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ProductsActivity.class);
                startActivity(i);
            }
        });

        drawerLayout = findViewById(R.id.home_drawer);
        menu = findViewById(R.id.btnMenu);
        close = findViewById(R.id.closeDrawer);
        cart = findViewById(R.id.btnCart);
        wishlist = findViewById(R.id.btnWishlist);
        toolbar_title = findViewById(R.id.textView);
        login = findViewById(R.id.btnLogin);
        imageSlider = findViewById(R.id.carousel);

        photos = new ArrayList<>();

        photos.add(new SlideModel(R.drawable.c1));
        photos.add(new SlideModel(R.drawable.c2));
        photos.add(new SlideModel(R.drawable.c3));

        imageSlider.setImageList(photos, true);

        toolbar_title.setText("Home");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });

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
        closeDrawer(drawerLayout);
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