package com.mundi.databaseadmin.ui.main;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.mundi.databaseadmin.database.TablesClass;

import java.util.ArrayList;
import java.util.List;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private final Context mContext;
    private static int pos = 0;
    private List<TablesClass> mList =  null;
    private List<Fragment> mFragments;

    public SectionsPagerAdapter(Context context, FragmentManager fm,
                                List<Fragment> fragments, List<TablesClass> titles) {
        super(fm);
        mContext = context;
        mFragments = fragments;
        mList = titles;
        return;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        //return PlaceholderFragment.newInstance(position + 1);
        return mFragments.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        setPos(position);
        return mList.get(position).getTitle();
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return mList.size();
    }

    public String getTablename(int pos) {
        return mList.get(pos).getTablename();
    }

    public static int getPos() {
        return pos;
    }

    public static void setPos(int pos) {
        SectionsPagerAdapter.pos = pos;
    }

    public void add(Class<PlaceholderFragment> c, String uri, String dbname,
                    TablesClass tablename, int i) {
        mFragments.add(PlaceholderFragment.newInstance(i, uri, dbname, tablename));
        mList.add(tablename);
    }

}