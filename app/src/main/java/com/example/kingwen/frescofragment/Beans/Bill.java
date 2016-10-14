package com.example.kingwen.frescofragment.Beans;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by kingwen on 2016/6/9.
 */
public class Bill implements Parcelable {
    /**
     * 确定图片的正反，1说明是获得积分，正向。 0是反向。
     * 同时决定“借出”“借入”
     */
    private  int inOrOut;

    /**
     * 确定花费的数目
     */
    private  int billNum;

    /**
     * 相关的图书
     */
    private  String relativebook;

    /**
     * 相关用户
     */
    private  String relativePerson;

    /**
     * 相关日期
     */
    private  String relativeData;


    public Bill() {
    }

    public Bill(int inOrOut, int billNum, String relativebook, String relativePerson, String relativeData) {
        this.inOrOut = inOrOut;
        this.billNum = billNum;
        this.relativebook = relativebook;
        this.relativePerson = relativePerson;
        this.relativeData = relativeData;
    }

    public int getInOrOut() {
        return inOrOut;
    }

    public void setInOrOut(int inOrOut) {
        this.inOrOut = inOrOut;
    }

    public int getBillNum() {
        return billNum;
    }

    public void setBillNum(int billNum) {
        this.billNum = billNum;
    }

    public String getRelativebook() {
        return relativebook;
    }

    public void setRelativebook(String relativebook) {
        this.relativebook = relativebook;
    }

    public String getRelativePerson() {
        return relativePerson;
    }

    public void setRelativePerson(String relativePerson) {
        this.relativePerson = relativePerson;
    }

    public String getRelativeData() {
        return relativeData;
    }

    public void setRelativeData(String relativeData) {
        this.relativeData = relativeData;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(inOrOut);
        dest.writeInt(billNum);
        dest.writeString(relativebook);
        dest.writeString(relativePerson);
        dest.writeString(relativeData);

    }
    public static  final Parcelable.Creator<Bill> CREATOR =new Parcelable.Creator<Bill>(){
        @Override
        public Bill createFromParcel(Parcel source) {
            Bill bill=new Bill();
            bill.inOrOut=source.readInt();
            bill.billNum=source.readInt();
            bill.relativebook=source.readString();
            bill.relativePerson=source.readString();
            bill.relativeData=source.readString();
            return bill;
        }

        @Override
        public Bill[] newArray(int size) {
            return new Bill[size];
        }
    };
}
