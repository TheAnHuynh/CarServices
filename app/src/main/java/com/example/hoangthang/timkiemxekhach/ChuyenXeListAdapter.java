package com.example.hoangthang.timkiemxekhach;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hoangthang on 3/18/18.
 */

public class ChuyenXeListAdapter extends BaseAdapter {
    private Context mContext;
    private List<ChuyenXe> mChuyenXeList;

    public ChuyenXeListAdapter(Context mContext, List<ChuyenXe> mChuyenXeList) {
        this.mContext = mContext;
        this.mChuyenXeList = mChuyenXeList;
    }

    @Override
    public int getCount() {
        return mChuyenXeList.size();
    }

    @Override
    public Object getItem(int i) {
        return mChuyenXeList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(mContext,R.layout.listketqua,null);
        TextView tvTenNhaXe = (TextView)v.findViewById(R.id.txtTenNhaXe);
        TextView tvSocho = (TextView)v.findViewById(R.id.txtLoaiXe);
        TextView tvTime= (TextView)v.findViewById(R.id.txtTime);
        tvTenNhaXe.setText(mChuyenXeList.get(i).getTennhaxe());
        tvSocho.setText(String.valueOf(mChuyenXeList.get(i).getSoCho()));
        tvTime.setText(mChuyenXeList.get(i).getTime());
        //v.setTag(mChuyenXeList.get(i).get);

        return v;

    }
}
