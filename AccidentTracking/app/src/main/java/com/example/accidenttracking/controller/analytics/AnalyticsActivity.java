package com.example.accidenttracking.controller.analytics;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.accidenttracking.R;
import com.example.accidenttracking.controller.common.BottomNav;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class AnalyticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics);

        BottomNav.init(getWindow().getDecorView().getRootView(), this);
        setUpTabLayout();
    }

    private void setUpTabLayout() {
        SectionPagerAdapter sectionPagerAdapter = new SectionPagerAdapter(getSupportFragmentManager(), getLifecycle());
        ViewPager2 pager = findViewById(R.id.pager);

        pager.setAdapter(sectionPagerAdapter);
        TabLayout tabLayout = findViewById(R.id.tabs);
        new TabLayoutMediator(tabLayout, pager,
                ((tab, position) -> {
                    switch (position){
                        case 0:
                            tab.setText("Time");
                            break;
                        case 1:
                            tab.setText("Car");
                            break;
                        case 2:
                            tab.setText("Distribution");
                            break;
                    }
                })
        ).attach();
    }

    private static class SectionPagerAdapter extends FragmentStateAdapter {
        FragmentManager fragmentManager;
        public SectionPagerAdapter(FragmentManager fragmentManager, Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
            this.fragmentManager = fragmentManager;
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position){
                case 0:
                    return new TimeChartFragment();
                case 1:
                    return new VehicleTypeFragment();
                case 2:
                    return new DistributionFragment();
            }
            return new TimeChartFragment();
        }

        @Override
        public int getItemCount() {
            return 3;
        }
    }
}