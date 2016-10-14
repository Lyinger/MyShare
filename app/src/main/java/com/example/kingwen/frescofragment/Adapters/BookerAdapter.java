package com.example.kingwen.frescofragment.Adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kingwen.frescofragment.Beans.Booker;
import com.example.kingwen.frescofragment.Beans.PublishBook;
import com.example.kingwen.frescofragment.R;

import java.util.List;


/**
 * Created by kingwen on 2016/6/2.
 */
public class BookerAdapter extends RecyclerView.Adapter<MyBookerViewHolder> implements View.OnClickListener {

    private LayoutInflater mInflater;

    private Context mContext;

    private List<Booker> mDatas;

    /**
     * 构造方法
     *
     * @param mContext 上下文对象
     */
    public BookerAdapter(Context mContext) {
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    public BookerAdapter(Context context, List<Booker> datas) {
        this.mContext = context;
        this.mDatas = datas;

        mInflater = LayoutInflater.from(context);
        mOnItemClickListener = null;
    }


    //自定义监听接口，用于提供监听事件
    private OnRecyclerViewItemClickListener mOnItemClickListener;


    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int data);
    }


    public void setDatas(List<Booker> mData) {
        this.mDatas = mData;
    }


    public void setOnItemClickListener(OnRecyclerViewItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }


    @Override
    public MyBookerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.item_booker_layout, parent, false);
        MyBookerViewHolder viewHolder = new MyBookerViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    /**
     * 将数据源和控件进行绑定
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(MyBookerViewHolder holder, int position) {

        holder.itemView.setTag(position);

        Booker booker=mDatas.get(position);

        Glide.with(mContext)
                .load(booker.getBookerUrl())
                .into(holder.iv_booker);

        holder.tv_name_booker.setText(booker.getName());
        holder.tv_location_booker.setText(booker.getLocation());
        holder.tv_introduction_booker.setText(booker.getIntroduction());

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public void onClick(View v) {

        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
        }
    }

}

class MyBookerViewHolder extends RecyclerView.ViewHolder {

    //@BindView(R.id.iv_booker)
    ImageView iv_booker;
   // @BindView(R.id.tv_name_booker)
    TextView tv_name_booker;
   // @BindView(R.id.tv_location_booker)
    TextView tv_location_booker;
    //@BindView(R.id.tv_introduction_booker)
    TextView tv_introduction_booker;

    public MyBookerViewHolder(View itemView) {

        super(itemView);


        iv_booker= (ImageView) itemView.findViewById(R.id.iv_booker);
        tv_name_booker= (TextView) itemView.findViewById(R.id.tv_name_booker);
        tv_location_booker= (TextView) itemView.findViewById(R.id.tv_location_booker);
        tv_introduction_booker= (TextView) itemView.findViewById(R.id.tv_introduction_booker);


    }
}