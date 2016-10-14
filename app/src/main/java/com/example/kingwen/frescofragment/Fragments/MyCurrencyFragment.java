package com.example.kingwen.frescofragment.Fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kingwen.frescofragment.Adapters.BillAdapter;
import com.example.kingwen.frescofragment.Beans.Bill;
import com.example.kingwen.frescofragment.Beans.PublishBook;
import com.example.kingwen.frescofragment.Constants.Books;
import com.example.kingwen.frescofragment.Constants.Nets;
import com.example.kingwen.frescofragment.R;
import com.example.kingwen.frescofragment.Utils.TransMethod;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by kingwen on 2016/6/5.
 */
public class MyCurrencyFragment extends Fragment {

    /**
     * 我们账单的数据源
     */
    private  ArrayList<Bill>  mBillData;

    /**
     * 账单适配器
     */
    private BillAdapter  billAdapter;

    /**
     * 上下文
     */
    private Context mContext;

    /**
     * 上下文
     */
    private Activity mActivity;

    /**
     * 网络请求队列
     */
    private RequestQueue mQueue;

    /**
     * TAG
     */
    private  final String TAG="我的书币界面";

    /**
     * 显示账单的recycleview
     */
   // @BindView(R.id.rv_mycurrency)
    RecyclerView rv_currency;


    /**
     * 显示书币总金额
     */
    //@BindView(R.id.tv_sum_summary)
    TextView tv_sum;


    /**
     * 显示总金额
     */
    public  static  int  summarySum=100;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivity=getActivity();
        mContext=getActivity();

        initDatas();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View  view=inflater.inflate(R.layout.fragment_mycurrency_layout,container,false);

        rv_currency= (RecyclerView) view.findViewById(R.id.rv_mycurrency);
        tv_sum= (TextView) view.findViewById(R.id.tv_sum_summary);


        billAdapter=new BillAdapter(mContext,mBillData);
        rv_currency.setLayoutManager(new LinearLayoutManager(mContext));
        rv_currency.setItemAnimator(new DefaultItemAnimator());
        rv_currency.setAdapter(billAdapter);

        tv_sum.setText(summarySum+"");

        return view;
    }

    private void initDatas() {

        mBillData=new ArrayList<Bill>();
        mQueue= Volley.newRequestQueue(mContext);
        StringRequest billRequest=new StringRequest(Request.Method.POST, Nets.NET_MYSUBSCRIPTION, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "initDatalBill" + response);

                JSONArray array;
                JSONObject resopnseobject;
                Bill bill;

                try {
                    array = new JSONArray(response);
                    mBillData = new ArrayList<Bill>();
                    int length = array.length();

                    for (int i = 0; i < length; i++) {

                        bill = new Bill();

                        resopnseobject = array.getJSONObject(i);

                        bill.setInOrOut(resopnseobject.getInt("inOrOut"));
                        bill.setBillNum(resopnseobject.getInt("billNum"));
                        bill.setRelativebook(resopnseobject.getString("relativeBook"));
                        bill.setRelativePerson(resopnseobject.getString("relativePerson"));
                        bill.setRelativeData(resopnseobject.getString("relativeData"));

                        if(resopnseobject.getInt("inOrOut")>0){
                            summarySum+=resopnseobject.getInt("billNum");
                        }else{
                            summarySum-=resopnseobject.getInt("billNum");
                        }

                        mBillData.add(bill);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                TransMethod.ShowToast(mContext,"获取数据失败，请刷新重试");
            }
        });

        mQueue.add(billRequest);

    }
}
