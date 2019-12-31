package com.mundi.databaseadmin;

import android.content.Intent;
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
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mundi.databaseadmin.database.ListTables;
import com.mundi.databaseadmin.database.TablesClass;
import com.mundi.databaseadmin.ui.main.PlaceholderFragment;
import com.mundi.databaseadmin.ui.main.SectionsPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int FUNC_LOGIN = 1;
    private static final int FUNC_SCANBARCODE = 2;

    private SectionsPagerAdapter sectionsPagerAdapter;
    private ViewPager viewPager;
    private String uri;
    private String dbname = "stocks";
    private Button mButtonNew;
    private Button mButtonSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uri = getString(R.string.url_dbweb);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_main);

        mButtonNew = findViewById(R.id.btn_new);
        mButtonSearch = findViewById(R.id.btn_search);

        mButtonNew.setOnClickListener(newListener);
        mButtonSearch.setOnClickListener(newListener);

        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent, FUNC_LOGIN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FUNC_LOGIN && resultCode == RESULT_OK) {
            Handler mHandler = new Handler();
            mHandler.postDelayed(delay, 1000);
        } else {
            if (requestCode == FUNC_SCANBARCODE && resultCode == RESULT_OK) {
                String barcode = data.getExtras().getString("barcode");
                Toast.makeText(this, "barcode = " + barcode, Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    private Runnable delay = new Runnable() {
        @Override
        public void run() {
            Log.d(TAG, "uri = " + uri + " dbname = " + dbname);
            ListTables listTables = new ListTables(uri, dbname);
            List<TablesClass> tables = listTables.getTablesClass();
            List<Fragment> mFragments = buildFragments(tables);
            initAdapter(mFragments, tables);
            return;
        }
    };

    private void startBarcodeScan() {
        Intent intent = new Intent(this, SimpleScannerActivity.class);
        startActivityForResult(intent, FUNC_SCANBARCODE);
    }

    private Button.OnClickListener newListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            int item = viewPager.getCurrentItem();
            if (v == mButtonNew) {
                int pos = viewPager.getCurrentItem();
                if (sectionsPagerAdapter.getTablename(pos).equals("stock_in")) {
                    startBarcodeScan();
                }
            } else if (v == mButtonSearch) {

            }
        }
    };

    private void initAdapter(List<Fragment> fragments, List<TablesClass> list) {
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

    public void addNewFragment(String dbname, TablesClass table, int i) {
        Bundle b = new Bundle();
        b.putInt("position", i);
        sectionsPagerAdapter.add(PlaceholderFragment.class, uri, dbname, table, i);
        sectionsPagerAdapter.notifyDataSetChanged();
        return;
    }

    private List<Fragment> buildFragments(List<TablesClass> list) {
        Log.d(TAG, "buildFragments list.size = " + list.size());
        List<Fragment> fragments = new ArrayList<Fragment>();
        for(int i = 0; i < list.size(); i++) {
            Bundle b = new Bundle();
            b.putInt("position", i);
            fragments.add(PlaceholderFragment.newInstance(i,
                    uri, dbname, list.get(i)));
        }
        return fragments;
    }

}