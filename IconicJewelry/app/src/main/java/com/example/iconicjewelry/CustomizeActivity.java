package com.example.iconicjewelry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CustomizeActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageView menu, close, login, cart, wishlist;
    TextView toolbar_title;

    ImageView neck1, neck2, neck3, neck4, ear1, ear2, ear3, ear4, ring1, ring2, ring3, ring4, brace1, brace2, brace3, brace4;
    ImageView gold, silver, rosegold;
    ImageView preview;

    String mainImage = "";

    DatabaseReference database;
    FirebaseAuth mAuth;

    FirebaseUser user;

    Button addToCart;

    String finalBase = "";
    String finalCurrentSource = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize);

        addToCart = findViewById(R.id.customizedCart);

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = FirebaseAuth.getInstance().getCurrentUser();

                if (user != null) {
                    database = FirebaseDatabase.getInstance().getReference().child("user").child(user.getUid()).child("customizedProducts");

                    String name = "Customized Product";
                    String price = "$399.00";
                    String baseProd = finalBase;
                    String currentSourceImage = finalCurrentSource;

                    CustomizedProductModel customProductModel = new CustomizedProductModel(baseProd, currentSourceImage, name, price);
                    database.push().setValue(customProductModel);

                    Toast.makeText(CustomizeActivity.this, "Product added to cart successfully", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Please sign in first.!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        preview = findViewById(R.id.previewImage);

        gold = findViewById(R.id.goldCust);
        silver = findViewById(R.id.silverCust);
        rosegold = findViewById(R.id.roseGoldCust);

        gold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (mainImage) {
                    case "mainNeck1":
                        maskImage(R.drawable.goldbase, R.drawable.necklace1);
                        finalCurrentSource = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/plainImages%2Fnecklace1.png?alt=media&token=48fe722b-f8d7-4159-bb4b-d7d21ac00889";
                        break;
                    case "mainNeck2":
                        maskImage(R.drawable.goldbase, R.drawable.necklace2);
                        finalCurrentSource = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/plainImages%2Fnecklace2.png?alt=media&token=901b1037-b6cf-41c0-85ee-b823a8dfc307";
                        break;
                    case "mainNeck3":
                        maskImage(R.drawable.goldbase, R.drawable.necklace3);
                        finalCurrentSource = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/plainImages%2Fnecklace3.png?alt=media&token=c193601e-5fe8-487e-bd2a-df43f5b356f4";
                        break;
                    case "mainNeck4":
                        maskImage(R.drawable.goldbase, R.drawable.necklace4);
                        finalCurrentSource = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/plainImages%2Fnecklace4.png?alt=media&token=ecdb7327-2724-4376-925a-d8bf3f526c2d";
                        break;
                    case "mainEar1":
                        maskImage(R.drawable.goldbase, R.drawable.earrings1);
                        finalCurrentSource = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/plainImages%2Fearrings1.png?alt=media&token=6d02e969-8010-4ab0-b63d-99a410d1641a";
                        break;
                    case "mainEar2":
                        maskImage(R.drawable.goldbase, R.drawable.earrings2);
                        finalCurrentSource = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/plainImages%2Fearrings2.png?alt=media&token=c9db621c-5028-463a-9773-33d1259f043e";
                        break;
                    case "mainEar3":
                        maskImage(R.drawable.goldbase, R.drawable.earrings3);
                        finalCurrentSource = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/plainImages%2Fearrings3.png?alt=media&token=e941ec3d-de96-4920-97a4-9cd1a13bc458";
                        break;
                    case "mainEar4":
                        maskImage(R.drawable.goldbase, R.drawable.earrings4);
                        finalCurrentSource = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/plainImages%2Fearrings4.png?alt=media&token=e84f620d-87ac-40a9-95fe-f42bae520067";
                        break;
                    case "mainRing1":
                        maskImage(R.drawable.goldbase, R.drawable.ring1);
                        finalCurrentSource = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/plainImages%2Fring1.png?alt=media&token=c9a4c533-d334-4b92-991f-898ae735bc85";
                        break;
                    case "mainRing2":
                        maskImage(R.drawable.goldbase, R.drawable.ring2);
                        finalCurrentSource = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/plainImages%2Fring2.png?alt=media&token=9f1c5ccc-91f9-4f14-aaab-20368129fe75";
                        break;
                    case "mainRing3":
                        maskImage(R.drawable.goldbase, R.drawable.ring3);
                        finalCurrentSource = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/plainImages%2Fring3.png?alt=media&token=8a2c6191-b14a-4469-a5a9-80544f934c8e";
                        break;
                    case "mainRing4":
                        maskImage(R.drawable.goldbase, R.drawable.ring4);
                        finalCurrentSource = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/plainImages%2Fring4.png?alt=media&token=f80ddfac-a507-4ea5-9d9c-ae15b8f07f70";
                        break;
                    case "mainBracelet1":
                        maskImage(R.drawable.goldbase, R.drawable.bracelet1);
                        finalCurrentSource = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/plainImages%2Fbracelet1.png?alt=media&token=a8df6c74-cd70-4655-996f-dc009bb2931a";
                        break;
                    case "mainBracelet2":
                        maskImage(R.drawable.goldbase, R.drawable.bracelet2);
                        finalCurrentSource = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/plainImages%2Fbracelet2.png?alt=media&token=63dd6b1e-a245-4b6f-aae8-bc9e91088354";
                        break;
                    case "mainBracelet3":
                        maskImage(R.drawable.goldbase, R.drawable.bracelet3);
                        finalCurrentSource = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/plainImages%2Fbracelet3.png?alt=media&token=ce5cda92-068f-4b9d-94c1-9394161f4c48";
                        break;
                    case "mainBracelet4":
                        maskImage(R.drawable.goldbase, R.drawable.bracelet4);
                        finalCurrentSource = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/plainImages%2Fbracelet4.png?alt=media&token=fbf3ccd9-5261-4bc3-86e3-d8e7758135c9";
                        break;
                }
                finalBase = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/baseProduct%2Fbp1.png?alt=media&token=f1fbc34f-80b8-4ed1-b050-8c248a988a7f";
            }
        });

        silver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (mainImage) {
                    case "mainNeck1":
                        maskImage(R.drawable.silverbase, R.drawable.necklace1);
                        finalCurrentSource = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/plainImages%2Fnecklace1.png?alt=media&token=48fe722b-f8d7-4159-bb4b-d7d21ac00889";
                        break;
                    case "mainNeck2":
                        maskImage(R.drawable.silverbase, R.drawable.necklace2);
                        finalCurrentSource = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/plainImages%2Fnecklace2.png?alt=media&token=901b1037-b6cf-41c0-85ee-b823a8dfc307";
                        break;
                    case "mainNeck3":
                        maskImage(R.drawable.silverbase, R.drawable.necklace3);
                        finalCurrentSource = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/plainImages%2Fnecklace3.png?alt=media&token=c193601e-5fe8-487e-bd2a-df43f5b356f4";
                        break;
                    case "mainNeck4":
                        maskImage(R.drawable.silverbase, R.drawable.necklace4);
                        finalCurrentSource = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/plainImages%2Fnecklace4.png?alt=media&token=ecdb7327-2724-4376-925a-d8bf3f526c2d";
                        break;
                    case "mainEar1":
                        maskImage(R.drawable.silverbase, R.drawable.earrings1);
                        finalCurrentSource = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/plainImages%2Fearrings1.png?alt=media&token=6d02e969-8010-4ab0-b63d-99a410d1641a";
                        break;
                    case "mainEar2":
                        maskImage(R.drawable.silverbase, R.drawable.earrings2);
                        finalCurrentSource = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/plainImages%2Fearrings2.png?alt=media&token=c9db621c-5028-463a-9773-33d1259f043e";
                        break;
                    case "mainEar3":
                        maskImage(R.drawable.silverbase, R.drawable.earrings3);
                        finalCurrentSource = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/plainImages%2Fearrings3.png?alt=media&token=e941ec3d-de96-4920-97a4-9cd1a13bc458";
                        break;
                    case "mainEar4":
                        maskImage(R.drawable.silverbase, R.drawable.earrings4);
                        finalCurrentSource = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/plainImages%2Fearrings4.png?alt=media&token=e84f620d-87ac-40a9-95fe-f42bae520067";
                        break;
                    case "mainRing1":
                        maskImage(R.drawable.silverbase, R.drawable.ring1);
                        finalCurrentSource = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/plainImages%2Fring1.png?alt=media&token=c9a4c533-d334-4b92-991f-898ae735bc85";
                        break;
                    case "mainRing2":
                        maskImage(R.drawable.silverbase, R.drawable.ring2);
                        finalCurrentSource = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/plainImages%2Fring2.png?alt=media&token=9f1c5ccc-91f9-4f14-aaab-20368129fe75";
                        break;
                    case "mainRing3":
                        maskImage(R.drawable.silverbase, R.drawable.ring3);
                        finalCurrentSource = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/plainImages%2Fring3.png?alt=media&token=8a2c6191-b14a-4469-a5a9-80544f934c8e";
                        break;
                    case "mainRing4":
                        maskImage(R.drawable.silverbase, R.drawable.ring4);
                        finalCurrentSource = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/plainImages%2Fring4.png?alt=media&token=f80ddfac-a507-4ea5-9d9c-ae15b8f07f70";
                        break;
                    case "mainBracelet1":
                        maskImage(R.drawable.silverbase, R.drawable.bracelet1);
                        finalCurrentSource = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/plainImages%2Fbracelet1.png?alt=media&token=a8df6c74-cd70-4655-996f-dc009bb2931a";
                        break;
                    case "mainBracelet2":
                        maskImage(R.drawable.silverbase, R.drawable.bracelet2);
                        finalCurrentSource = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/plainImages%2Fbracelet2.png?alt=media&token=63dd6b1e-a245-4b6f-aae8-bc9e91088354";
                        break;
                    case "mainBracelet3":
                        maskImage(R.drawable.silverbase, R.drawable.bracelet3);
                        finalCurrentSource = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/plainImages%2Fbracelet3.png?alt=media&token=ce5cda92-068f-4b9d-94c1-9394161f4c48";
                        break;
                    case "mainBracelet4":
                        maskImage(R.drawable.silverbase, R.drawable.bracelet4);
                        finalCurrentSource = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/plainImages%2Fbracelet4.png?alt=media&token=fbf3ccd9-5261-4bc3-86e3-d8e7758135c9";
                        break;
                }
                finalBase = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/baseProduct%2Fbp2.png?alt=media&token=60147981-1e53-45ec-ba42-d2e1b6c1448f";
            }
        });

        rosegold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (mainImage) {
                    case "mainNeck1":
                        maskImage(R.drawable.rosegoldbase, R.drawable.necklace1);
                        finalCurrentSource = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/plainImages%2Fnecklace1.png?alt=media&token=48fe722b-f8d7-4159-bb4b-d7d21ac00889";
                        break;
                    case "mainNeck2":
                        maskImage(R.drawable.rosegoldbase, R.drawable.necklace2);
                        finalCurrentSource = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/plainImages%2Fnecklace2.png?alt=media&token=901b1037-b6cf-41c0-85ee-b823a8dfc307";
                        break;
                    case "mainNeck3":
                        maskImage(R.drawable.rosegoldbase, R.drawable.necklace3);
                        finalCurrentSource = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/plainImages%2Fnecklace3.png?alt=media&token=c193601e-5fe8-487e-bd2a-df43f5b356f4";
                        break;
                    case "mainNeck4":
                        maskImage(R.drawable.rosegoldbase, R.drawable.necklace4);
                        finalCurrentSource = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/plainImages%2Fnecklace4.png?alt=media&token=ecdb7327-2724-4376-925a-d8bf3f526c2d";
                        break;
                    case "mainEar1":
                        maskImage(R.drawable.rosegoldbase, R.drawable.earrings1);
                        finalCurrentSource = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/plainImages%2Fearrings1.png?alt=media&token=6d02e969-8010-4ab0-b63d-99a410d1641a";
                        break;
                    case "mainEar2":
                        maskImage(R.drawable.rosegoldbase, R.drawable.earrings2);
                        finalCurrentSource = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/plainImages%2Fearrings2.png?alt=media&token=c9db621c-5028-463a-9773-33d1259f043e";
                        break;
                    case "mainEar3":
                        maskImage(R.drawable.rosegoldbase, R.drawable.earrings3);
                        finalCurrentSource = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/plainImages%2Fearrings3.png?alt=media&token=e941ec3d-de96-4920-97a4-9cd1a13bc458";
                        break;
                    case "mainEar4":
                        maskImage(R.drawable.rosegoldbase, R.drawable.earrings4);
                        finalCurrentSource = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/plainImages%2Fearrings4.png?alt=media&token=e84f620d-87ac-40a9-95fe-f42bae520067";
                        break;
                    case "mainRing1":
                        maskImage(R.drawable.rosegoldbase, R.drawable.ring1);
                        finalCurrentSource = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/plainImages%2Fring1.png?alt=media&token=c9a4c533-d334-4b92-991f-898ae735bc85";
                        break;
                    case "mainRing2":
                        maskImage(R.drawable.rosegoldbase, R.drawable.ring2);
                        finalCurrentSource = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/plainImages%2Fring2.png?alt=media&token=9f1c5ccc-91f9-4f14-aaab-20368129fe75";
                        break;
                    case "mainRing3":
                        maskImage(R.drawable.rosegoldbase, R.drawable.ring3);
                        finalCurrentSource = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/plainImages%2Fring3.png?alt=media&token=8a2c6191-b14a-4469-a5a9-80544f934c8e";
                        break;
                    case "mainRing4":
                        maskImage(R.drawable.rosegoldbase, R.drawable.ring4);
                        finalCurrentSource = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/plainImages%2Fring4.png?alt=media&token=f80ddfac-a507-4ea5-9d9c-ae15b8f07f70";
                        break;
                    case "mainBracelet1":
                        maskImage(R.drawable.rosegoldbase, R.drawable.bracelet1);
                        finalCurrentSource = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/plainImages%2Fbracelet1.png?alt=media&token=a8df6c74-cd70-4655-996f-dc009bb2931a";
                        break;
                    case "mainBracelet2":
                        maskImage(R.drawable.rosegoldbase, R.drawable.bracelet2);
                        finalCurrentSource = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/plainImages%2Fbracelet2.png?alt=media&token=63dd6b1e-a245-4b6f-aae8-bc9e91088354";
                        break;
                    case "mainBracelet3":
                        maskImage(R.drawable.rosegoldbase, R.drawable.bracelet3);
                        finalCurrentSource = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/plainImages%2Fbracelet3.png?alt=media&token=ce5cda92-068f-4b9d-94c1-9394161f4c48";
                        break;
                    case "mainBracelet4":
                        maskImage(R.drawable.rosegoldbase, R.drawable.bracelet4);
                        finalCurrentSource = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/plainImages%2Fbracelet4.png?alt=media&token=fbf3ccd9-5261-4bc3-86e3-d8e7758135c9";
                        break;
                }
                finalBase = "https://firebasestorage.googleapis.com/v0/b/iconic-jewelry-3ae03.appspot.com/o/baseProduct%2Fbp3.png?alt=media&token=4f118741-0ab2-4aaf-b2bf-0e94b6c174d7";
            }
        });

        neck1 = findViewById(R.id.necklace1);
        neck2 = findViewById(R.id.necklace2);
        neck3 = findViewById(R.id.necklace3);
        neck4 = findViewById(R.id.necklace4);

        ear1 = findViewById(R.id.earring1);
        ear2 = findViewById(R.id.earring2);
        ear3 = findViewById(R.id.earring3);
        ear4 = findViewById(R.id.earring4);

        ring1 = findViewById(R.id.ring1);
        ring2 = findViewById(R.id.ring2);
        ring3 = findViewById(R.id.ring3);
        ring4 = findViewById(R.id.ring4);

        brace1 = findViewById(R.id.bracelet1);
        brace2 = findViewById(R.id.bracelet2);
        brace3 = findViewById(R.id.bracelet3);
        brace4 = findViewById(R.id.bracelet4);

        neck1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainImage = "mainNeck1";
                Glide.with(getApplicationContext()).load(R.drawable.necklace1).into(preview);
            }
        });

        neck2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainImage = "mainNeck2";
                Glide.with(getApplicationContext()).load(R.drawable.necklace2).into(preview);
            }
        });

        neck3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainImage = "mainNeck3";
                Glide.with(getApplicationContext()).load(R.drawable.necklace3).into(preview);
            }
        });

        neck4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainImage = "mainNeck4";
                Glide.with(getApplicationContext()).load(R.drawable.necklace4).into(preview);
            }
        });

        ear1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainImage = "mainEar1";
                Glide.with(getApplicationContext()).load(R.drawable.earrings1).into(preview);
            }
        });

        ear2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainImage = "mainEar2";
                Glide.with(getApplicationContext()).load(R.drawable.earrings2).into(preview);
            }
        });

        ear3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainImage = "mainEar3";
                Glide.with(getApplicationContext()).load(R.drawable.earrings3).into(preview);
            }
        });

        ear4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainImage = "mainEar4";
                Glide.with(getApplicationContext()).load(R.drawable.earrings4).into(preview);
            }
        });

        ring1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainImage = "mainRing1";
                Glide.with(getApplicationContext()).load(R.drawable.ring1).into(preview);
            }
        });

        ring2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainImage = "mainRing2";
                Glide.with(getApplicationContext()).load(R.drawable.ring2).into(preview);
            }
        });

        ring3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainImage = "mainRing3";
                Glide.with(getApplicationContext()).load(R.drawable.ring3).into(preview);
            }
        });

        ring4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainImage = "mainRing4";
                Glide.with(getApplicationContext()).load(R.drawable.ring4).into(preview);
            }
        });

        brace1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainImage = "mainBracelet1";
                Glide.with(getApplicationContext()).load(R.drawable.bracelet1).into(preview);
            }
        });

        brace2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainImage = "mainBracelet2";
                Glide.with(getApplicationContext()).load(R.drawable.bracelet2).into(preview);
            }
        });

        brace3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainImage = "mainBracelet3";
                Glide.with(getApplicationContext()).load(R.drawable.bracelet3).into(preview);
            }
        });

        brace4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainImage = "mainBracelet4";
                Glide.with(getApplicationContext()).load(R.drawable.bracelet4).into(preview);
            }
        });

        drawerLayout = findViewById(R.id.customize_drawer);
        menu = findViewById(R.id.btnMenu);
        close = findViewById(R.id.closeDrawer);
        toolbar_title = findViewById(R.id.textView);
        login = findViewById(R.id.btnLogin);

        toolbar_title.setText("Customize");

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

    public void maskImage(int over, int maskImage) {
        Bitmap original = BitmapFactory.decodeResource(getResources(), over);
        Bitmap mask = BitmapFactory.decodeResource(getResources(), maskImage);
        Bitmap result = Bitmap.createBitmap(mask.getWidth(), mask.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas mCanvas = new Canvas(result);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mCanvas.drawBitmap(original, 0, 0, null);
        mCanvas.drawBitmap(mask, 0, 0, paint);
        paint.setXfermode(null);
        preview.setImageBitmap(result);
        preview.setScaleType(ImageView.ScaleType.CENTER);
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

        closeDrawer(drawerLayout);
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
