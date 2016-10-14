package com.example.kingwen.frescofragment.Beans;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import java.io.Serializable;
import java.net.URL;

/**
 * Created by kingwen on 2016/6/2.
 */
public class PublishBook implements Parcelable {

    private int uid;

    private int bookid;

    /**
     * 书的封面

     */
    private String bookUrl;
    /**
     * 书名
     */
    private  String bookName;

    /**
     * 作者
     */
    private  String bookAuthor;

    /**
     * 简介
     */
    private  String bookSummary;

    /**
     * 书籍分类 1小说2传记3生活4专业5期刊杂志6其他
     */
    private int bookAssortment;

    /**
     * 借书方式,参见常量1送 2 换  3卖  4借
     */

    private  int bookWay;

    /**
     * 我想说,针对于作者的看法或者对对方的看法的一些建议
     */
    private String bookerSay;

    /**
     * 发布者的头像
     */
    private String nickUrl;

    /**

     * 发布者的昵称
     */
    private  String nickName;

    /**
     * 没有参数的构造方法
     * */
    public PublishBook() {
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }


    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }


    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNickUrl() {
        return nickUrl;
    }

    public void setNickUrl(String nickUrl) {
        this.nickUrl = nickUrl;
    }

    public String getBookerSay() {
        return bookerSay;
    }

    public void setBookerSay(String bookerSay) {
        this.bookerSay = bookerSay;
    }

    public int getBookWay() {
        return bookWay;
    }

    public void setBookWay(int bookWay) {
        this.bookWay = bookWay;
    }

    public int getBookAssortment() {
        return bookAssortment;
    }

    public void setBookAssortment(int bookAssortment) {
        this.bookAssortment = bookAssortment;
    }

    public String getBookSummary() {
        return bookSummary;
    }

    public void setBookSummary(String bookSummary) {
        this.bookSummary = bookSummary;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookUrl() {
        return bookUrl;
    }

    public void setBookUrl(String bookUrl) {
        this.bookUrl = bookUrl;
    }

    public PublishBook(String bookUrl, String bookName, String bookAuthor, String bookSummary, int bookAssortment, int bookWay, String bookerSay, String nickUrl, String nickName) {
        this.bookUrl = bookUrl;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookSummary = bookSummary;
        this.bookAssortment = bookAssortment;
        this.bookWay = bookWay;
        this.bookerSay = bookerSay;
        this.nickUrl = nickUrl;
        this.nickName = nickName;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(uid);
        dest.writeInt(bookid);
        dest.writeString(bookUrl);
        dest.writeString(bookName);
        dest.writeString(bookAuthor);
        dest.writeString(bookSummary);
        dest.writeInt(bookAssortment);
        dest.writeInt(bookWay);
        dest.writeString(bookerSay);
        dest.writeString(nickUrl);
        dest.writeString(nickName);

    }

    public static  final Parcelable.Creator<PublishBook> CREATOR =new Parcelable.Creator<PublishBook>(){
        @Override
        public PublishBook createFromParcel(Parcel source) {
            PublishBook book=new PublishBook();
            book.uid=source.readInt();
            book.bookid=source.readInt();
            book.bookUrl=source.readString();
            book.bookName=source.readString();
            book.bookAuthor=source.readString();
            book.bookSummary=source.readString();
            book.bookAssortment=source.readInt();
            book.bookWay=source.readInt();
            book.bookerSay=source.readString();
            book.nickUrl=source.readString();
            book.nickName=source.readString();
            return book;
        }

        @Override
        public PublishBook[] newArray(int size) {
            return new PublishBook[size];
        }
    };
}
