package com.example.finals;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Logout extends AppCompatActivity {

    Button btnMaps, btnMessage, btnActivityForRes, btnGoogle, btnLogout;
    TextView tvIntent;
    ActivityResultLauncher<Intent> launcher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_logout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();

       launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK ) {
                        Toast.makeText(this, "I DONT KNOW", Toast.LENGTH_SHORT).show();

                        tvIntent.setText(result.getData().getStringExtra("username"));
                    }
                    else
                        tvIntent.setText("No data");
                    Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();

                }

        );

        mapsListener();
        messageListener();
        activityForResListener();
        googleListener();





        btnLogout.setOnClickListener(v -> {
            SharedPreferences sp = getSharedPreferences("login", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("loggedIn", false);
            editor.apply();
            Intent i = new Intent(Logout.this, Login.class);
            startActivity(i);
            finish();
        });

    }

    public void init(){
        tvIntent= findViewById(R.id.tv3);

        btnMaps = findViewById(R.id.btnMaps);
        btnMessage = findViewById(R.id.btnMessage);
        btnActivityForRes = findViewById(R.id.btnActivityForRes);
        btnGoogle = findViewById(R.id.btnGoogle);
        btnLogout = findViewById(R.id.btnLogout);

    }

    public void mapsListener() {
        btnMaps.setOnClickListener(v -> {
            Uri location = Uri.parse("geo:0,0?q=1600+Amphitheatre+Parkway,+Mountain+View,+California");
            Intent i = new Intent(Intent.ACTION_VIEW, location);
            startActivity(i);

        });
    }
    public void messageListener() {
        btnMessage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"maryam18salman@gmail.com"});
            intent.putExtra(Intent.EXTRA_SUBJECT, "Hello");
            intent.putExtra(Intent.EXTRA_TEXT, "How are you?");
            startActivity(intent);
        });

    }
    public void activityForResListener() {
        btnActivityForRes.setOnClickListener(v -> {

            Intent intent = new Intent(Logout.this, ActivityLaunch.class);

            Bundle sendBundle = new Bundle();
            sendBundle.putString("name", "Maryam");

            ArrayList<String> sendlist = new ArrayList<>();
            sendlist.add("Heyoooo");
            sendlist.add("How are you?");
            sendBundle.putStringArrayList("list", sendlist);

            intent.putExtra("bundle", sendBundle);

            launcher.launch(intent); // ✅ SAFE now
        });
    }

    public void googleListener() {

        btnGoogle.setOnClickListener(v->{
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.google.com"));

        startActivity(intent);

        });

    }
}