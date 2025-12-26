package com.example.finals;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finals.MainPkg.ViewPagerAdapter;
import com.google.firebase.auth.FirebaseAuth;



public class Login extends AppCompatActivity {

    EditText etemail, etPassword, etConfirmPassword;
    Button btnEnter, btnToggle;
    FirebaseAuth mAuth;

    int mode = 1; //Login

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        SharedPreferences sp = getSharedPreferences("login", MODE_PRIVATE);


        // Auto login
        if (sp.getBoolean("loggedIn", false)) {
            launchmain();
            return;
        }

        btnToggle.setOnClickListener(v -> {
            if (mode == 1) {
                mode = 2;
                btnToggle.setText("Register");
                etConfirmPassword.setVisibility(View.VISIBLE);
            } else {
                mode = 1;
                btnToggle.setText("Login");
                etConfirmPassword.setVisibility(View.GONE);
            }
        });

        btnEnter.setOnClickListener(v -> handleAuth());
    }
    private void handleAuth() {
        String email = etemail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        SharedPreferences sp = getSharedPreferences("login", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if (mode == 1) { // LOGIN

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            // Login success
                            editor.putBoolean("loggedIn", true);
                            editor.apply();
                            launchmain();
                        } else {
                            Toast.makeText(this,
                                    task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });

        } else { // REGISTER



            String confirm = etConfirmPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
                Toast.makeText(this, "All fields required", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirm)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            // User created successfully
                            editor.putString("email", email);
                            editor.putString("password", password);
                            editor.putBoolean("loggedIn", true);
                            editor.apply();

                            launchmain();
                        } else {
                            // Error
                            Toast.makeText(this,
                                    task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });



        }
    }

    public void launchmain(){

        Intent intent = new Intent(Login.this, Logout.class);
        startActivity(intent);
        finish();
    }

    public void init(){
        mAuth = FirebaseAuth.getInstance();

        etemail = findViewById(R.id.etemail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnEnter = findViewById(R.id.btnEnter);
        btnToggle = findViewById(R.id.btnToggle);

    }

}
