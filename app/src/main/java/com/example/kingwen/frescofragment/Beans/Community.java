package com.example.kingwen.frescofragment.Beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kingwen on 2016/9/21.
 */
public class Community implements Parcelable {

    private String companyName;

    private String activityName;

    public Community(String companyName, String activityName, String activityTime, String activityContent) {
        this.companyName = companyName;
        this.activityName = activityName;
        this.activityTime = activityTime;
        this.activityContent = activityContent;
    }

    private String activityTime;

    private String activityContent;


    public Community(){

    }

    public  Community(String companyname,String activitytime,String activitycontent){
        companyName=companyname;
        activityTime=activitytime;
        activityContent=activitycontent;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(String activityTime) {
        this.activityTime = activityTime;
    }

    public String getActivityContent() {
        return activityContent;
    }

    public void setActivityContent(String activityContent) {
        this.activityContent = activityContent;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(companyName);
        dest.writeString(activityContent);
        dest.writeString(activityTime);
        dest.writeString(activityName);
    }

    public static final Creator<Community> CREATOR = new Creator<Community>() {

        @Override
        public Community createFromParcel(Parcel in) {
            Community community=new Community();

            community.companyName=in.readString();
            community.activityContent=in.readString();
            community.activityTime=in.readString();
            community.activityName=in.readString();

            return community;
        }

        @Override
        public Community[] newArray(int size) {
            return new Community[size];
        }

    };

}
