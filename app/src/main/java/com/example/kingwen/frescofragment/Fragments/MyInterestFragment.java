package com.example.kingwen.frescofragment.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kingwen.frescofragment.R;

import java.util.ArrayList;


/**
 * Created by kingwen on 2016/6/2.
 */
public class MyInterestFragment extends Fragment implements View.OnClickListener,ViewPager.OnPageChangeListener {

    /**
     * tab标签1
     */
   // @BindView(R.id.tab1_tv)
    TextView tv_tab1;
    /**
     * tab标签2
     */
  //  @BindView(R.id.tab2_tv)
    TextView tv_tab2;
    /**
     * 游标位置
     */
   // @BindView(R.id.cursor)
    ImageView iv_cursor;

    /**
     * viewpager
     */
   // @BindView(R.id.vp_fragActivity)
    ViewPager mViewPager;

    /**
     * 手机屏幕宽度
     */
    private  int screenWidth=0;

    /**
     * 手机屏幕1/2宽度
     */
    private  int screen1_2;

    /**
     * 左侧间隔
     */
    private  int leftMargin;

    /**
     * 当前的索引，用来表示当前是第几个tab
     */
    private  int currentIndex;

    /**
     * 并列函数
     */
    private int  offset=0;

    /**
     * 用于存放所有fragment的链表
     */
    private ArrayList<Fragment> fragmentList;

    /**
     * 用来保存我们的iv_cursor的布局参数，方便于我们后期进行更一步的更改，比如说换位置等
     */
    private LinearLayout.LayoutParams lp=null;

    /**
     * 碎片
     */
    private android.support.v4.app.Fragment fragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragmentList=new ArrayList<Fragment>();

        initDatas();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myinterest_layout, container, false);


        iv_cursor= (ImageView) view.findViewById(R.id.cursor);
        mViewPager= (ViewPager) view.findViewById(R.id.vp_fragActivity);

        tv_tab1= (TextView) view.findViewById(R.id.tab1_tv);
        tv_tab2= (TextView) view.findViewById(R.id.tab2_tv);


        initSurface();

        initViewpager();

        initListeners();

        return view;
    }

    private void initListeners() {

        tv_tab1.setOnClickListener(this);
        tv_tab2.setOnClickListener(this);

    }

    private void initViewpager() {

        mViewPager.setCurrentItem(0);
        mViewPager.setAdapter(new MyFragmentPagerAdapter(getFragmentManager(),fragmentList));
        mViewPager.addOnPageChangeListener(this);
    }


    private void initDatas() {

        //添加第一个fragment
        fragment=new MyInterestBookFragment();
        fragmentList.add(fragment);

        //添加第二个fragment
        fragment=new MyInterestPersonFragment();
        fragmentList.add(fragment);

    }
    private void initSurface() {


        DisplayMetrics dm=new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth=dm.widthPixels;
        screen1_2=screenWidth/2;

        lp= (LinearLayout.LayoutParams)iv_cursor.getLayoutParams();
        leftMargin=lp.leftMargin;

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.tab1_tv:
                mViewPager.setCurrentItem(0);
                break;

            case R.id.tab2_tv:
                mViewPager.setCurrentItem(1);
                break;

        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        offset=(screen1_2-iv_cursor.getLayoutParams().width)/2;

        Log.d("111", position + "--" + positionOffset + "--"
                + positionOffsetPixels);
        final float scale = getResources().getDisplayMetrics().density;
        if (position == 0) {// 0<->1
            lp.leftMargin = (int) (positionOffsetPixels / 2) + offset;
        }
        iv_cursor.setLayoutParams(lp);
        currentIndex = position;

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
class MyFragmentPagerAdapter extends FragmentPagerAdapter{

    private ArrayList<Fragment> fragmentArrayList;

    public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragmentArrayList) {
        super(fm);
        this.fragmentArrayList = fragmentArrayList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }
}