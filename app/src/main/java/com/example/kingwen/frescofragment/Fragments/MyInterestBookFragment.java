package com.example.kingwen.frescofragment.Fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kingwen.frescofragment.Activities.PubBookDetailActivity;
import com.example.kingwen.frescofragment.Adapters.BookAdapter;
import com.example.kingwen.frescofragment.Beans.PublishBook;
import com.example.kingwen.frescofragment.Constants.Books;
import com.example.kingwen.frescofragment.Constants.Nets;
import com.example.kingwen.frescofragment.R;
import com.example.kingwen.frescofragment.Utils.TransMethod;
import java.util.ArrayList;

/**
 * Created by kingwen on 2016/6/5.
 */
public class MyInterestBookFragment extends Fragment {

    /**
     * 用来展示我关注的书
     */
    RecyclerView rv_mInterestBook;

    /**
     * 上下文
     */
    private Context mContext;

    /**
     * 打印用的TAG
     */
    private final String TAG="我的关注界面";

    /**
     * 网络请求队列
     * */
    private RequestQueue mQueue;

    /**
     * 适配器数据源
     */
    private ArrayList<PublishBook> mDatas;

    private BookAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext=getActivity();

        initDatas();

    }
    private void initDatas() {

        mDatas=new ArrayList<PublishBook>();
        mDatas.add(Books.book);


        mQueue= Volley.newRequestQueue(mContext);
        StringRequest subRequest=new StringRequest(Request.Method.POST, Nets.NET_MYINTEREST_BOOK, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "initDataintersetbook" + response);

                mDatas= TransMethod.getArrFromResponce(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                TransMethod.ShowToast(mContext,"获取数据失败，请查看网络");
            }
        });

        mQueue.add(subRequest);

    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_interestbook_layout, container, false);

        rv_mInterestBook= (RecyclerView) view.findViewById(R.id.rv_interestbook);

        mAdapter=new BookAdapter(mContext,mDatas);

        rv_mInterestBook.setLayoutManager(new LinearLayoutManager(mContext));
        rv_mInterestBook.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BookAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int data) {
                Intent intent=new Intent(mContext, PubBookDetailActivity.class);
                intent.putExtra("detailbook",mDatas.get(data));
                mContext.startActivity(intent);
            }
        });
        return view;

    }
}
