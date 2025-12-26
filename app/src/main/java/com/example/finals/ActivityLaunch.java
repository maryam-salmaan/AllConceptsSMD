package com.example.finals;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ActivityLaunch extends AppCompatActivity {

    TextView tv1, tv2;
    Button btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_launch);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        btnback= findViewById(R.id.btnback);

        Intent i = getIntent();
        Bundle b = i.getBundleExtra("bundle");

        String name = b.getString("name");
        ArrayList<String> list = b.getStringArrayList("list");
        tv1.setText(name);
        tv2.setText(list.get(0) + " " + list.get(1));

        btnback.setOnClickListener(v->{


        Intent sendback = new Intent(this, Logout.class);
        sendback.putExtra("username", "maryammz");
        if (b != null)
        {
            setResult(ActivityLaunch.RESULT_OK, sendback);

        }
        else {
            tv1.setText("No data");
            setResult(ActivityLaunch.RESULT_CANCELED, sendback);
        }


        finish();
        });
    }



};