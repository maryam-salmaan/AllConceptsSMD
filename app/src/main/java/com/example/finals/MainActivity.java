package com.example.finals;


import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.finals.Frag1ListView.Frag1;
import com.example.finals.Frag1ListView.ListData;
import com.example.finals.MainPkg.ViewPagerAdapter;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity implements Frag1.onItemClicked {

    TabLayout tl;
    ViewPager2 vp2;
    ViewPagerAdapter vp2Adapter;

    TextView tvname, tvage;
    ArrayList<ListData> ld;
    FragmentManager listfragmanager;


    @Override
    public void onItemClick(int pos) {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();

        TabLayoutMediator mediator= new TabLayoutMediator(tl, vp2, (tab, position) -> {

            switch (position){
                case 0:
                    tab.setText("Frag1");
                    BadgeDrawable bd = tab.getOrCreateBadge();
                    bd.setNumber(100);
                    bd.setMaxCharacterCount(3);
                    break;
                case 1:
                    tab.setText("Frag2");
                    break;
                case 2:
                    tab.setText("Firebase");
                    break;
                case 3:
                    tab.setText("FragVolley");
                    break;
                default:
                    tab.setText("Frag1");
            }

        });
        mediator.attach();

        vp2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                TabLayout.Tab tab = tl.getTabAt(position);

                BadgeDrawable bd = tab.getOrCreateBadge();
                bd.setNumber(0);
                bd.setVisible(false);

            }
        });


    }

    public void init(){
        tl= findViewById(R.id.tl);
        vp2 = findViewById(R.id.vp2);
        vp2Adapter= new ViewPagerAdapter(this);
        vp2.setAdapter(vp2Adapter);

        //for listview

        listfragmanager = getSupportFragmentManager();



    }
}