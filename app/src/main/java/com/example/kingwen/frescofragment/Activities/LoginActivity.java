package com.example.kingwen.frescofragment.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kingwen.frescofragment.Beans.Booker;
import com.example.kingwen.frescofragment.Beans.StringSessionRequest;
import com.example.kingwen.frescofragment.Constants.Nets;
import com.example.kingwen.frescofragment.MyApplication.MyApplication;
import com.example.kingwen.frescofragment.R;
import com.example.kingwen.frescofragment.Utils.DoubleClickJuage;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by kingwen on 2016/6/10.
 */
public class LoginActivity extends BaseActivity {
    private Button btnRegister;
    private Button btnLogin;
    private EditText etNickName;
    private EditText etPassword;

    private String nickName,password;


    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);

        initViews();

        initListeners();

    }

    private void initNets() {
        mQueue= MyApplication.newInstance().getRequestQueue();

        StringSessionRequest stringSessionRequest=new StringSessionRequest(Request.Method.POST, Nets.NET_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.equals("1")) {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);

                        } else if (response.equals("0")) {
                            Toast.makeText(LoginActivity.this, "用户名或者密码错误", Toast.LENGTH_SHORT).show();
                        } else if (response.equals("-1")) {

                         //   Log.e("response", "-1");
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG1", error.getMessage(), error);
                Toast.makeText(LoginActivity.this, "网络有点问题", Toast.LENGTH_SHORT).show();
            }
        }
        )
            {
                protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("nickname", nickName);
                params.put("password", password);
                return params;
            }

        };

        mQueue.add(stringSessionRequest);


    }

    private void initListeners() {
        //登录
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * 快速点击避免
                 */
                if(DoubleClickJuage.isFastDoubleClick()){
                    return;
                }

                nickName = etNickName.getText().toString();
                password = etPassword.getText().toString();


                Log.e("login activity", nickName + password);

                if (nickName.equals("")) {
                    Toast.makeText(LoginActivity.this, "请输入你的用户名", Toast.LENGTH_SHORT).show();
                }
                if (password.equals("")) {
                    Toast.makeText(LoginActivity.this, "请输入你的密码", Toast.LENGTH_SHORT).show();
                } else {
                    initNets();

                }

            }
        });


        //注册按钮
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //设置当前用户 .和后台这个地方应该怎么办
                Booker booker=new Booker();

                MyApplication  application= (MyApplication) getApplication();
                application.setCurrentBooker(booker);



               //跳转当前页面
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);

            }
        });
    }

    private void initViews() {
        etNickName = (EditText) findViewById(R.id.login_name);
        etPassword = (EditText) findViewById(R.id.login_password);
        btnLogin = (Button) findViewById(R.id.login_confirm);
        btnRegister = (Button) findViewById(R.id.login_register);

    }
}
