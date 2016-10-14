package com.example.kingwen.frescofragment.Fragments;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.kingwen.frescofragment.Activities.ChangeMyInforActivity;
import com.example.kingwen.frescofragment.Activities.MyInforActivity;
import com.example.kingwen.frescofragment.Adapters.MyInforAdapter;
import com.example.kingwen.frescofragment.Beans.Booker;
import com.example.kingwen.frescofragment.MyApplication.MyApplication;
import com.example.kingwen.frescofragment.R;
import com.example.kingwen.frescofragment.Views.CircleImageView;

import java.io.FileNotFoundException;

/**
 * Created by kingwen on 2016/6/5.
 */
public class MyInformationFragment extends Fragment {

     private CircleImageView iv_touxiang;

    private ImageButton iv_changeInfo;

    private RecyclerView rc_myinfo;


    //RecycleView的布局属性
    private RecyclerView.LayoutManager manager;
    private MyInforAdapter myInforAdapter;

    private MyApplication myApplication;
    private Booker currentBooker;

    @Override
    public Context getContext() {
        return super.getContext();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myApplication= (MyApplication)getActivity().getApplication();
        currentBooker=myApplication.getCurrentBooker();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View  view=inflater.inflate(R.layout.fragment_myinfromation_layout, container, false);


        iv_changeInfo= (ImageButton)view.findViewById(R.id.setting_myinfor);
        rc_myinfo= (RecyclerView) view.findViewById(R.id.recycleview_myfor);
        //配置适配器
        manager=new LinearLayoutManager(getActivity());
        rc_myinfo.setLayoutManager(manager);
        rc_myinfo.setHasFixedSize(true);

        myInforAdapter=new MyInforAdapter(getContext(), currentBooker);
        rc_myinfo.setAdapter(myInforAdapter);

        initListeners();

        return view;
    }

    /*
 * 选择照片并回传这个照片
 * */
/*    public void  onActivityResult(int requestCode,int resultCode,Intent data){

        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode== Activity.RESULT_OK){
            Uri uri=data.getData();
            Log.e("uri", uri.toString());
            ContentResolver cr=this.getActivity().getContentResolver();
            try{
//                通过路径得到图片。然后显示到我们的imageButton中
                Bitmap bitMap= BitmapFactory.decodeStream(cr.openInputStream(uri));
                if(bitMap==null){
                    Log.e("bitMap is wrong","bitMap 为空");
                }
                Log.e("showImage", "yunxing dao le show Image");

                Glide.with(this)
                        .load(uri)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .override(120,120)
                        .fitCenter()
                        .into(iv_changeInfo);



            }catch (FileNotFoundException e) {
                Log.e("Exception",e.getMessage());
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }*/


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK){
            Uri uri=data.getData();
            Log.e("uri", uri.toString());
            ContentResolver cr=this.getActivity().getContentResolver();
            try{
//                通过路径得到图片。然后显示到我们的imageButton中
                Bitmap bitMap= BitmapFactory.decodeStream(cr.openInputStream(uri));
                if(bitMap==null){
                    Log.e("bitMap is wrong","bitMap 为空");
                }
                Log.e("showImage", "yunxing dao le show Image");

                Glide.with(this)
                        .load(uri)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .override(120,120)
                        .fitCenter()
                        .into(iv_changeInfo);



            }catch (FileNotFoundException e) {
                Log.e("Exception",e.getMessage());
                e.printStackTrace();
            }
        }


    }

    private void initListeners() {


        iv_changeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                   /*
              * 通过intent实现页面的跳转，打开选择照片的页面
              * */
                Intent intent = new Intent();
//                开启pictures画面的type设定为image
                intent.setType("image/*");
//                使用intent.action_get_content这个action
                intent.setAction(Intent.ACTION_GET_CONTENT);
//                取得相片之后返回本界面
                startActivityForResult(intent, 111);


            }
        });


        //如果进行相应的信息更改
        iv_changeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* Intent intent = new Intent(getContext(), ChangeMyInforActivity.class);
                startActivity(intent);*/

            }
        });

    }



}
