package com.example.iconicjewelry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    ImageView backToHome;
    TextInputEditText first, last, emailText, passText, aptText, bldgText, streetText, cityText, provinceText, postalCodeText;
    Button register;

    String firstText = "";
    String lastText = "";
    String email = "";
    String pass = "";
    String apt = "";
    String bldg = "";
    String street = "";
    String city = "";
    String province = "";
    String postalCode = "";

    DatabaseReference db;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = FirebaseDatabase.getInstance().getReference().child("user");

        mAuth = FirebaseAuth.getInstance();

        first = findViewById(R.id.fName);
        last = findViewById(R.id.lName);
        emailText = findViewById(R.id.email);
        passText = findViewById(R.id.password);
        aptText = findViewById(R.id.aptNumber);
        bldgText = findViewById(R.id.buildingNo);
        streetText = findViewById(R.id.streetName);
        cityText = findViewById(R.id.city);
        provinceText = findViewById(R.id.province);
        postalCodeText = findViewById(R.id.postalCode);

        register = findViewById(R.id.btnRegister);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firstText = Objects.requireNonNull(first.getText()).toString();
                lastText = Objects.requireNonNull(last.getText()).toString();
                email = Objects.requireNonNull(emailText.getText()).toString();
                pass = Objects.requireNonNull(passText.getText()).toString();
                apt = Objects.requireNonNull(aptText.getText()).toString();
                bldg = Objects.requireNonNull(bldgText.getText()).toString();
                street = Objects.requireNonNull(streetText.getText()).toString();
                city = Objects.requireNonNull(cityText.getText()).toString();
                province = Objects.requireNonNull(provinceText.getText()).toString();
                postalCode = Objects.requireNonNull(postalCodeText.getText()).toString();

                if (firstText.equals("") || lastText.equals("") || email.equals("") || pass.equals("") || apt.equals("") || bldg.equals("") || street.equals("") || city.equals("") || province.equals("") || postalCode.equals("")) {
                    Toast.makeText(RegisterActivity.this, "Please enter all the details.!", Toast.LENGTH_SHORT).show();
                } else {
                    register(getApplicationContext(), firstText, lastText, email, pass, apt, bldg, street, city, province, postalCode);
                }

            }
        });

        backToHome = findViewById(R.id.btnBack);

        backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(i);
            }
        });

    }

    private void register(Context applicationContext, String firstText, String lastText, String email, String pass, String apt, String bldg, String street, String city, String province, String postalCode) {

        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            UserModel newUser = new UserModel(firstText, lastText, email, apt, bldg, street, city, province, postalCode);
                            db.child(user.getUid()).setValue(newUser);
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(RegisterActivity.this, "Registration failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });

    }

    private void updateUI(FirebaseUser user) {

        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);

    }

}