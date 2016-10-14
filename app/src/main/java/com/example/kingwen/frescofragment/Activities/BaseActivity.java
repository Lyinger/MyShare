package com.example.kingwen.frescofragment.Activities;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by kingwen on 2016/9/20.
 */
public class BaseActivity extends Activity {

    public void  showToast(String s){
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
    }
}
