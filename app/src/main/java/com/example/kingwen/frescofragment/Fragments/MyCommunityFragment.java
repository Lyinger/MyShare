package com.example.kingwen.frescofragment.Fragments;

import android.app.Activity;
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

import com.example.kingwen.frescofragment.Activities.ShowCompanyActivity;
import com.example.kingwen.frescofragment.Adapters.CommunityAdapter;
import com.example.kingwen.frescofragment.Beans.Community;
import com.example.kingwen.frescofragment.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kingwen on 2016/9/20.
 */
public class MyCommunityFragment extends Fragment {


    private RecyclerView rv_community;

    private Context mContext;

    private Activity activity;

    private List<Community> mCommunityDatas=null;

    private CommunityAdapter mCommunityAdapter=null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext=getActivity();
        activity=getActivity();


        initDatas();

    }

    private void initDatas() {

        mCommunityDatas=new ArrayList<>();
        Community community=new Community("叶子公司","叶子咖啡美食分享会","2016-9-21",
                "安静的午后，一杯浓浓的咖啡，一群可爱的伙伴，让我们享受我们的生活");
        mCommunityDatas.add(community);

    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_community_layout,container,false);
        rv_community= (RecyclerView) view.findViewById(R.id.rv_community);

        mCommunityAdapter=new CommunityAdapter(mContext,mCommunityDatas);
        rv_community.setLayoutManager(new LinearLayoutManager(mContext));
        rv_community.setItemAnimator(new DefaultItemAnimator());

        mCommunityAdapter.setOnItemClickListener(new CommunityAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int data) {

                Log.e("MyCommunity","setonclicklistener");

                Intent intent=new Intent(mContext,ShowCompanyActivity.class);
                intent.putExtra("mCommunity", mCommunityDatas.get(data));
                startActivity(intent);


            }
        });

        rv_community.setAdapter(mCommunityAdapter);


        return view;
    }

}
