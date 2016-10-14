package com.example.kingwen.frescofragment.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.kingwen.frescofragment.Beans.PublishBook;
import com.example.kingwen.frescofragment.Beans.StringSessionRequest;
import com.example.kingwen.frescofragment.Constants.Nets;
import com.example.kingwen.frescofragment.R;
import com.example.kingwen.frescofragment.Utils.TransMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kingwen on 2016/6/6.
 *
 * 查看数据详情的活动
 */
public class PubBookDetailActivity extends AppCompatActivity {
    private ImageView iv_book;
    private TextView  tv_bookname;
    private TextView  tv_bookauthor;
    private TextView  tv_bookway;
    private TextView  tv_booksummary;


    private ImageView iv_nick;
    private TextView  tv_nickname;
    private TextView  tv_bookersay;


    private Button btn_followPerson;
    private Button btn_followBook;
    private ImageButton btn_return;

    //上下文对象
    private Context mContext;

    //TAG
    private final String TAG="PubBookDatailActivity";


    //是否已经关注人
    private  boolean isfollowPerson=false;

    //是否已经关注这本书

    private  boolean isfollowBook=false;


    //网络请求队列
    private RequestQueue mQueue;


    //发布的图书
    private PublishBook book;

    /**
     *
     * 按说应该有一个他人评价部分，这里暂时先忽略，有时间再补上
     *
    private  ImageView iv_nick_assess;
    private  TextView   tv_nickname_aeess;
    private  TextView   tv_assess;
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pubbookdetail_layout);

        mContext=getBaseContext();

        initViews();

        initDatas();

        initNets();

        initListeners();
    }

    private void initNets() {

        mQueue=Volley.newRequestQueue(mContext);

    }

    private void initListeners() {

        btn_followBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            /*    if(isfollowPerson){
                    Toast.makeText(mContext,"已经成功关注"+tv_bookname.getText(),Toast.LENGTH_SHORT).show();
                    isfollowPerson=true;
                }else{
                    Toast.makeText(mContext,"取消关注"+tv_bookname.getText(),Toast.LENGTH_SHORT).show();
                    isfollowPerson=false;
                }
*/
                StringSessionRequest request=new StringSessionRequest(Request.Method.POST, Nets.NET_FOLLOWPERSON, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d(TAG,response);
                        if("1".equals(response)&&(!isfollowPerson)){
                            Toast.makeText(mContext,"已经成功关注"+tv_bookname.getText(),Toast.LENGTH_SHORT).show();
                            btn_followPerson.setText("取消关注");
                           ;
                        }else if ("0".equals(response)&&(isfollowPerson)){
                            Toast.makeText(mContext,"已经成功取消关注"+tv_bookname.getText(),Toast.LENGTH_SHORT).show();
                            btn_followPerson.setText("关注此书");
                            isfollowBook=false;
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(mContext,"关注失败，请检查网络信息",Toast.LENGTH_SHORT).show();

                    }
                }){
                    protected Map<String, String> getParams() throws AuthFailureError {

                        /**
                         * 上传参数，是否已经关注，0是没有关注，1是已经关注
                         */
                        Map<String, String> map = new HashMap<String, String>();

                        map.put("bid",String.valueOf(book.getBookid()));
                        map.put("isfollowBook", String.valueOf(isfollowBook));

                        return map;

                    }

                };
                mQueue.add(request);

            }
        });


        btn_followPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringRequest request1=new StringRequest(Request.Method.POST, Nets.NET_FOLLOWPERSON, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d(TAG,response);
                        if("1".equals(response)&&(isfollowPerson)){
                            Toast.makeText(mContext,"已经成功关注"+tv_nickname.getText(),Toast.LENGTH_SHORT).show();
                            btn_followPerson.setText("取消关注");
                            isfollowPerson=true;
                        }else if ("0".equals(response)&&(!isfollowPerson)){
                            Toast.makeText(mContext,"已经成功取消关注"+tv_nickname.getText(),Toast.LENGTH_SHORT).show();
                            btn_followPerson.setText("关注此人");
                            isfollowPerson=false;
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(mContext,"关注失败，请检查网络信息",Toast.LENGTH_SHORT).show();

                    }
                }){
                    protected Map<String, String> getParams() throws AuthFailureError {

                        /**
                         * 上传参数，是否已经关注，0是没有关注，1是已经关注
                         */
                        Map<String, String> map = new HashMap<String, String>();

                        map.put("uid2",String.valueOf(book.getUid()));
                        map.put("isfollowPerson", String.valueOf(isfollowPerson));

                        return map;

                    }

                };
                mQueue.add(request1);

            }
        });

        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PubBookDetailActivity.this.finish();
            }
        });
    }

    private void initDatas() {
         book= (PublishBook) getIntent().getParcelableExtra("detailbook");


        Log.e("获取的book",book+"");

        /**
         * 将数据中的所有信息绑定到我们的控件中
         */

        Glide.with(mContext)
                .load(book.getBookUrl())
                .into(iv_book);
        Glide.with(mContext)
                .load(book.getNickUrl())
                .into(iv_nick);

        tv_bookauthor.setText(book.getBookAuthor());
        tv_bookname.setText(book.getBookName());
        tv_booksummary.setText(book.getBookSummary());
        tv_bookway.setText(TransMethod.GetBookWay(book.getBookWay()));
        tv_nickname.setText(book.getNickName());
        tv_bookersay.setText(book.getBookerSay());

    }

    private void initViews() {

         iv_book= (ImageView) findViewById(R.id.iv_book_detail);
         tv_bookname= (TextView) findViewById(R.id.tv_bookname_detail);
         tv_bookauthor= (TextView) findViewById(R.id.tv_bookauthor_detail);
         tv_bookway= (TextView) findViewById(R.id.tv_bookway_detail);
         tv_booksummary= (TextView) findViewById(R.id.tv_booksummary_detail);

         iv_nick= (ImageView) findViewById(R.id.iv_nick_detail);
         tv_nickname= (TextView) findViewById(R.id.tv_nickname_detail);
         tv_bookersay= (TextView) findViewById(R.id.tv_bookersay_detail);

         btn_followPerson= (Button) findViewById(R.id.btn_followPerson_detail);
         btn_followBook= (Button) findViewById(R.id.btn_followBook_detail);
         btn_return=(ImageButton)findViewById(R.id.return_setting_pubbookdetail_title);

    }
}
