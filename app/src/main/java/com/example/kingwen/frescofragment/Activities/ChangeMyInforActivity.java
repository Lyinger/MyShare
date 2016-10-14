package com.example.kingwen.frescofragment.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.example.kingwen.frescofragment.Adapters.ChangeMyInforAdapter;
import com.example.kingwen.frescofragment.Beans.Booker;
import com.example.kingwen.frescofragment.MyApplication.MyApplication;
import com.example.kingwen.frescofragment.R;


/**
 * Created by kingwen on 2016/9/20.
 *
 * 更改个人信息界面 类似于微信的更新信息界面
 */
public class ChangeMyInforActivity extends AppCompatActivity {


    //RecycleView的布局属性
    private RecyclerView.LayoutManager manager;
    private ChangeMyInforAdapter mChangeInforAdapter;

    private MyApplication myApplication;
    private Booker currentBooker;


    private RecyclerView rv_changeinfor;

    private ImageView ib_return;

    private ImageView iv_touxiang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_changeinfor_layout);

        initViews();

        initListener();

        initRecycleview();

    }


    private void initRecycleview() {
        myApplication= (MyApplication) getApplication();
        currentBooker=myApplication.getCurrentBooker();

        //配置适配器
        manager=new LinearLayoutManager(ChangeMyInforActivity.this);
        rv_changeinfor.setLayoutManager(manager);
        rv_changeinfor.setHasFixedSize(true);


        mChangeInforAdapter=new  ChangeMyInforAdapter(ChangeMyInforActivity.this, currentBooker);

        mChangeInforAdapter.setOnItemClickListener(new ChangeMyInforAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int data) {
                myApplication = (MyApplication) getApplication();
                currentBooker = myApplication.getCurrentBooker();

                Log.e("ChangeMyinfoActivity", view.toString() + data + "");

                /**
                 * 这里选择对个人信息进行修改
                 */


            }
        });

        rv_changeinfor.setAdapter(mChangeInforAdapter);

    }

    private void initListener() {

        ib_return.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 ChangeMyInforActivity.this.finish();
             }
         });

        /**
         * 更换头像进行操作:从手机中选择照片即可
         */
        iv_touxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //  Intent 。。。。


            }
        });
    }


    private void initViews() {

        rv_changeinfor= (RecyclerView) findViewById(R.id.recycleview_changeinfor);

        iv_touxiang= (ImageView) findViewById(R.id.touxiang_changeinfo);

        ib_return= (ImageView) findViewById(R.id.return_setting);
    }

}
