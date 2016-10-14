package com.example.kingwen.frescofragment.Beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kingwen on 2016/6/10.
 *
 * 个人信息资料，同时是查看自己关注好友的数据源
 */
public class Booker implements Parcelable {

    /**
     * 头像
     */
    private  String bookerUrl;

    /**
     * 昵称
     */
    private  String name;

    /**
     * 邮箱
     */
    private  String mailNum;

    /**
     * 是否为学生
     */
    private   int isStudent;

    /**
     * 家庭住址
     */
    private  String location;

    /**
     * 自我介绍
     */
    private  String introduction;


    public Booker() {}




    public String getBookerUrl() {
        return bookerUrl;
    }

    public void setBookerUrl(String bookerUrl) {
        this.bookerUrl = bookerUrl;
    }

    public int getIsStudent() {
        return isStudent;
    }

    public void setIsStudent(int isStudent) {
        this.isStudent = isStudent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Booker(String bookerUrl, String name, String mailNum, int isStudent, String location, String introduction) {
        this.bookerUrl = bookerUrl;
        this.name = name;
        this.mailNum = mailNum;
        this.isStudent = isStudent;
        this.location = location;
        this.introduction = introduction;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIntroduction() {
        return introduction;
    }

    public String getMailNum() {
        return mailNum;
    }

    public void setMailNum(String mailNum) {
        this.mailNum = mailNum;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(bookerUrl);
        dest.writeString(name);
        dest.writeString(mailNum);
        dest.writeInt(isStudent);
        dest.writeString(location);
        dest.writeString(introduction);

    }

     public static final Creator<Booker> CREATOR = new Creator<Booker>() {

         @Override
         public Booker createFromParcel(Parcel in) {
            Booker booker=new Booker();
             booker.setBookerUrl(in.readString());
             booker.setName(in.readString());
             booker.setMailNum(in.readString());
             booker.setIsStudent(in.readInt());
             booker.setLocation(in.readString());
             booker.setIntroduction(in.readString());
             return booker;
         }

         @Override
         public Booker[] newArray(int size) {
             return new Booker[size];
         }

     };
}
