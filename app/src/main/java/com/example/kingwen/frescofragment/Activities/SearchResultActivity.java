package com.example.kingwen.frescofragment.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.util.Util;
import com.example.kingwen.frescofragment.Adapters.BookAdapter;
import com.example.kingwen.frescofragment.Beans.PublishBook;
import com.example.kingwen.frescofragment.Constants.Books;
import com.example.kingwen.frescofragment.R;
import com.example.kingwen.frescofragment.Utils.TransMethod;

import java.util.ArrayList;

/**
 * Created by kingwen on 2016/6/7.
 */
public class SearchResultActivity extends AppCompatActivity {
    /**
     * 分类
     */
    private TextView tv_assortment;
    /**
     * 展示搜索结果的recycleview
     */
    private RecyclerView rv_searchresult;
    /**
     * 数据源
     */
    private ArrayList<PublishBook> mDatas;
    /**
     * 适配器
     */
    private BookAdapter mAdapter;

    /**
     *
     * 对应网址
     */
   private String searchUrl=null;

    /**
     * 上下文对象
     */
    private Context mContext;

    /**
     * 用于打印的TAG
     */

    private final String TAG="搜索界面";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchresult_layout);

        initViews();

        initDatas();

        initAdapters();

        initListeners();

    }

    private void initListeners() {

        mAdapter.setOnItemClickListener(new BookAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int data) {

                Intent intent=new Intent(SearchResultActivity.this,PubBookDetailActivity.class);
                intent.putExtra("bookdetail",mDatas.get(data));
                startActivity(intent);

            }
        });
    }

    private void initAdapters() {

        mAdapter=new BookAdapter(mContext,mDatas);

        rv_searchresult.setLayoutManager(new LinearLayoutManager(mContext));

        rv_searchresult.setItemAnimator(new DefaultItemAnimator());

        rv_searchresult.setAdapter(mAdapter);


    }

    private void initDatas() {

        mDatas=new ArrayList<PublishBook>();
      //  mDatas.add(Books.book);


        mContext=getBaseContext();

        /**
         * 从另一个activity传输过来的信息
         */
        Intent intent=getIntent();
        int i=intent.getIntExtra("assortment",-1);

        /**
         * 得到对应的网址
         */
        //searchUrl= TransMethod.getUrlById(i);


Log.d(TAG,"URL"+searchUrl);

        mDatas= TransMethod.getBookData(i);


/*
        *//**
         * 通过网络请求得到我们搜索的数据
         *//*
        RequestQueue mQueue= Volley.newRequestQueue(mContext);

        StringRequest mRequest=new StringRequest(Request.Method.GET, searchUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.e("searchActivity success",response);

                        *//**
                         * 将请求回来的数据进行整理 
                         *//*
                        mDatas=TransMethod.getArrFromResponce(response);

                        mDatas.add(Books.book);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("searchActivity ","fail");

                        TransMethod.ShowToast(mContext,"请求出错");

                    }
                }
        );

        mQueue.add(mRequest);*/

    }

    private void initViews() {

        tv_assortment= (TextView) findViewById(R.id.tv_assortment_searchactivity);
        rv_searchresult= (RecyclerView) findViewById(R.id.rv_searchresult_activity);

    }
}
