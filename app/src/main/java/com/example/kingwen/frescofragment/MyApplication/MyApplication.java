package com.example.kingwen.frescofragment.MyApplication;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.kingwen.frescofragment.Beans.Booker;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by kingwen on 2016/9/20.
 */
public class MyApplication extends Application {

    private static final String SET_COOKIE_KEY = "Set-Cookie";
    private static final String COOKIE_KEY = "Cookie";
    //private static final String COOKIE_KEY = "Token";
    private static final String SESSION_COOKIE = "JSESSIONID";

    private static MyApplication instance;
    private RequestQueue requestQueue;
    private SharedPreferences preferences;

    public static MyApplication newInstance() {
        return instance;
    }

    //设置当前用户
    private Booker currentBooker;

    public Booker getCurrentBooker() {
        if(currentBooker==null){
            return new Booker("url","李庆文","13075313106@163.com",1,"济南","本科在读，伪程序员");
        }
        return currentBooker;
    }

    public void setCurrentBooker(Booker currentBooker) {
        this.currentBooker = currentBooker;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        requestQueue = Volley.newRequestQueue(this);
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }


    /**
     * 检查返回的Response header中有没有session
     * @param responseHeaders Response Headers.
     */
    public final void checkSessionCookie(Map<String, String> responseHeaders) {

       Log.e("responce", responseHeaders.toString());

        //便利得到所有的header
        Collection<String> strings = responseHeaders.values();
        Iterator iterator =strings.iterator();
        for(;iterator.hasNext();){
            Log.e("iterator", iterator.next() + "");
        }


   /*     Log.e("judge1",responseHeaders.containsKey(SET_COOKIE_KEY)+"");

        Log.e("judge2", responseHeaders.get(SET_COOKIE_KEY).startsWith(SESSION_COOKIE) + "");

        Log.e("judge3", responseHeaders.get(SET_COOKIE_KEY).toString() + "");

        Log.e("judge4", responseHeaders.get(SET_COOKIE_KEY).length() + "");*/

        if (responseHeaders.containsKey(SET_COOKIE_KEY) ) {
            String cookie = responseHeaders.get(SET_COOKIE_KEY);
            if (cookie.length() > 0) {
                String[] splitCookie = cookie.split(";");
                String[] splitSessionId = splitCookie[0].split("=");
                cookie = splitSessionId[1];
                SharedPreferences.Editor prefEditor = preferences.edit();
                prefEditor.putString(SESSION_COOKIE, cookie);

                Log.e("checkSessionCookie", cookie);

                prefEditor.commit();
            }
        }

       // Log.e("checkSessionCookie","yunxing ");
    }

    /**
     * 添加session到Request header中
     */
    public final void addSessionCookie(Map<String, String> requestHeaders) {
        String sessionId = preferences.getString(SESSION_COOKIE, "");
        if (sessionId.length() > 0) {
            StringBuilder builder = new StringBuilder();
            builder.append(SESSION_COOKIE);
            builder.append("=");
            builder.append(sessionId);
            if (requestHeaders.containsKey(COOKIE_KEY)) {
                builder.append("; ");
                builder.append(requestHeaders.get(COOKIE_KEY));
            }
            requestHeaders.put(COOKIE_KEY, builder.toString());

            Log.e("Cookie", COOKIE_KEY);
            Log.e("builser",builder.toString());

            Log.e("requestheader", requestHeaders.toString());


        }
    }

}


