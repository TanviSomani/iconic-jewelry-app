package com.example.iconicjewelry;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

public class CheckoutActivity extends AppCompatActivity {

    String sender = "appliedresearch2021@gmail.com";
    String pass = "wnezhatihtntwtyv";

    TextView total, taxes, finalTotal;

    ImageView back;

    FirebaseAuth mAuth;
    FirebaseUser user;

    FirebaseDatabase database;
    DatabaseReference ref;

    double price = 0.0;
    double totalTaxes = 0.0;
    double totalPrice = 0.0;
    double finalAmount = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        back = findViewById(R.id.btnBack);

        back.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), CartActivity.class);
            startActivity(i);
        });

        database = FirebaseDatabase.getInstance();

        total = findViewById(R.id.itemTotal);
        taxes = findViewById(R.id.itemTaxes);
        finalTotal = findViewById(R.id.finalTotal);

        mAuth = FirebaseAuth.getInstance();

        user = mAuth.getCurrentUser();

        ref = database.getReference().child("user").child(user.getUid()).child("cart");

        if (user != null) {

            ref.addValueEventListener(new ValueEventListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        price = Double.parseDouble(Objects.requireNonNull(dataSnapshot.child("productPrice").getValue()).toString().replace("$", ""));
                        totalPrice = totalPrice + price;
                    }

                    totalTaxes = (totalPrice * 12.5) / 100;

                    totalPrice = (double) Math.round(totalPrice * 100) / 100;
                    totalTaxes = (double) Math.round(totalTaxes * 100) / 100;

                    total.setText("$" + totalPrice);
                    taxes.setText("$" + totalTaxes);

                    finalAmount = totalPrice + totalTaxes + 22.04;

                    finalAmount = (double) Math.round(finalAmount * 100) / 100;

                    finalTotal.setText("$" + finalAmount);

                    String order = "Item Total: $" + totalPrice + "\n" + "Taxes: $" + totalTaxes + "\n" + "Shipping: $22.04" + "\n" + "Final Amount: $" + finalAmount;
                    String thankYou = "Thank you for shopping with us. \nWe'll deliver your products within 4-7 business days once we receive the payment. \nTeam Iconic Jewelry.";

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
                        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(Objects.requireNonNull(user.getEmail())));
                        message.setSubject("Order confirmed.");
                        message.setText(order + "\n" + thankYou);
                        Transport.send(message);
                        Toast.makeText(CheckoutActivity.this, "Mail sent", Toast.LENGTH_SHORT).show();
                    } catch (MessagingException e) {
                        Toast.makeText(CheckoutActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
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