package com.example.kingwen.frescofragment.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kingwen.frescofragment.Beans.Community;
import com.example.kingwen.frescofragment.Beans.PublishBook;
import com.example.kingwen.frescofragment.R;
import com.example.kingwen.frescofragment.Utils.TransMethod;

import java.util.List;

/**
 * Created by kingwen on 2016/9/21.
 *
 * 用于显示公司发布活动
 */
public class CommunityAdapter extends RecyclerView.Adapter<MyCommunityViewHolder> implements View.OnClickListener{
    private LayoutInflater mInflater;

    private Context mContext ;

    private List<Community> mDatas;

        /**
         * 构造方法
         * @param mContext  上下文对象
         */
        public CommunityAdapter(Context mContext) {
            this.mContext = mContext;
            mInflater=LayoutInflater.from(mContext);
        }

        public CommunityAdapter(Context context, List<Community> datas){
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


        public  void setDatas(List<Community> mData){
            this.mDatas=mData;
        }

        public void setData(Community community){
            mDatas.add(community);
            notifyDataSetChanged();
        }

        public void setOnItemClickListener(OnRecyclerViewItemClickListener mOnItemClickListener) {
            this.mOnItemClickListener = mOnItemClickListener;
        }

        @Override
        public MyCommunityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view=mInflater.inflate(R.layout.item_company_layout,parent,false);

            MyCommunityViewHolder viewHolder=new MyCommunityViewHolder(view);
            view.setOnClickListener(this);
            return viewHolder;
        }

    @Override
    public void onBindViewHolder(MyCommunityViewHolder holder, int position) {

        holder.itemView.setTag(position);

        Community community=mDatas.get(position);

        holder.tv_activityname.setText(community.getActivityName());
        holder.tv_activitytime.setText(community.getActivityTime());
        holder.tv_companyname.setText(community.getCompanyName());

    }

        @Override
        public int getItemCount() {

            if(mDatas==null){
                Log.e("mAdapter", "mDatas为空");
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
    class MyCommunityViewHolder extends RecyclerView.ViewHolder{

        TextView tv_companyname;
        TextView tv_activityname;
        TextView tv_activitytime;


        public MyCommunityViewHolder(View itemView) {
            super(itemView);
              tv_companyname= (TextView) itemView.findViewById(R.id.companyname);
              tv_activityname= (TextView) itemView.findViewById(R.id.activityname);
               tv_activitytime= (TextView) itemView.findViewById(R.id.companytime);


        }
    }

