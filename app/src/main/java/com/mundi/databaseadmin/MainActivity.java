package com.mundi.databaseadmin;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mundi.databaseadmin.database.ListTables;
import com.mundi.databaseadmin.ui.main.PlaceholderFragment;
import com.mundi.databaseadmin.ui.main.SectionsPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private SectionsPagerAdapter sectionsPagerAdapter;
    private ViewPager viewPager;
    private String uri;
    private String dbname = "stocks";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uri = getString(R.string.url_dbweb);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_main);
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Handler mHandler = new Handler();
        mHandler.postDelayed(delay, 1000);
    }

    private Runnable delay = new Runnable() {
        @Override
        public void run() {
            Log.d(TAG, "uri = " + uri + " dbname = " + dbname);
            ListTables listTables = new ListTables(uri, dbname);
            ArrayList<String> tables = listTables.getTables();
            List<Fragment> mFragments = buildFragments(tables);
            initAdapter(mFragments, tables);
            return;
        }
    };

    private void initAdapter(List<Fragment> fragments, ArrayList<String> list) {
        sectionsPagerAdapter = new SectionsPagerAdapter(this,
                getSupportFragmentManager(), fragments, list);
        Log.d(TAG, "sectionsPagerAdapter count = " + sectionsPagerAdapter.getCount());
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
        return;
    }

    public void addNewFragment(String dbname, int i) {
        Bundle b = new Bundle();
        b.putInt("position", i);
        sectionsPagerAdapter.add(PlaceholderFragment.class, uri, dbname, dbname, i);
        sectionsPagerAdapter.notifyDataSetChanged();
        return;
    }

    private List<Fragment> buildFragments(ArrayList<String> list) {
        Log.d(TAG, "buildFragments list.size = " + list.size());
        List<Fragment> fragments = new ArrayList<Fragment>();
        for(int i = 0; i < list.size(); i++) {
            Bundle b = new Bundle();
            b.putInt("position", i);
            fragments.add(PlaceholderFragment.newInstance(i, uri, dbname, list.get(i)));
        }
        return fragments;
    }

}