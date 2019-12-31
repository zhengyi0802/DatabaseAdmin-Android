package com.mundi.databaseadmin.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.mundi.databaseadmin.R;
import com.mundi.databaseadmin.bean.CellData;
import com.mundi.databaseadmin.bean.ColumnTitle;
import com.mundi.databaseadmin.bean.RowTitle;
import com.mundi.databaseadmin.database.TablesClass;
import com.mundi.databaseadmin.viewmodel.UserTablesViewModel;

import java.util.ArrayList;
import java.util.List;

import cn.zhouchaoyuan.excelpanel.ExcelPanel;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment implements ExcelPanel.OnLoadMoreListener {

    private static final String TAG = "PlaceholderFragment";

    private final static int INIT_ROWS = 100;
    private final static int INIT_COLUMNS = 27;
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_SECTION_DBNAME = "section_dbanem";
    private static final String ARG_SECTION_TABLENAME = "section_tableanem";
    private PageViewModel pageViewModel;
    private ExcelPanel mExcelPanel;
    private ProgressBar mProgress;
    private SheetDataAdapter mAdapter;
    private UserTablesViewModel mUserTablesViewModel;
    private String weburl;
    private String dbname;
    private String tablename;
    private int index;

    public static PlaceholderFragment newInstance(int index, String uri, String dbname,
                                                  TablesClass tablename) {
        Log.d(TAG, "newInstance index = " + index);
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        bundle.putString(ARG_SECTION_DBNAME, dbname);
        bundle.putString(ARG_SECTION_TABLENAME, tablename.getTablename());
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);

*/
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        index = getArguments().getInt(ARG_SECTION_NUMBER);
        dbname = getArguments().getString(ARG_SECTION_DBNAME);
        tablename = getArguments().getString(ARG_SECTION_TABLENAME);
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        //mProgress = root.findViewById(R.id.progress);
        mExcelPanel = root.findViewById(R.id.content_container);
        mAdapter = new SheetDataAdapter(getActivity(), blockListener);
        mExcelPanel.setAdapter(mAdapter);
        mExcelPanel.setOnLoadMoreListener(this);
        mExcelPanel.addOnScrollListener(onScrollListener);
        weburl = getString(R.string.url_dbweb);

        mUserTablesViewModel =
                ViewModelProviders.of(this).get(UserTablesViewModel.class);

        mUserTablesViewModel.getfromRemote(weburl, dbname, tablename);

        mUserTablesViewModel.getRowData().observe(this,
                new Observer<ArrayList<RowTitle>>() {
                    @Override
                    public void onChanged(ArrayList<RowTitle> rowTitles) {
                        Log.d(TAG,"getRowData()");
                        mAdapter.setLeftData(rowTitles);
                    }
                });

        mUserTablesViewModel.getColumnData().observe(this,
                new Observer<ArrayList<ColumnTitle>>() {
                    @Override
                    public void onChanged(ArrayList<ColumnTitle> columnTitles) {
                        Log.d(TAG,"getColumnData()");
                        mAdapter.setTopData(columnTitles);
                    }
                });

        mUserTablesViewModel.getCellData().observe(this,
                new Observer<List<List<CellData>>>() {
                    @Override
                    public void onChanged(List<List<CellData>> cellData) {
                        Log.d(TAG,"getCellData()");
                        mAdapter.setMajorData(cellData);
                        //mProgress.setVisibility(View.GONE);
                        mAdapter.enableFooter();
                        mAdapter.enableHeader();
                    }

                });
        return root;
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onLoadHistory() {

    }

    private ExcelPanel.OnScrollListener onScrollListener = new ExcelPanel.OnScrollListener() {
        @Override
        public void onScrolled(ExcelPanel excelPanel, int dx, int dy) {
            super.onScrolled(excelPanel, dx, dy);
            Log.d("acjiji", dx + "     " + dy);
        }
    };

    private View.OnClickListener blockListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.d(TAG, "blockListener");
            CellData mCellData =  (CellData) view.getTag();
        }
    };

}