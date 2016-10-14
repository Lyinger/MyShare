package com.example.kingwen.frescofragment.Adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kingwen.frescofragment.Activities.PubBookDetailActivity;
import com.example.kingwen.frescofragment.Beans.PublishBook;
import com.example.kingwen.frescofragment.R;
import com.example.kingwen.frescofragment.Utils.TransMethod;

import java.util.List;

/**
 * Created by kingwen on 2016/6/2.
 */
public class SimpleBookAdapter extends RecyclerView.Adapter<SimViewHolder>{

    private LayoutInflater mInflater;

    private Context mContext ;

    private List<PublishBook> mDatas;

    /**
     * 构造方法
     * @param mContext  上下文对象
     */
    public SimpleBookAdapter(Context mContext) {
        this.mContext = mContext;
        mInflater=LayoutInflater.from(mContext);
    }

    public SimpleBookAdapter(Context context, List<PublishBook> datas){
        this.mContext=context;
        this.mDatas=datas;

        mInflater=LayoutInflater.from(context);
    }


    public void setData(PublishBook book){
        mDatas.add(book);
        notifyDataSetChanged();
    }


    @Override
    public SimViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=mInflater.inflate(R.layout.item_simple_book_layout,parent,false);
        SimViewHolder viewHolder=new SimViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SimViewHolder holder, int position) {


       if(mDatas.size()==0){
           TransMethod.ShowToast(mContext, "还没有属于自己的收藏");
           holder.linear2.setVisibility(View.INVISIBLE);
           holder.linear3.setVisibility(View.INVISIBLE);

           Glide.with(mContext)
                 .load(R.drawable.img_null)
                 .into(holder.iv_book1);

           holder.tv_bookname1.setText("");
       }else if(mDatas.size()==1){
           holder.linear2.setVisibility(View.INVISIBLE);
           holder.linear3.setVisibility(View.INVISIBLE);

           PublishBook currentBook=mDatas.get(0);
           Glide.with(mContext)
                   .load(currentBook.getBookUrl())
                   .into(holder.iv_book1);
           holder.tv_bookname1.setText(currentBook.getBookName());


           holder.linear1.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent =new Intent(mContext, PubBookDetailActivity.class);
                   intent.putExtra("detailbook",mDatas.get(0));
                   mContext.startActivity(intent);
               }
           });

       }else if(mDatas.size()==2){
           holder.linear3.setVisibility(View.INVISIBLE);

           PublishBook currentBook=mDatas.get(0);
           Glide.with(mContext)
                   .load(currentBook.getBookUrl())
                   .into(holder.iv_book1);

           holder.tv_bookname1.setText(currentBook.getBookName());


           holder.linear1.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent = new Intent(mContext, PubBookDetailActivity.class);
                   intent.putExtra("detailbook", mDatas.get(0));
                   mContext.startActivity(intent);
               }
           });


           currentBook=mDatas.get(1);
           Glide.with(mContext)
                   .load(currentBook.getBookUrl())
                   .into(holder.iv_book2);

           holder.tv_bookname2.setText(currentBook.getBookName());


           holder.linear1.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent =new Intent(mContext, PubBookDetailActivity.class);
                   intent.putExtra("detailbook",mDatas.get(1));
                   mContext.startActivity(intent);
               }
           });



       }else if(mDatas.size()>=3){
           PublishBook currentBook=mDatas.get(0);
           Glide.with(mContext)
                   .load(currentBook.getBookUrl())
                   .into(holder.iv_book1);

           holder.tv_bookname1.setText(currentBook.getBookName());

           holder.linear1.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent =new Intent(mContext, PubBookDetailActivity.class);
                   intent.putExtra("detailbook",mDatas.get(0));
                   mContext.startActivity(intent);
               }
           });

           currentBook=mDatas.get(1);
           Glide.with(mContext)
                   .load(currentBook.getBookUrl())
                   .into(holder.iv_book2);
           holder.tv_bookname2.setText(currentBook.getBookName());


           holder.linear2.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent =new Intent(mContext, PubBookDetailActivity.class);
                   intent.putExtra("detailbook",mDatas.get(1));
                   mContext.startActivity(intent);
               }
           });

          currentBook=mDatas.get(2);
           Glide.with(mContext)
                   .load(currentBook.getBookUrl())
                   .into(holder.iv_book3);

           holder.tv_bookname3.setText(currentBook.getBookName());

           holder.linear3.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent =new Intent(mContext, PubBookDetailActivity.class);
                   intent.putExtra("detailbook",mDatas.get(2));
                   mContext.startActivity(intent);
               }
           });

       }


    }

    @Override
    public int getItemCount() {
        return 1;
    }


}
class SimViewHolder extends RecyclerView.ViewHolder{

    LinearLayout linear1;
    LinearLayout linear2;
    LinearLayout linear3;

    ImageView iv_book1;
    ImageView iv_book2;
    ImageView iv_book3;

    TextView tv_bookname1;
    TextView tv_bookname2;
    TextView tv_bookname3;


    public SimViewHolder(View itemView) {
        super(itemView);
        linear1= (LinearLayout) itemView.findViewById(R.id.ll_simple1);
        linear2= (LinearLayout) itemView.findViewById(R.id.ll_simple2);
        linear3= (LinearLayout) itemView.findViewById(R.id.ll_simple3);

        iv_book1= (ImageView) itemView.findViewById(R.id.iv_book1_simple);
        iv_book2= (ImageView) itemView.findViewById(R.id.iv_book2_simple);
        iv_book3= (ImageView) itemView.findViewById(R.id.iv_book3_simple);

        tv_bookname1= (TextView) itemView.findViewById(R.id.tv_bookname1_simple);
        tv_bookname2= (TextView) itemView.findViewById(R.id.tv_bookname2_simple);
        tv_bookname3= (TextView) itemView.findViewById(R.id.tv_bookname3_simple);


    }
}