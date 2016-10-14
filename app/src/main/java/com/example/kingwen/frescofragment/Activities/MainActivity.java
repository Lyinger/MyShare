package com.example.kingwen.frescofragment.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.kingwen.frescofragment.Fragments.MyBookFragment;
import com.example.kingwen.frescofragment.Fragments.MyBorrowFragment;
import com.example.kingwen.frescofragment.Fragments.MyCommunityFragment;
import com.example.kingwen.frescofragment.Fragments.MyCurrencyFragment;
import com.example.kingwen.frescofragment.Fragments.MyHomePageFragment;
import com.example.kingwen.frescofragment.Fragments.MyInformationFragment;
import com.example.kingwen.frescofragment.Fragments.MyInterestFragment;
import com.example.kingwen.frescofragment.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Fragment fragment;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setDefaultFragment();

        /**
         * 悬浮按钮
         */
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, PublishActivity.class);
                startActivity(intent);

            }
        });

        /**
         * 侧滑栏
         */
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    /**
     * 设置默认的fragment数据 ：我们的数据是camera界面
     *
     */
    private void setDefaultFragment() {

        fragment=new MyHomePageFragment();

        android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.main_contain,fragment,"ONE");
        fragmentTransaction.commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * 策划栏的设定，进行fragment的更改，添加等操作
     * @param item
     * @return
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {


        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id==R.id.nav_homepage){
            //主页
            fragment=new MyHomePageFragment();
        }else if(id==R.id.nav_mybook){
            //我的图书
         fragment=new MyBookFragment();
        }else if(id==R.id.nav_myborrow){
            //我的借阅
            fragment=new MyBorrowFragment();
        }else if (id==R.id.nav_myinterest){
            //我的关注
            fragment=new MyInterestFragment();
        }else if(id==R.id.nav_currency){
            //我的书币
            fragment=new MyCurrencyFragment();
        }else if(id==R.id.nav_myinformation){
            fragment=new MyInformationFragment();
        }else if(id==R.id.nav_mycommunity){
            fragment=new MyCommunityFragment();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_contain,fragment).commit();
        return true;
    }






}
