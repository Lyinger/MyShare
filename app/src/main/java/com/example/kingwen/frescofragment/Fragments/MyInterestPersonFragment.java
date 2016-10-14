package com.example.kingwen.frescofragment.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
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
import com.example.kingwen.frescofragment.Activities.BookerDetailActivity;
import com.example.kingwen.frescofragment.Adapters.BookerAdapter;
import com.example.kingwen.frescofragment.Beans.Booker;
import com.example.kingwen.frescofragment.Constants.Books;
import com.example.kingwen.frescofragment.Constants.Nets;
import com.example.kingwen.frescofragment.R;
import com.example.kingwen.frescofragment.Utils.TransMethod;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by kingwen on 2016/6/5.
 */
public class MyInterestPersonFragment extends Fragment {

    /**
     * 用来显示booker的信息
     */
   // @BindView(R.id.rv_interestperson)
    RecyclerView rv_interestbooker;


    private ArrayList<Booker> mDatas;

    private BookerAdapter mAdapter;

    private Context mContext;

    private RequestQueue mQueue;

    private final String TAG="myinterest界面 ";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext=getActivity();

        initDatas();
    }

    private void initDatas() {

        mDatas=new ArrayList<Booker>();
        mDatas.add(Books.booker);

        mQueue= Volley.newRequestQueue(mContext);
        StringRequest subRequest=new StringRequest(Request.Method.POST, Nets.NET_MYINTEREST_BOOK, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "initDataintersetbook" + response);

                JSONArray array;
                JSONObject resopnseobject;
                Booker booker;


                try {
                    array = new JSONArray(response);

                    mDatas = new ArrayList<Booker>();
                    int length = array.length();

                    for (int i = 0; i < length; i++) {

                        booker = new Booker();

                        resopnseobject = array.getJSONObject(i);
                        booker.setBookerUrl(resopnseobject.getString("bookerUrl"));
                        booker.setName(resopnseobject.getString("name"));
                        booker.setIntroduction(resopnseobject.getString("introduction"));
                        booker.setLocation(resopnseobject.getString("location"));
                        booker.setIsStudent(resopnseobject.getInt("isStudent"));
                        booker.setMailNum(resopnseobject.getString("mailNum"));

                        mDatas.add(booker);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_interestperson_layout, container, false);


        rv_interestbooker= (RecyclerView) view.findViewById(R.id.rv_interestperson);

        rv_interestbooker.setLayoutManager(new LinearLayoutManager(mContext));
        rv_interestbooker.setItemAnimator(new DefaultItemAnimator());

        mAdapter=new BookerAdapter(mContext,mDatas);
        mAdapter.setOnItemClickListener(new BookerAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int data) {

                /**
                 * 跳转到详细的个人信息的界面
                 */
                Intent intent=new Intent(mContext,BookerDetailActivity.class);
                intent.putExtra("detailbooker",mDatas.get(data));
                mContext.startActivity(intent);

            }
        });

        rv_interestbooker.setAdapter(mAdapter);

        return view;
    }
}
