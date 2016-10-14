package com.example.kingwen.frescofragment.Activities;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.kingwen.frescofragment.Beans.StringSessionRequest;
import com.example.kingwen.frescofragment.Constants.Nets;
import com.example.kingwen.frescofragment.R;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kingwen on 2016/6/12.
 */
public class PublishActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private ImageView ivPublish;

    EditText etName;

    EditText etAuthor;

    EditText etSummary;

    CheckBox checkboxNovel;

    CheckBox checkboxBiography;

    CheckBox checkboxLife;

    CheckBox checkboxProfessional;

    CheckBox checkboxJournal;

    CheckBox checkboxOther;

    CheckBox checkboxWaydeliver;

    CheckBox checkboxWayexchange;

    CheckBox checkboxWaysale;

    CheckBox checkboxWayborrow;



    EditText etBookersay;


    Button btn_publish;


    RequestQueue mQueue;

    private Context mContext;

    private int  kindchecked = -1;

    private int  waychecked = -1;


    /**
     *  从edittext中获取的参数
     */
     private  String name;

     private  String author;

     private String summary;

     private String bookersay;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       setContentView(R.layout.activity_publish_layout);

        Log.e("publish activity", "setlayout");


        initViews();

        mContext=getApplicationContext();
        mQueue= Volley.newRequestQueue(mContext);


        /**
         * 分类的监听事件
         */

        ivPublish.setOnClickListener(new View.OnClickListener() {
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
        checkboxJournal.setOnCheckedChangeListener(this);
        checkboxLife.setOnCheckedChangeListener(this);
        checkboxNovel.setOnCheckedChangeListener(this);
        checkboxOther.setOnCheckedChangeListener(this);
        checkboxProfessional.setOnCheckedChangeListener(this);
        checkboxBiography.setOnCheckedChangeListener(this);

        /**
         * 处理方式的监听事件
         */
         checkboxWayborrow.setOnCheckedChangeListener(this);
         checkboxWaydeliver.setOnCheckedChangeListener(this);
         checkboxWayexchange.setOnCheckedChangeListener(this);
         checkboxWaysale.setOnCheckedChangeListener(this);




        /**
         * 设置checkbox的监听，一共两个部分，分类和处理方式。
         */
       btn_publish.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               /**
                * 需要判断合法性，如果合法就进行更新，否则重新输入
                */

               if(JudgeLegal()){


                   StringSessionRequest mRequest = new StringSessionRequest(Request.Method.POST, Nets.NET_TOPUBLISH, new Response.Listener<String>() {
                       @Override
                       public void onResponse(String response) {

                           if(response.equals("")){
                               Log.e("pub responce","null");
                           }

                           //Log.e("pub onresponse",response.toString());

                           if(response.equals("1")){

                               Toast.makeText(getApplicationContext(), "上传成功", Toast.LENGTH_SHORT).show();

                               Intent intent=new Intent(PublishActivity.this,MainActivity.class);
                               startActivity(intent);

                           }

                       }
                   }, new Response.ErrorListener() {
                       @Override
                       public void onErrorResponse(VolleyError error) {

                          Log.e("errorListener", error.toString());

                           Toast.makeText(getApplicationContext(), "请检查网络问题", Toast.LENGTH_SHORT).show();

                       }
                   }){
                       /**
                        * 上传参数
                        * @return
                        * @throws AuthFailureError
                        */
                       protected Map<String, String> getParams() throws AuthFailureError {

                           Map<String, String> map = new HashMap<String, String>();

                           map.put("bookname", name);
                           map.put("bookauthor", author);
                           map.put("booksummary", summary);
                           map.put("bookassortment", String.valueOf(kindchecked));
                           map.put("bookway", String.valueOf(waychecked));
                           map.put("bookersay", bookersay);


                           Log.d("publish name", name);
                           Log.d("bookassortment",  String.valueOf(kindchecked));
                           Log.d("bookway",String.valueOf(waychecked));
                           Log.d("publish author", author);
                           Log.d("publish summary", summary);
                           Log.d("publish bookersay",bookersay);
                           return map;
                       }
                   };

                   try {
                       Log.e("PublishActivityheader", mRequest.getHeaders().toString());
                   } catch (AuthFailureError authFailureError) {
                       authFailureError.printStackTrace();
                   }

                   mQueue.add(mRequest);

               }else{

                   Toast.makeText(mContext,"所有内容要填写哦",Toast.LENGTH_SHORT).show();
               }
           }
       });
    }

    /*
* 选择照片并回传这个照片
* */
    protected void  onActivityResult(int requestCode,int resultCode,Intent data){
        if(resultCode==RESULT_OK){
            Uri uri=data.getData();
            Log.e("uri",uri.toString());
            ContentResolver cr=this.getContentResolver();
           // try{
//                通过路径得到图片。然后显示到我们的imageButton中
               // Bitmap bitMap= BitmapFactory.decodeStream(cr.openInputStream(uri));
            /*    if(bitMap==null){
                    Log.e("bitMap is wrong","bitMap 为空");
                }
                Log.e("showImage", "yunxing dao le show Image");
                ivPublish.setImageBitmap(bitMap);*/

                Glide.with(this)
                        .load(uri)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .override(120,120)
                        .fitCenter()
                        .into(ivPublish);



       /*     }catch (FileNotFoundException e) {
                Log.e("Exception",e.getMessage());
                e.printStackTrace();
            }*/
        }
        super.onActivityResult(requestCode,resultCode,data);
    }


    private void initViews() {


        ivPublish= (ImageView) findViewById(R.id.iv_publish);
        etName= (EditText) findViewById(R.id.et_name);
        etAuthor= (EditText) findViewById(R.id.et_author);
        etSummary= (EditText) findViewById(R.id.et_summary);
        checkboxNovel= (CheckBox) findViewById(R.id.checkbox_novel);
        checkboxBiography= (CheckBox) findViewById(R.id.checkbox_biography);
        checkboxLife= (CheckBox) findViewById(R.id.checkbox_life);
        checkboxProfessional= (CheckBox) findViewById(R.id.checkbox_professional);
        checkboxJournal= (CheckBox) findViewById(R.id.checkbox_journal);
        checkboxOther= (CheckBox) findViewById(R.id.checkbox_other);
        checkboxWaydeliver= (CheckBox) findViewById(R.id.checkbox_waydeliver);
        checkboxWayexchange= (CheckBox) findViewById(R.id.checkbox_wayexchange);
        checkboxWaysale= (CheckBox) findViewById(R.id.checkbox_waysale);
        checkboxWayborrow= (CheckBox) findViewById(R.id.checkbox_wayborrow);
        etBookersay= (EditText) findViewById(R.id.et_bookersay);
        btn_publish= (Button) findViewById(R.id.btn_publish);

    }




    /**
     * 在发布之前要进行判断所填内容是否合法
     * @return
     */
    private boolean JudgeLegal() {

        name=etName.getText().toString().trim();
        author=etAuthor.getText().toString().trim();
        summary=etSummary.getText().toString().trim();
        bookersay=etBookersay.getText().toString().trim();

        Log.d("publish name", name);
        Log.d("publish author", author);
        Log.d("publish summary", summary);
        Log.d("publish bookersay",bookersay);
        Log.d("publish kindchoosed", kindchecked+"");
        Log.d("publish typechoosed", waychecked+"");


        if(("".equals(name))||("".equals(author))
            ||("".equals(summary))||("".equals(bookersay))||
                (waychecked==-1)||(kindchecked==-1))
        {
            return false;
        }
           return true;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        switch (buttonView.getId()){
            case R.id.checkbox_novel:
                if(isChecked){
                    judgeKindchecked();
                    kindchecked=1;
                }else{
                    kindchecked=-1;
                }
                break;
            case R.id.checkbox_biography:
                if(isChecked){
                    judgeKindchecked();
                    kindchecked=2;
                }else{
                    kindchecked=-1;
                }
                break;
            case R.id.checkbox_life:

                if(isChecked){
                    judgeKindchecked();
                    kindchecked=3;
                }else{
                    kindchecked=-1;
                }
                break;

            case R.id.checkbox_professional:

                if(isChecked){
                    judgeKindchecked();
                    kindchecked=4;
                }else{
                    kindchecked=-1;
                }
                break;

            case R.id.checkbox_journal:

                if(isChecked){
                    judgeKindchecked();
                    kindchecked=5;
                }else{
                    kindchecked=-1;
                }
                break;

            case R.id.checkbox_other:

                if(isChecked){
                    judgeKindchecked();
                    kindchecked=6;
                }else{
                    kindchecked=-1;
                }
                break;


            //1送 2 换  3卖  4借
            case R.id.checkbox_waydeliver:

                if(isChecked){
                    judgeWaychecked();
                    waychecked=1;
                }else{
                    kindchecked=-1;
                }

                break;
            case R.id.checkbox_wayexchange:
                if(isChecked){
                    judgeWaychecked();
                    waychecked=2;
                }else{
                    kindchecked=-1;
                }
                break;
            case R.id.checkbox_waysale:
                if(isChecked){
                    judgeWaychecked();
                    waychecked=3;
                }else{
                    kindchecked=-1;
                }
                break;
            case R.id.checkbox_wayborrow:

                if(isChecked){
                    judgeWaychecked();
                    waychecked=4;
                }else{
                    kindchecked=-1;
                }

                break;
        }

    }

    /**
     * 图书处理方式的checkbox的 ：确保单选效果 //1送 2 换  3卖  4借
     */
    private void judgeWaychecked() {
        switch (waychecked){
            case 1:
                checkboxWaydeliver.setChecked(false);
                waychecked=-1;
                break;
            case 2:
                checkboxWayexchange.setChecked(false);
                waychecked=-1;
                break;
            case 3:
                checkboxWaysale.setChecked(false);
                waychecked=-1;
                break;

            case 4:
                checkboxWayborrow.setChecked(false);
                waychecked=-1;
                break;

        }

    }

    /**
     * 图书类型的的checkbox的选项。确保单选效果
     */
    private void judgeKindchecked() {

        switch (kindchecked){
            case 1:
                checkboxNovel.setChecked(false);
                kindchecked=-1;
                break;
            case 2:
                checkboxBiography.setChecked(false);
                kindchecked=-1;
                break;
            case 3:
                checkboxLife.setChecked(false);

                kindchecked=-1;
                break;
            case 4:
                checkboxProfessional.setChecked(false);
                kindchecked=-1;
                break;
            case 5:
                checkboxJournal.setChecked(false);
                kindchecked=-1;
                break;
            case 6:
                checkboxOther.setChecked(false);
                kindchecked=-1;
                break;

        }

    }

}
