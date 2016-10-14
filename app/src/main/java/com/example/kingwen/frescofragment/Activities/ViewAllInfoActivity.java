package com.example.kingwen.frescofragment.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.kingwen.frescofragment.Adapters.BookAdapter;
import com.example.kingwen.frescofragment.Beans.PublishBook;
import com.example.kingwen.frescofragment.R;

import java.util.ArrayList;
/**
 * Created by kingwen on 2016/6/9.
 */
public class ViewAllInfoActivity extends AppCompatActivity {

    /**
     * 标题栏显示名字
     */

    TextView tv_title;
    /**
     * 用于显示结果的recycleview
     */

    RecyclerView rv_allInfo;

    /**
     * 数据源
     */
    private ArrayList<PublishBook> mDatas;

    /**
     * 适配器
     */
    private BookAdapter mAdapter;

    /**
     * 上下文
     */
    private Context mContext;


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_viewallinfo_layout);
        
        initViews();
        

        initDatas();

        initAdapter();

    }

    private void initViews() {

          tv_title= (TextView) findViewById(R.id.tv_title_viewallinfo);

         rv_allInfo= (RecyclerView) findViewById(R.id.rv_allinfo_activity);

    }

    private void initAdapter() {
        mAdapter=new BookAdapter(mContext,mDatas);
        rv_allInfo.setLayoutManager(new LinearLayoutManager(mContext));
        rv_allInfo.setItemAnimator(new DefaultItemAnimator());
        rv_allInfo.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new BookAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int data) {
                Intent intent=new Intent(mContext,PubBookDetailActivity.class);
                intent.putExtra("detailbook",mDatas.get(data));
                startActivity(intent);
            }
        });

    }

    private void initDatas() {

         Intent intent=getIntent();
         String titleText=intent.getStringExtra("text");
         tv_title.setText(titleText);

         mContext=getBaseContext();
         mDatas=intent.getParcelableArrayListExtra("data");

    }
}
