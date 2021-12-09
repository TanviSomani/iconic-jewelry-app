package com.example.iconicjewelry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.textfield.TextInputEditText;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ContactActivity extends FragmentActivity implements OnMapReadyCallback {

    String sender = "appliedresearch2021@gmail.com";
    String pass = "wnezhatihtntwtyv";

    GoogleMap map;

    DrawerLayout drawerLayout;
    ImageView menu, close, login, cart, wishlist;
    TextView toolbar_title;

    TextInputEditText name, email, msg;
    Button sendMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        name = findViewById(R.id.fullName);
        email = findViewById(R.id.fullEmail);
        msg = findViewById(R.id.fullMessage);
        sendMail = findViewById(R.id.btnSendMessage);

        sendMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Objects.requireNonNull(name.getText()).toString().equals("") || Objects.requireNonNull(email.getText()).toString().equals("") || Objects.requireNonNull(msg.getText()).toString().equals("")) {
                    Toast.makeText(ContactActivity.this, "Please fill all the details.!", Toast.LENGTH_SHORT).show();
                } else {
                    String n = name.getText().toString();
                    String e = email.getText().toString();
                    String m = msg.getText().toString();

                    Properties props = new Properties();
                    props.put("mail.smtp.auth", "true");
                    props.put("mail.smtp.starttls.enable", "true");
                    props.put("mail.smtp.host", "smtp.gmail.com");
                    props.put("mail.smtp.port", "587");

                    Session session = Session.getInstance(props, new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(sender, pass);
                        }
                    });

                    try {
                        Message message = new MimeMessage(session);
                        message.setFrom(new InternetAddress(sender));
                        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("appliedresearch2021@gmail.com"));
                        message.setSubject(n + " has sent you a message for inquiry");
                        message.setText(m);
                        Transport.send(message);
                        Toast.makeText(getApplicationContext(), "Mail sent", Toast.LENGTH_SHORT).show();
                    } catch (MessagingException ex) {
                        Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                    }

                }

            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        drawerLayout = findViewById(R.id.contact_drawer);
        menu = findViewById(R.id.btnMenu);
        close = findViewById(R.id.closeDrawer);
        toolbar_title = findViewById(R.id.textView);
        login = findViewById(R.id.btnLogin);

        toolbar_title.setText("Contact");

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
        Intent i = new Intent(getApplicationContext(), AboutActivity.class);
        startActivity(i);
    }

    public void goToContact(View view) {

        closeDrawer(drawerLayout);
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

    @Override
    public void onMapReady(@NonNull @NotNull GoogleMap googleMap) {

        map = googleMap;

        LatLng Douglas = new LatLng(49.202983, -122.913112);
        map.addMarker(new MarkerOptions().position(Douglas).title("Douglas College"));
        map.moveCamera(CameraUpdateFactory.newLatLng(Douglas));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(Douglas, 12.0f));
    }
}