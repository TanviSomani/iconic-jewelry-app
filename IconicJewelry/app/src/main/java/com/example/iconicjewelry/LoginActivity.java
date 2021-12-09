package com.example.iconicjewelry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 555;
    private static final String TAG = "abc";
    private static FirebaseAuth mAuth;
    SignInButton signInButton;
    GoogleSignInClient mGoogleSignInClient;

    ImageView user_image, backToHome;
    TextView user_name, user_email, register;
    Button signOut;

    TextInputEditText emailText, passText;
    Button signIn;

    String email = "";
    String pass = "";

    @SuppressLint("StaticFieldLeak")
    static LinearLayout before;
    @SuppressLint("StaticFieldLeak")
    static LinearLayout beforeGoogle;
    @SuppressLint("StaticFieldLeak")
    static LinearLayout after;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        register = findViewById(R.id.register_text);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
            }
        });

        emailText = findViewById(R.id.email);
        passText = findViewById(R.id.password);

        signIn = findViewById(R.id.btnSignIn);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = Objects.requireNonNull(emailText.getText()).toString();
                pass = Objects.requireNonNull(passText.getText()).toString();

                if (email.equals("") || pass.equals("")) {
                    Toast.makeText(LoginActivity.this, "Please enter your details.!", Toast.LENGTH_SHORT).show();
                } else {
                    login(getApplicationContext(), email, pass);
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

        mAuth = FirebaseAuth.getInstance();

        before = findViewById(R.id.beforeSignIn);
        beforeGoogle = findViewById(R.id.beforeSignInGoogle);
        after = findViewById(R.id.afterSignIn);

        user_image = findViewById(R.id.user_image);
        user_name = findViewById(R.id.user_name);
        user_email = findViewById(R.id.user_email);
        signOut = findViewById(R.id.btnSignOut);

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                before.setVisibility(View.VISIBLE);
                beforeGoogle.setVisibility(View.VISIBLE);
                after.setVisibility(View.INVISIBLE);
            }
        });

        signInButton = findViewById(R.id.googleSignIn);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }

    private static void login(Context context, String email, String pass) {

        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show();
                    before.setVisibility(View.INVISIBLE);
                    beforeGoogle.setVisibility(View.INVISIBLE);
                    after.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            updateUI(null);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        if (user == null) {
            before.setVisibility(View.VISIBLE);
            beforeGoogle.setVisibility(View.VISIBLE);
            after.setVisibility(View.INVISIBLE);
            Toast.makeText(this, "No user found.!", Toast.LENGTH_SHORT).show();
        } else {
            before.setVisibility(View.INVISIBLE);
            beforeGoogle.setVisibility(View.INVISIBLE);
            after.setVisibility(View.VISIBLE);
            if (user.getPhotoUrl() == null) {
                Glide.with(getApplicationContext()).load(R.drawable.icon_login).into(user_image);
            } else {
                Glide.with(getApplicationContext()).load(user.getPhotoUrl()).into(user_image);
            }
            user_name.setText(user.getDisplayName());
            user_email.setText(user.getEmail());
        }

    }

}