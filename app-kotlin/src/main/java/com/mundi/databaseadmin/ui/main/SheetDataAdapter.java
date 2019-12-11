package com.mundi.databaseadmin.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.mundi.databaseadmin.R;
import com.mundi.databaseadmin.bean.CellData;
import com.mundi.databaseadmin.bean.ColumnTitle;
import com.mundi.databaseadmin.bean.RowTitle;

import cn.zhouchaoyuan.excelpanel.BaseExcelPanelAdapter;


public class SheetDataAdapter extends BaseExcelPanelAdapter<ColumnTitle, RowTitle, CellData> {

    private final static String TAG = "SheetDataAdapter";

    private Context context;
    private View.OnClickListener blockListener;

    public SheetDataAdapter(Context context, View.OnClickListener blockListener) {
        super(context);
        this.context = context;
        this.blockListener = blockListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateCellViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sheetdata, parent, false);
        CellHolder cellHolder = new CellHolder(layout);
        return cellHolder;
    }

    @Override
    public void onBindCellViewHolder(RecyclerView.ViewHolder holder, int verticalPosition, int horizontalPosition) {
        CellData mCellData = getMajorItem(verticalPosition, horizontalPosition);
        if (null == holder || !(holder instanceof CellHolder) || mCellData == null) {
            return;
        }
        CellHolder viewHolder = (CellHolder) holder;
        viewHolder.cellContainer.setTag(mCellData);
        viewHolder.mDataTextView.setText(mCellData.getData());
        viewHolder.cellContainer.setOnClickListener(blockListener);
        if ((verticalPosition % 2) == 0) {
            viewHolder.cellContainer
                    .setBackgroundColor(ContextCompat.getColor(context, R.color.white));
        } else {
            viewHolder.cellContainer
                    .setBackgroundColor(ContextCompat.getColor(context, R.color.staying));

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateTopViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.column_label, parent, false);
        TopHolder topHolder = new TopHolder(layout);
        return topHolder;
    }

    @Override
    public void onBindTopViewHolder(RecyclerView.ViewHolder holder, int position) {
        ColumnTitle mColumnTitle = getTopItem(position);
        if (null == holder || !(holder instanceof TopHolder) || mColumnTitle == null) {
            return;
        }
        TopHolder viewHolder = (TopHolder) holder;
        viewHolder.mTitle.setText(mColumnTitle.getTitle());
    }

    @Override
    public RecyclerView.ViewHolder onCreateLeftViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.row_label, parent, false);
        LeftHolder leftHolder = new LeftHolder(layout);
        return leftHolder;
    }

    @Override
    public void onBindLeftViewHolder(RecyclerView.ViewHolder holder, int position) {
        RowTitle mRowTitle = getLeftItem(position);
        if (null == holder || !(holder instanceof LeftHolder) || mRowTitle == null) {
            return;
        }
        LeftHolder viewHolder = (LeftHolder) holder;
        viewHolder.indexLabel.setText(mRowTitle.getRowTitle());
        ViewGroup.LayoutParams lp = viewHolder.root.getLayoutParams();
        viewHolder.root.setLayoutParams(lp);
    }

    @Override
    public View onCreateTopLeftView() {
        return LayoutInflater.from(context).inflate(R.layout.column_label, null);
    }

    private static class TopHolder extends RecyclerView.ViewHolder {

        public final TextView mTitle;

        public TopHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.column_lable);
        }
    }

    private static class LeftHolder extends RecyclerView.ViewHolder {

        public final TextView indexLabel;
        public final View root;

        public LeftHolder(View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.root);
            indexLabel = (TextView) itemView.findViewById(R.id.index_label);
        }
    }

    private static class CellHolder extends RecyclerView.ViewHolder {

        public final TextView mDataTextView;
        public final LinearLayout cellContainer;

        public CellHolder(View itemView) {
            super(itemView);
            mDataTextView = (TextView) itemView.findViewById(R.id.cell_text);
            cellContainer = (LinearLayout) itemView.findViewById(R.id.pms_cell_container);
        }
    }

}
