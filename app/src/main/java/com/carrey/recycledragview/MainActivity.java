package com.carrey.recycledragview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements ServiceItemOPLinstener{

    private RecyclerView mMyservice;
    private RecyclerView mBaseservice;

    private MyServiceAdapter mMyServiceAdapter;
    private BaseServiceAdapter mBaseServiceAdapter;
    private TextView mEdit;
    private boolean isEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEdit = (TextView) findViewById(R.id.edit);
        mMyservice = (RecyclerView) findViewById(R.id.brv_my_service);
        mBaseservice = (RecyclerView) findViewById(R.id.brv_base_service);
        mMyservice.setLayoutManager(new GridLayoutManager(this, 4));
        mBaseservice.setLayoutManager(new GridLayoutManager(this, 4));


        mMyServiceAdapter = new MyServiceAdapter(this, initMyServiceData());
        mMyservice.setAdapter(mMyServiceAdapter);
        ItemTouchHelper.Callback simpleItemTouchHelperCallback = new SimpleItemTouchHelperCallback(mMyServiceAdapter);
        ItemTouchHelper helper = new ItemTouchHelper(simpleItemTouchHelperCallback);
        helper.attachToRecyclerView(mMyservice);

        mBaseServiceAdapter = new BaseServiceAdapter(this, initBaseServiceData());
        mBaseservice.setAdapter(mBaseServiceAdapter);
        mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isEdit = !isEdit;
                mEdit.setText(isEdit ? "完成" : "编辑");
                mMyServiceAdapter.setEdit(isEdit);
                mBaseServiceAdapter.setEdit(isEdit);
            }
        });

    }

    private List<ServiceEntity> initMyServiceData() {
        List<ServiceEntity> list = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            list.add(new ServiceEntity("功能" + i,R.mipmap.ic_service));

        }
        return list;
    }

    private List<ServiceEntity> initBaseServiceData() {
        List<ServiceEntity> list = new ArrayList<>();

        for (int i = 0; i < 16; i++) {
            list.add(new ServiceEntity("基础功能" + i,R.mipmap.ic_base_service));

        }
        return list;
    }

    @Override
    public void serviewAdd(ServiceEntity entity) {
        mMyServiceAdapter.add(entity);
        mBaseServiceAdapter.remove(entity);

    }

    @Override
    public void serviceDel(ServiceEntity entity) {
        mMyServiceAdapter.remove(entity);
        mBaseServiceAdapter.add(entity);
    }
}

