package com.example.kingwen.frescofragment.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kingwen.frescofragment.Activities.PubBookDetailActivity;
import com.example.kingwen.frescofragment.Activities.SearchActivity;
import com.example.kingwen.frescofragment.Activities.SearchResultActivity;
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
public class MyHomePageFragment extends Fragment implements View.OnClickListener {

    //上下文对象
    private Context context;
    //当前活动对象
    private Activity activity;
   //用于显示中间的界面
    private  View view;

    //用于下拉刷新的控件
    private SwipeRefreshLayout swipe;

    //TAG
    private final String TAG="my homepageFragment";

    //网络请求队列
    private RequestQueue mQueue;


    //界面中各个组件
    private TextView et_search;
    private ImageButton  btn_search;

    //不同图书分类
    private  Button  btn_novel;
    private  Button  btn_biography;
    private  Button  btn_life;
    private  Button  btn_professional;
    private  Button  btn_journal;
    private  Button  btn_other;


    /**
     * 今日推荐(后期进行加工)
     * 同样是用一个recycleview来进行显示，只有三个
     * private ArrayList<Books> datalist;
     */


    /**
     * 最新发布的图书信息
     */
   private RecyclerView publishRecycleView;
   private ArrayList<PublishBook> publishdata;
   private BookAdapter publishadapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context=getActivity();
        activity=getActivity();

        Log.e(TAG,"oncreat");

        publishdata=new ArrayList<PublishBook>();
        publishadapter=new BookAdapter(context);

        initDatas();
        publishdata.add(Books.book_android);
        publishdata.add(Books.book_bio_deng);
        publishdata.add(Books.book_journal_guojiadili);
        publishdata.add(Books.book_journal_lonely);
        publishdata.add(Books.book_bio_jobs);
        publishdata.add(Books.book_journal_reader);
        publishdata.add(Books.book_life_bodylove);
        publishdata.add(Books.book_other_buddha);
        publishdata.add(Books.book_other_lonelyandhonor);
        publishdata.add(Books.book_life_bodylove);
        publishdata.add(Books.book_story_heyisheng);
        publishdata.add(Books.book_story_wanmei);
        publishdata.add(Books.book_bio_fangao);



    }


    @Override
    public void onResume() {
        super.onResume();

        initDatas();

    }

    /**
     * 初始化界面
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_homepage_layout,container,false);

        Log.e("oncreatview","view");

        initViews();

        initListeners();

        initSwipes();

        initAdapter();

        return view;
    }



    /**
     * 下拉刷新控件
     */
    private void initSwipes() {

      //  swipe.setColorSchemeColors();
      swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
          @Override
          public void onRefresh() {

              new Handler().postDelayed(new Runnable() {
                  @Override
                  public void run() {

                      int num = publishdata.size();
                      initDatas();

                      if (num != publishdata.size()) {

                          publishRecycleView.notify();
                          publishadapter.notify();

                      }

                      swipe.setRefreshing(false);

                  }
              }, 1000);

          }
      });

    }

    /**
     * 适配器的绑定
     */
    private void initAdapter() {

        //设置布局，线性布局
        publishRecycleView.setLayoutManager(new LinearLayoutManager(context));

        //设置动画，这里设置为默认动画
        publishRecycleView.setItemAnimator(new DefaultItemAnimator());

        //设置数据源
        publishadapter.setDatas(publishdata);

        //设置监听事件:点击之后跳转到图书的详细界面

        publishadapter.setOnItemClickListener(new BookAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int data) {
                Intent intent = new Intent(context, PubBookDetailActivity.class);
                intent.putExtra("detailbook", publishdata.get(data));
                startActivity(intent);
            }
        });

        //设定adapter
        publishRecycleView.setAdapter(publishadapter);

    }

    /**
     * 监听事件的实现
     */
    private void initListeners() {

        btn_search.setOnClickListener(this);
        btn_novel.setOnClickListener(this);
        btn_biography.setOnClickListener(this);
        btn_life.setOnClickListener(this);
        btn_professional.setOnClickListener(this);
        btn_journal.setOnClickListener(this);
        btn_other.setOnClickListener(this);
    }
    /**
     * 所有view的绑定
     **/
    private void initViews() {

        et_search= (TextView) view.findViewById(R.id.et_search_homefragment);
        btn_search= (ImageButton) view.findViewById(R.id.btn_search_homefragment);
        btn_novel= (Button) view.findViewById(R.id.btn_novel);
        btn_biography=(Button) view.findViewById(R.id.btn_biography);
        btn_life=(Button) view.findViewById(R.id.btn_life);
        btn_professional=(Button) view.findViewById(R.id.btn_professional);
        btn_journal=(Button) view.findViewById(R.id.btn_journal);
        btn_other= (Button) view.findViewById(R.id.btn_other);
        publishRecycleView= (RecyclerView) view.findViewById(R.id.recycle_view);
        swipe= (SwipeRefreshLayout) view.findViewById(R.id.swipe);

    }



    /**
     * 通过网络获取我们的书籍的数据，初始化我们的适配器中的数据
     */
    private void initDatas() {
        mQueue= Volley.newRequestQueue(context);
        StringRequest stringRequest=new StringRequest( Request.Method.GET,Nets.NET_PUBLISH,
               //成功的监听
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        Log.e("homepage  responce",response);


                        //将对responce的处理方法进行提取，成为一个专门的方法，从而提高程序的复用性
                        // 针对Json数据的处理
                        publishdata=TransMethod.getArrFromResponce(response);

                        publishadapter.setDatas(publishdata);


                    }
                },
                //错误的监听
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(context,"没有更多数据",Toast.LENGTH_LONG).show();

                    }
                });

//         mQueue.add(stringRequest);

    }

    /**
     * 点击事件的接口
     * @param v
     */
    @Override
    public void onClick(View v) {

        int id=v.getId();

        switch (id){

            //搜索界面
            case R.id.btn_search_homefragment:
                Intent intent=new Intent(context,SearchActivity.class);
                startActivity(intent);
                break;
            //小说
            case R.id.btn_novel:
                Intent intent1 =new Intent(context, SearchResultActivity.class);
                intent1.putExtra("assortment",1);
                startActivity(intent1);
                break;
            //传记
            case R.id.btn_biography:
                Intent intent2 =new Intent(context, SearchResultActivity.class);
                intent2.putExtra("assortment",2);
                startActivity(intent2);
                break;
            //生活
            case R.id.btn_life:
                Intent intent3 =new Intent(context, SearchResultActivity.class);
                intent3.putExtra("assortment",3);
                startActivity(intent3);
                break;
            //专业书
            case R.id.btn_professional:
                Intent intent4 =new Intent(context, SearchResultActivity.class);
                intent4.putExtra("assortment",4);
                startActivity(intent4);
                break;
            case R.id.btn_journal:
                Intent intent5 =new Intent(context, SearchResultActivity.class);
                intent5.putExtra("assortment",5);
                startActivity(intent5);
                break;
            case R.id.btn_other:
                Intent intent6 =new Intent(context, SearchResultActivity.class);
                intent6.putExtra("assortment",6);
                startActivity(intent6);
                break;
            default:{
                //do nothing
            }

        }


    }



}
