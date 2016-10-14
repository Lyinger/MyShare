package com.example.kingwen.frescofragment.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kingwen.frescofragment.Constants.Nets;
import com.example.kingwen.frescofragment.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kingwen on 2016/6/10.
 */
public class RegisterActivity extends Activity {

    EditText registerEtName;
    EditText registerEtEmail;
    EditText registerEtPas;
    EditText registerEtPass;
    Button btn_register;

    RequestQueue mQueue ;
    StringRequest stringRequest;

    String nickName,mailName,password,enpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_layout);

        initViews();

        initNets();

        initListener();

    }

    private void initListener() {

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isLegal()) {
                    Log.e("btn_register","add request");
                    mQueue.add(stringRequest);
                }
            }
        });

    }

    private boolean isLegal() {
        nickName=registerEtName.getText().toString();
        mailName=registerEtEmail.getText().toString();
        password=registerEtPas.getText().toString();
        enpassword=registerEtPass.getText().toString();

        if("".equals(nickName)||"".equals(mailName)||"".equals(password)||(!password.equals(enpassword)))
        {
            Log.e("islegal","false");
            return false;
        }
        return true;
    }

    private void initViews() {
        registerEtName = (EditText) findViewById(R.id.register_name);
        registerEtEmail = (EditText) findViewById(R.id.register_email);
        registerEtPas = (EditText) findViewById(R.id.register_passward);
        registerEtPass = (EditText) findViewById(R.id.register_passward_confirm);
        btn_register = (Button) findViewById(R.id.register_confirm);

        Log.e("register ","initview");
    }

    private void initNets() {

        mQueue = Volley.newRequestQueue(RegisterActivity.this);

        stringRequest = new StringRequest(Request.Method.POST,
                Nets.NET_REGISTER,
                new Response.Listener<String>()
                {
                    public void onResponse(String s)
                    {
                        Log.e("register",s);
                        if(s.equals("1"))
                        {
                            Intent intent = new Intent();
                            intent.setClass(RegisterActivity.this, MainActivity.class);
                            startActivity(intent);
                        }else {
                            Log.e("register","不是1");
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    public void onErrorResponse(VolleyError volleyError)
                    {

                        Log.e("register","fail to internet");
                        Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_LONG).show();


                    }
                }) {

            protected Map<String, String> getParams() throws AuthFailureError {

              /*
              *传参数
              * */
                Map<String, String> map = new HashMap<String, String>();
                map.put("nickname",nickName);
                map.put("email",mailName);
                map.put("password",password);
                return map;

            }
        };

    }
}
