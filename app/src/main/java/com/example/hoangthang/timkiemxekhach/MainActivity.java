package com.example.hoangthang.timkiemxekhach;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView mListView;
    private ChuyenXeListAdapter adapter;
    private List<ChuyenXe> mChuyenXeList ;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.listView);
        mChuyenXeList = new ArrayList<>();
        mChuyenXeList.add(new ChuyenXe("Phương trang",8,"11h30"));
        mChuyenXeList.add(new ChuyenXe("Hoàng long",9,"11h30"));
        mChuyenXeList.add(new ChuyenXe("Hải âu",8,"11h30"));
        adapter = new ChuyenXeListAdapter(getApplicationContext(),mChuyenXeList);
        mListView.setAdapter(adapter);
    }

}
