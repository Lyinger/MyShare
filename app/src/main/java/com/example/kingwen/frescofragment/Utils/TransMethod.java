package com.example.kingwen.frescofragment.Utils;

import android.content.Context;
import android.widget.Toast;

import com.example.kingwen.frescofragment.Beans.PublishBook;
import com.example.kingwen.frescofragment.Constants.Books;
import com.example.kingwen.frescofragment.Constants.Nets;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by kingwen on 2016/6/6.
 */
public class TransMethod {

    /**
     * 处理书的方式：1送 2 换  3卖  4借
     * @param i 书里书的方式代号
     * @return  具体的处理方式
     */
    public static final String GetBookWay(int i){

        switch (i){
            case 1:
                return "倾情赠送";

            case 2:

                return "以书换书";
            case 3:

                return "钱货两清";
            case 4:

                return "好借好还";

            default:{
                return  "你觉得呢";
            }
        }
    }

    /**
     * activity传递数据的时候用于返回要搜索的东西
     * @param i  输入的id号  1小说2传记3生活4专业5期刊杂志6其他
     * @return   相应的搜索网址
     */
    public static  final String getUrlById(int i){
        switch (i){
            case 1:
                return Nets.NET_SEARCH_NOVEL;
            case 2:
                return Nets.NET_SEARCH_BIOGRAPHY;
            case 3:
                return Nets.NET_SEARCH_LIFE;
            case 4:
                return Nets.NET_SEARCH_PROFESSIONAL;
            case 5:
                return Nets.NET_SEARCH_JOURNAL;
            case 6:
                return Nets.NET_SEARCH_OTHER;
          default:
                return getUrlById(1);
        }

    }


    /**
     *将我们从网页中请求的数据处理，放到一个链表中，作为adapter的数据源
     * @param responce
     * @return
     */
    public static final ArrayList<PublishBook> getArrFromResponce(String responce) {
        JSONArray array;
        JSONObject resopnseobject;
        PublishBook book;

        ArrayList<PublishBook> mDatas=null;

        /**
         * 测试数据
         * */
/*
        String bookurl="http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E9%98%BF%E5%BC%A5%E9%99%80%E4%BD%9B%E4%B9%88%E4%B9%88%E5%93%92&step_word=&pn=3&spn=0&di=100919363050&pi=&rn=1&tn=baiduimagedetail&is=&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=undefined&cs=1962149298%2C2674395814&os=514813962%2C1331395615&simid=0%2C0&adpicid=0&ln=1000&fr=&fmq=1465406172709_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fimg3.douban.com%2Fview%2Fnote%2Flarge%2Fpublic%2Fp32048940.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3B157kwg_z%26e3Bv54AzdH3Fg5pjAzdH3Fc98bdaadbAzdH3F%3Fpyrj%3D6jv&gsm=0&rpstart=0&rpnum=0";
        String nickurl="http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=false&word=%E5%A4%B4%E5%83%8F&pn=14&spn=0&di=18600526370&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&ie=utf-8&oe=utf-8&in=3354&cl=2&lm=-1&cs=3145304103%2C1465502637&os=1840626504%2C1367830110&simid=0%2C0&adpicid=0&fr=ala&fm=&sme=&statnum=head&cg=head&bdtype=11&oriquery=%E5%A4%B4%E5%83%8F&objurl=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201503%2F15%2F20150315150939_AWHRV.jpeg&fromurl=http%3A%2F%2Fwww.duitang.com%2Fblog%2F%3Fid%3D566537072&gsm=0";

        PublishBook testbook=new PublishBook(bookurl,"阿弥陀佛么么哒","大冰","一本让人感到心暖的书，一本让人不忍心读完的书",1,2,
                         "这是我特别喜欢的一本书，喜欢我冰叔，希望你也喜欢",nickurl,"双生蝶");

        mDatas.add(testbook);
        mDatas.add(testbook);
        mDatas.add(testbook);*/
        try {
            array = new JSONArray(responce);

            mDatas = new ArrayList<PublishBook>();

            int length = array.length();

            for (int i = 0; i < length; i++) {

                book = new PublishBook();

                resopnseobject = array.getJSONObject(i);
                book.setBookid(resopnseobject.getInt("bid"));
                book.setBookName(resopnseobject.getString("bookname"));
                book.setBookAuthor(resopnseobject.getString("bookauthor"));
                book.setBookSummary(resopnseobject.getString("booksummary"));
                book.setBookAssortment(Integer.parseInt(resopnseobject.getString("bookassortment")));
                book.setBookWay(Integer.parseInt(resopnseobject.getString("bookway")));
                book.setBookerSay(resopnseobject.getString("bookersay"));
               // book.setBookUrl(resopnseobject.getString("bookurl"));
                //book.setNickName(resopnseobject.getString("nickname"));
                //book.setNickUrl(resopnseobject.getString("nickurl"));

                mDatas.add(book);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return mDatas;

    }

    /**
     * 用于显示toast的一个简便方法
     * @param context 上下文对象
     * @param s   需要弹出的话
     */
    public static final void ShowToast(Context context,String s){

        Toast.makeText(context,s,Toast.LENGTH_SHORT).show();

    }

    public static final ArrayList<PublishBook> getBookData(int i){
        ArrayList<PublishBook> mData=new ArrayList<PublishBook>();
        switch (i){
            case 1:
                mData.add(Books.book_story_heyisheng);
                mData.add(Books.book_story_wanmei);
                return mData;

            case 2:
                mData.add(Books.book_bio_fangao);
                mData.add(Books.book_bio_deng);
                mData.add(Books.book_bio_jobs);
                return mData;

            case 3:
                mData.add(Books.book_life_bodylove);
                return mData;

            case 4:
                mData.add(Books.book_android);
                mData.add(Books.book_java);
                return mData;

            case 5:
                mData.add(Books.book_journal_guojiadili);
                mData.add(Books.book_journal_lonely);
                mData.add(Books.book_journal_reader);
                return mData;

            case 6:
                mData.add(Books.book_other_buddha);
                mData.add(Books.book_other_lonelyandhonor);
                return mData;

            default:
                return mData;

        }
    }


}