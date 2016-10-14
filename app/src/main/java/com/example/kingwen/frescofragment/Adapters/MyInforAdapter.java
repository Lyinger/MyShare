package com.example.kingwen.frescofragment.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kingwen.frescofragment.Beans.Booker;
import com.example.kingwen.frescofragment.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kingwen on 2016/9/20.
 */
public class MyInforAdapter extends RecyclerView.Adapter<MyInforHolder> {
    //布局扩展对象，作用类似于findviewByid，只是他找的是整个文件的布局而不是一个一个组件
    private LayoutInflater mInflater;
    //上下文对象，可有可无，用于之后的构造方法，当然如果后面需要弹出对话框那么就是必须的
    private Context mContext;
    //我们的数据源，这里是一个list链表。当然可以使用数组或者使用数据库对象
    private Map<String,String> mDatas;

    /*
    * 构造方法
    * */
    public MyInforAdapter(Context context,Booker booker){
        this.mContext=context;
        this.mDatas=getBookerData(booker);
//        Log.d("recycleview构造方法",mDatas.size()+"");
        mInflater= LayoutInflater.from(context);
    }

    private Map<String, String> getBookerData(Booker booker) {
        Map<String,String> bookmap =new HashMap<>();

        bookmap.put(getkey(1),booker.getName());

        bookmap.put(getkey(2),booker.getMailNum());

        bookmap.put(getkey(3),booker.getLocation());

        bookmap.put(getkey(4),booker.getIntroduction());

        return bookmap;
    }


    private  String getkey( int i){
        switch (i){
            case 1:
                return "姓名";
            case 2:
                return "邮箱";
            case 3:
                return "地址";
            case 4:
                return "个人签名";
        }
        return "";
    }



    @Override
    public MyInforHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /*
        * 得到我们每个item的布局，并且给每一个item添加我们的监听事件
        * */
        View view=mInflater.inflate(R.layout.item_myinfo_layout,parent,false);

        MyInforHolder inforHolder=new MyInforHolder(view);

        return inforHolder;
    }
    /*
    * 布局控件和数据源进行绑定
    * */
    @Override
    public void onBindViewHolder(MyInforHolder holder, int position) {
        //每一个view设置我们的文字，在数据源中相应位置的东西

        Log.e("MyInfo 未更改",position+"");

        holder.title.setText(getkey(position+1));
        //为每一个view设置我们的文章标题
        holder.describe.setText(mDatas.get(getkey(position+1)));
    }
    /*
    * 返回数据源的数目
    * */
    @Override
    public int getItemCount() {
        return mDatas.size();
    }



}
/*
* 通过viewholder来进行布局的绑定
* */
class MyInforHolder extends RecyclerView.ViewHolder{
    //显示题目
    TextView title;
    //显示内容
    TextView describe;

    public MyInforHolder(View itemView) {

        super(itemView);
        title= (TextView)itemView.findViewById(R.id.tv_choice_inforitem);
        describe= (TextView) itemView.findViewById(R.id.tv_font_inforitem);

    }
}
