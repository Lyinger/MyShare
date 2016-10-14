package com.example.kingwen.frescofragment.Adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kingwen.frescofragment.Beans.PublishBook;
import com.example.kingwen.frescofragment.Constants.Books;
import com.example.kingwen.frescofragment.R;
import com.example.kingwen.frescofragment.Utils.TransMethod;

import java.util.List;

/**
 * Created by kingwen on 2016/6/2.
 */
public class BookAdapter extends RecyclerView.Adapter<MyViewHolder> implements View.OnClickListener{

    private LayoutInflater mInflater;

    private Context mContext ;

    private List<PublishBook> mDatas;

    /**
     * 构造方法
     * @param mContext  上下文对象
     */
    public BookAdapter(Context mContext) {
        this.mContext = mContext;
        mInflater=LayoutInflater.from(mContext);
    }

    public BookAdapter(Context context, List<PublishBook> datas){
        this.mContext=context;
        this.mDatas=datas;

        mInflater=LayoutInflater.from(context);
        mOnItemClickListener=null;
    }

    //自定义监听接口，用于提供监听事件
    private OnRecyclerViewItemClickListener mOnItemClickListener;

    public interface  OnRecyclerViewItemClickListener{
        void onItemClick(View view,int data);
    }


    public  void setDatas(List<PublishBook> mData){
        this.mDatas=mData;
    }

    public void setData(PublishBook book){
        mDatas.add(book);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=mInflater.inflate(R.layout.item_recbook_layout,parent,false);
        MyViewHolder viewHolder=new MyViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    /**
     * 将我们的数据和我们的控件进行绑定
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.itemView.setTag(position);

        PublishBook currentBook=mDatas.get(position);
        holder.tv_bookname.setText(currentBook.getBookName());
        /**
         * 需要一个方法进行转变。将参数传进来之后输出我们的文字
         */
        holder.tv_bookway.setText(TransMethod.GetBookWay(currentBook.getBookWay()));

        holder.tv_bookauthor.setText(currentBook.getBookAuthor());

        holder.tv_booksummary.setText(currentBook.getBookSummary());

        holder.tv_nickname.setText(currentBook.getNickName());

        /**
         * 使用glide库进行设定我们的照片
         */

        Glide.with(mContext)
                .load(currentBook.getBookUrl())
                .into(holder.iv_book);

        Glide.with(mContext)
                .load(currentBook.getNickUrl())
                .into(holder.iv_nickphoto);

    }

    @Override
    public int getItemCount() {

        if(mDatas==null){
            Log.e("mAdapter","mDatas为空");
        }

        return mDatas.size();
    }

    @Override
    public void onClick(View v) {

        if(mOnItemClickListener!=null){

            Log.e("bookAdapter tag",(Integer) v.getTag()+"");
            mOnItemClickListener.onItemClick(v,(Integer) v.getTag());

        }
    }

}
class MyViewHolder extends RecyclerView.ViewHolder{

    ImageView iv_book;
    TextView tv_bookname;
    TextView tv_bookauthor;
    TextView tv_bookway;
    TextView tv_booksummary;
    ImageView iv_nickphoto;
    TextView  tv_nickname;

    public MyViewHolder(View itemView) {

        super(itemView);
        tv_bookname= (TextView) itemView.findViewById(R.id.tv_bookname_item);
        iv_book= (ImageView) itemView.findViewById(R.id.iv_book_item);
        tv_bookauthor= (TextView) itemView.findViewById(R.id.tv_author_item);
        tv_bookway= (TextView) itemView.findViewById(R.id.tv_bookway_item);
        tv_booksummary = (TextView) itemView.findViewById(R.id.tv_summary_item);
        iv_nickphoto= (ImageView) itemView.findViewById(R.id.iv_photo_item);
        tv_nickname= (TextView) itemView.findViewById(R.id.tv_nickname_item);

    }
}