package com.example.kingwen.frescofragment.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.kingwen.frescofragment.Adapters.MyInforAdapter;
import com.example.kingwen.frescofragment.Beans.Booker;
import com.example.kingwen.frescofragment.MyApplication.MyApplication;
import com.example.kingwen.frescofragment.R;


/**
 * Created by kingwen on 2016/9/20.
 * <p>
 * 我的个人信息界面，方面用于之后的宣传，这里的所有信息是textview 如果需要更改进入更改界面然后进行更改
 */
public class MyInforActivity extends AppCompatActivity {



    private ImageView iv_return;

    private ImageButton iv_changeInfo;

    private RecyclerView rc_myinfo;


    //RecycleView的布局属性
    private RecyclerView.LayoutManager manager;
    private MyInforAdapter myInforAdapter;

    private MyApplication myApplication;
    private Booker currentBooker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_myinfor_layout);


        initViews();

        initRecycleView();

        initListeners();

    }

    private void initViews() {
        iv_return= (ImageView) findViewById(R.id.return_myinfo);
        iv_changeInfo= (ImageButton) findViewById(R.id.setting_myinfor);
        rc_myinfo= (RecyclerView) findViewById(R.id.recycleview_myfor);
    }

    private void initRecycleView() {
        myApplication= (MyApplication) getApplication();
        currentBooker=myApplication.getCurrentBooker();

       //配置适配器
        manager=new LinearLayoutManager(MyInforActivity.this);
        rc_myinfo.setLayoutManager(manager);
        rc_myinfo.setHasFixedSize(true);


        myInforAdapter=new MyInforAdapter(MyInforActivity.this, currentBooker);
        rc_myinfo.setAdapter(myInforAdapter);

    }

    private void initListeners() {

        //如果点击了返回按键，那么当前界面销毁，返回前一个界面
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyInforActivity.this.finish();
            }
        });

        //如果进行相应的信息更改
        iv_changeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(MyInforActivity.this,ChangeMyInforActivity.class);
                startActivity(intent);

            }
        });





    }
}
