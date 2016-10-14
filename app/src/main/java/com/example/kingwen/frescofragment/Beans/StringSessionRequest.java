package com.example.kingwen.frescofragment.Beans;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.example.kingwen.frescofragment.MyApplication.MyApplication;


import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kingwen on 2016/9/21.
 *
 * 自定义的request类。保存session
 * */
public class StringSessionRequest extends StringRequest {

    public StringSessionRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {

     super(method,url,listener, errorListener);

    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        //检查本地是否含有session。 如果没有那么就将session数据保存到本地
        MyApplication.newInstance().checkSessionCookie(response.headers);

        String parsed=null;
        try {
          parsed= new String(response.data, HttpHeaderParser.parseCharset(response.headers));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    //    Log.e("SessionRequest parsed",parsed);

        return Response.success(parsed,HttpHeaderParser.parseCacheHeaders(response));

    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = super.getHeaders();

        if (headers == null
                || headers.equals(Collections.emptyMap())) {
            headers = new HashMap<String, String>();
        }

        MyApplication.newInstance().addSessionCookie(headers);

        Log.e("SessionRequestgetheader",headers.toString());

        return headers;

    }

}
