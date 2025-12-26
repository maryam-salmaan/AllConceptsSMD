package com.example.finals.MainPkg;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.finals.Frag1ListView.Frag1;
import com.example.finals.Frag2Rv.Frag2;
import com.example.finals.firebaseRvCRUD.FirebaseRvCRUD;
import com.example.finals.FragVolley.fragvolly;

public class ViewPagerAdapter extends FragmentStateAdapter {


    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new Frag1();
            case 1:
                return new Frag2();
            case 2:
                return new FirebaseRvCRUD();
            case 3:
                return new fragvolly();
            default:
                return new Frag1();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
