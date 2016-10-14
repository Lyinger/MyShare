package com.example.kingwen.frescofragment.Fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kingwen.frescofragment.Activities.ViewAllInfoActivity;
import com.example.kingwen.frescofragment.Adapters.SimpleBookAdapter;
import com.example.kingwen.frescofragment.Beans.PublishBook;
import com.example.kingwen.frescofragment.Constants.Books;
import com.example.kingwen.frescofragment.Constants.Nets;
import com.example.kingwen.frescofragment.R;
import com.example.kingwen.frescofragment.Utils.TransMethod;

import java.util.ArrayList;


/**
 * Created by kingwen on 2016/6/1.
 */
public class MyBorrowFragment extends Fragment implements View.OnClickListener {

    /**
     * 上下文对象
     */
    private Context mContext;
    private Activity mActivity;

    /**
     * 网络请求队列
     */
    private RequestQueue mQueue;

    /**
     * 正在借阅的书籍的数据
     */
    private ArrayList<PublishBook> mLendingDatas;

    /**
     * 正在借阅书籍的适配器
     */
    private SimpleBookAdapter mLendingAdapter;

    /**
     * 已经归还的书籍的数据
     */
    private ArrayList<PublishBook> mLentDatas;

    /**
     * 已经归还的书籍的适配器
     */
    private SimpleBookAdapter mlentAdapter;

    /**
     * TAG
     */
    public  final String TAG="我的借阅界面";

    /**
     * 查看更多我正在借阅的书籍的图片按钮
     */

    ImageButton btn_lending;

    /**
     * 查看更多我已经归还的书籍的图片按钮
     */

    ImageButton btn_lent;

    /**
     * 用于展示我正在借阅的书籍的recycleview
     */

    RecyclerView rv_lending;

    /**
     * 用于展示我已经归还的书籍的recycleview
     */
    RecyclerView rv_lent;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=getActivity();
        mActivity=getActivity();

        initDatas();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_myborrow_layout,container,false);


        if(rv_lending==null){
            Log.e("rv_lending","null");
        }

        btn_lending= (ImageButton) view.findViewById(R.id.ib_lending_myborrow);
        btn_lent= (ImageButton) view.findViewById(R.id.ib_lent_myborrow);

        rv_lending= (RecyclerView) view.findViewById(R.id.rv_lending_myborrow);
        mLendingAdapter=new SimpleBookAdapter(mContext,mLendingDatas);
        rv_lending.setLayoutManager(new LinearLayoutManager(mContext));
        rv_lending.setItemAnimator(new DefaultItemAnimator());
        rv_lending.setAdapter(mLendingAdapter);

        rv_lent= (RecyclerView) view.findViewById(R.id.rv_lent_myborrow);
        mlentAdapter=new SimpleBookAdapter(mContext,mLentDatas);
        rv_lent.setLayoutManager(new LinearLayoutManager(mContext));
        rv_lent.setItemAnimator(new DefaultItemAnimator());
        rv_lent.setAdapter(mlentAdapter);

        /**
         * 当前借阅的书籍
         */
        btn_lending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(mContext,ViewAllInfoActivity.class);
                intent.putExtra("text",Books.MYBORROW_LENDING);
                intent.putParcelableArrayListExtra("data", mLendingDatas);

            }
        });

        /**
         * 借阅结束的书籍
         */
        btn_lent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(mContext,ViewAllInfoActivity.class);
                intent.putExtra("text",Books.MYBORROW_LENT);
                intent.putParcelableArrayListExtra("data", mLentDatas);

            }
        });

        return view;
    }

    private void initDatas() {

        mQueue= Volley.newRequestQueue(mContext);

        mLendingDatas=new ArrayList<PublishBook>();
        mLendingDatas.add(Books.book_story_wanmei);
        mLendingDatas.add(Books.book_bio_fangao);

        mLentDatas=new ArrayList<PublishBook>();
        //mLentDatas.add(Books.book_bio_deng);


        StringRequest subRequest=new StringRequest(Request.Method.POST, Nets.NET_MYSUBSCRIPTION, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "initDatalending" + response);

                mLendingDatas= TransMethod.getArrFromResponce(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                TransMethod.ShowToast(mContext,"获取数据失败，请查看网络");

            }
        });

        mQueue.add(subRequest);

        StringRequest lendRequest=new StringRequest(Request.Method.POST, Nets.NET_MYLEND, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG,"initDatalent"+response);

                mLentDatas=TransMethod.getArrFromResponce(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                TransMethod.ShowToast(mContext,"获取数据失败，请查看网络");
            }
        });
        mQueue.add(lendRequest);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.ib_lending_myborrow:

                Intent intent = new Intent(mContext, ViewAllInfoActivity.class);
                intent.putExtra("text", "正在借阅");

                //传递我们数据作为adapter的数据源
                intent.putParcelableArrayListExtra("data",mLendingDatas);
                startActivity(intent);


                break;
            case R.id.ib_lent_myborrow:
                Intent intent1 = new Intent(mContext, ViewAllInfoActivity.class);
                intent1.putExtra("text", "已经归还");

                //传递我们数据作为adapter的数据源
                intent1.putParcelableArrayListExtra("data",mLentDatas);
                startActivity(intent1);

                break;

            default:{
                //donothing
            }
        }
    }
}
