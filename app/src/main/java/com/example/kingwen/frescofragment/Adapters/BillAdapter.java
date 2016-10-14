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
import com.example.kingwen.frescofragment.Beans.Bill;
import com.example.kingwen.frescofragment.R;

import java.util.List;

/**
 * Created by kingwen on 2016/6/9.
 */
public class BillAdapter extends RecyclerView.Adapter<BillViewHolder> {

    private LayoutInflater mInflater;

    private Context mContext;

    private List<Bill> mDatas;

    /**
     * 构造方法
     * @param mContext  上下文对象
     */
    public BillAdapter(Context mContext) {
        this.mContext = mContext;
        mInflater=LayoutInflater.from(mContext);
    }

    public BillAdapter(Context context, List<Bill> datas){
        this.mContext=context;
        this.mDatas=datas;

        mInflater=LayoutInflater.from(context);
    }


    public  void setDatas(List<Bill> mData){
        this.mDatas=mData;
    }


    @Override
    public BillViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=mInflater.inflate(R.layout.item_bill_layout, parent, false);
        BillViewHolder viewHolder=new BillViewHolder(view);
        return viewHolder;
    }

    /**
     * 将我们的数据和我们的控件进行绑定
     */
    @Override
    public void onBindViewHolder(BillViewHolder holder, int position) {
        Bill bill=mDatas.get(position);

        int moneyin=bill.getInOrOut();
        if(moneyin==1){
            Glide.with(mContext).load(R.drawable.img_getmoneys).into(holder.iv_inOrOut);
            holder.tv_inOrOut.setText(R.string.mycurrency_lend);
            holder.tv_plusminus.setText(R.string.mycurrency_plus);
        }else{
            Glide.with(mContext).load(R.drawable.img_losemoneys).into(holder.iv_inOrOut);
            holder.tv_inOrOut.setText(R.string.mycurrency_borrow);
            holder.tv_plusminus.setText(R.string.mycurrency_minus);
        }


        if(holder.tv_billnum==null){
            Log.e("billAdapter","tv_billNum为空");
        }


        holder.tv_billnum.setText(bill.getBillNum()+"");

        holder.tv_book.setText(bill.getRelativebook());

        holder.tv_relativeperson.setText(bill.getRelativePerson());

        holder.tv_relativedata.setText(bill.getRelativeData());

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

}
class BillViewHolder extends RecyclerView.ViewHolder{

    /**
     * 正负  + —
     */
   // @BindView(R.id.tv_plusminus_billitem)
    TextView tv_plusminus;

   // @BindView(R.id.tv_billnum_billitem)
    TextView tv_billnum;

    //@BindView(R.id.tv_inout_billitem)
    TextView tv_inOrOut;

   // @BindView(R.id.tv_book_billitem)
    TextView tv_book;

   // @BindView(R.id.tv_relativeperson_billitem)
    TextView tv_relativeperson;

    //@BindView(R.id.tv_relativedata_billitem)
    TextView tv_relativedata;

  //   @BindView(R.id.iv_moneyin_billitem)
     ImageView iv_inOrOut;

    public BillViewHolder(View itemView) {
        super(itemView);


        tv_plusminus= (TextView) itemView.findViewById(R.id.tv_plusminus_billitem);
        tv_billnum= (TextView) itemView.findViewById(R.id.tv_billnum_billitem);
        tv_inOrOut= (TextView) itemView.findViewById(R.id.tv_inout_billitem);
        tv_book= (TextView) itemView.findViewById(R.id.tv_book_billitem);
        tv_relativeperson= (TextView) itemView.findViewById(R.id.tv_relativeperson_billitem);
        tv_relativedata= (TextView) itemView.findViewById(R.id.tv_relativedata_billitem);

        iv_inOrOut= (ImageView) itemView.findViewById(R.id.iv_moneyin_billitem);

    }
}
