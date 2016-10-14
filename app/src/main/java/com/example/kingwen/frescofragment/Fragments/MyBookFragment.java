package com.example.kingwen.frescofragment.Fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kingwen.frescofragment.Activities.ViewAllInfoActivity;
import com.example.kingwen.frescofragment.Adapters.SimpleBookAdapter;
import com.example.kingwen.frescofragment.Beans.PublishBook;
import com.example.kingwen.frescofragment.Beans.StringSessionRequest;
import com.example.kingwen.frescofragment.Constants.Books;
import com.example.kingwen.frescofragment.Constants.Nets;
import com.example.kingwen.frescofragment.R;
import com.example.kingwen.frescofragment.Utils.TransMethod;

import java.util.ArrayList;

/**
 * Created by kingwen on 2016/6/1.
 */
public class MyBookFragment extends Fragment implements View.OnClickListener{

    /**
     * 查看我的订阅的更多
     */
    private ImageButton btn_detail_subs;

    /**
     * 查看我的出借的更多
     */
    private ImageButton  btn_detail_lend;

    /**
     * 显示我的订阅的recycleview
     */
    private RecyclerView rv_mysubs;

    /**
     *显示我的出借的recycleview
     */
    private RecyclerView rv_mylend;

    /**
     * 绑定订阅的数据源
     */
    private ArrayList<PublishBook> mSubDatas;

    /**
     * 绑定借出的数据源
     */
    private ArrayList<PublishBook> mLendDatas;

    /**
     * 订阅适配器Adapter
     */
    private SimpleBookAdapter mSubAdapter;

    /**
     * 借出适配器Adapter
     */
    private SimpleBookAdapter mLendAdapter;


    /**
     * 上下文对象
     */
    private Context mContext;

    /**
     * 活动对象
     */
    private Activity mActivity;

    /**
     * 网络请求队列
     */
    private RequestQueue mQueue;

    public  final  String TAG="我的图书界面";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext=getActivity();
        mActivity=getActivity();

        mQueue= Volley.newRequestQueue(mContext);

        initDatas();


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_mybook_layout,container,false);

        btn_detail_subs= (ImageButton) view.findViewById(R.id.detail_subs_mybook);
        btn_detail_lend= (ImageButton) view.findViewById(R.id.detail_lent_mybook);
        btn_detail_lend.setOnClickListener(this);
        btn_detail_subs.setOnClickListener(this);

        rv_mylend= (RecyclerView) view.findViewById(R.id.rv_myled_mybook);
        rv_mysubs= (RecyclerView) view.findViewById(R.id.rv_mysub_mybook);
        rv_mysubs.setLayoutManager(new LinearLayoutManager(mContext));
        rv_mylend.setLayoutManager(new LinearLayoutManager(mContext));


        mLendAdapter=new SimpleBookAdapter(mContext,mLendDatas);
        rv_mylend.setItemAnimator(new DefaultItemAnimator());
        rv_mylend.setAdapter(mLendAdapter);


        mSubAdapter=new SimpleBookAdapter(mContext,mSubDatas);
        rv_mysubs.setItemAnimator(new DefaultItemAnimator());
        rv_mysubs.setAdapter(mSubAdapter);

        return view;
    }


    @Override
    public void onClick(View v) {

        int id=v.getId();

        switch (id){

            case R.id.detail_subs_mybook:

                /**
                 * 跳转到showresult activity中去，
                 * 将我们在这里请求的数据直接传递过去，从而可以节省一部分的流量
                 */
                Intent intent = new Intent(mContext, ViewAllInfoActivity.class);
                intent.putExtra("text", "我的订阅");

                //传递我们数据作为adapter的数据源
                intent.putParcelableArrayListExtra("data",mSubDatas);
                startActivity(intent);


                break;
            case R.id.detail_lent_mybook:
                Intent intent1 = new Intent(mContext, ViewAllInfoActivity.class);
                intent1.putExtra("text", "我的借出");

                //传递我们数据作为adapter的数据源
                intent1.putParcelableArrayListExtra("data",mLendDatas);

                startActivity(intent1);

                break;
        }
    }


    /**
     * 通过网络请求获取对应的网络数据
     */
    private void initDatas() {

        mSubDatas=new ArrayList<PublishBook>();

        mLendDatas=new ArrayList<PublishBook>();

        mSubDatas.add(Books.book_other_lonelyandhonor);
        mSubDatas.add(Books.book_android);

        //mLendDatas.add(Books.book_other_lonelyandhonor);
        mLendDatas.add(Books.book_journal_lonely);

        StringRequest subRequest=new StringRequest(Request.Method.POST, Nets.NET_MYBOOK_PUBLISHING, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG,"initDatasub"+response);

                mSubDatas=TransMethod.getArrFromResponce(response);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                TransMethod.ShowToast(mContext,"获取数据失败，请查看网络");
            }
        });

        mQueue.add(subRequest);

        StringSessionRequest lendRequest=new  StringSessionRequest(Request.Method.POST, Nets.NET_MYBOOK_PUBLISHED, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG,"initDatalend"+response);

                mLendDatas=TransMethod.getArrFromResponce(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                TransMethod.ShowToast(mContext,"获取数据失败，请查看网络");
            }
        });

        mQueue.add(lendRequest);

    }
}
