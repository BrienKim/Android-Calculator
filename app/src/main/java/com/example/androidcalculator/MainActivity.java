package com.example.androidcalculator;

import android.view.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import database.FormulaDao;
import database.FormulaDatabase;
import function.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity
{
    private TabLayout tabs_layout;
    private ViewPager tabs;
    protected static FormulaDao formulaDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);;

        tabs_layout = findViewById(R.id.tab_layout);
        tabs_layout.setSelectedTabIndicatorColor(getResources().getColor(R.color.yellow_orange));
        tabs = findViewById(R.id.viewpager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CalculationFragment(), "Calculation");
        adapter.addFragment(new HistoryFragment(), "History");
        tabs.setAdapter(adapter);
        tabs_layout.setupWithViewPager(tabs);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));

        FormulaDatabase database;
        synchronized (FormulaDatabase.class)
        {
            database = Room.databaseBuilder(getApplicationContext(), FormulaDatabase.class, "Android Calculator")
                    .allowMainThreadQueries()         // allow IO (Input/Output) from Main Thread
                    .fallbackToDestructiveMigration() // allow to change a version for Schema (Database)
                    .build();
        }

        formulaDao = database.formulaDao();
    }
}