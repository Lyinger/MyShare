package com.example.kingwen.frescofragment.Activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kingwen.frescofragment.Beans.Community;
import com.example.kingwen.frescofragment.R;

/**
 * Created by kingwen on 2016/9/20.
 *
 * 用于显示活动
 */
public class ShowCompanyActivity extends AppCompatActivity {

    private Community community=null;

    private TextView tv_activityname;
    private TextView tv_activitytime;
    private TextView tv_activitycontent;
    private TextView tv_companyname;

    private Button btn_confirm;
    private ImageButton btn_return;

    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //需要修改
        setContentView(R.layout.activity_showactivity_layout);

        mContext=getBaseContext();

       initViews();

        initData();

        initListener();

        Log.e("showcompanyactivity","hell0");

    }

    private void initData() {
        community=getIntent().getParcelableExtra("mCommunity");

        Log.e("showActivity",community.toString());
        tv_activityname.setText(community.getActivityName());
        tv_activitytime.setText(community.getActivityTime());
        tv_activitycontent.setText(community.getActivityContent());
        tv_companyname.setText(community.getCompanyName());

    }

    private void initListener() {

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(mContext,"报名成功",Toast.LENGTH_SHORT).show();

            }
        });

        btn_return.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ShowCompanyActivity.this.finish();
            }
        });

    }

    private void initViews() {

        tv_activityname= (TextView) findViewById(R.id.dt_activity_name);
        tv_activitytime= (TextView) findViewById(R.id.dt_activity_time);
        tv_activitycontent= (TextView) findViewById(R.id.dt_activity_introduction);
        tv_companyname= (TextView) findViewById(R.id.dt_company_name);

        btn_confirm= (Button) findViewById(R.id.dt_attend_button);
        btn_return=(ImageButton)findViewById(R.id.return_setting_showacitivity_title);


    }
}
