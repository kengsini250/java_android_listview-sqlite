package com.example.db_test;

import android.database.sqlite.SQLiteDatabase;

public class WebData {

    private String Name;
    private String Info;
    private String Url;

    WebData()
    {
        Name ="";
        Info="";
        Url="";
    }

    WebData(String n,String i,String u)
    {
        this.Name=n;
        this.Info=i;
        this.Url=u;
    }

    @Override
    public String toString()
    {
        return this.Name;
    }

    public String getName()
    {
        return Name;
    }

    public void setName(String n){Name = n;}

    public String getInfo() {
        return Info;
    }

    public void setInfo(String i){Info = i;}

    public String getUrl() {
        return Url;
    }

    public void setUrl(String u){Url = u;}

}
