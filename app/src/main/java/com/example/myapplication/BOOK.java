package com.example.myapplication;

import java.io.Serializable;

public class BOOK implements Serializable {
    private int picId;
    private String name;

    public BOOK(int picId, String name){
        this.picId = picId;
        this.name = name;
    }

    public int getPicId(){
        return picId;
    }

    public String getName(){
        return name;
    }

    public void setPicId(int picId){
        this.picId = picId;
    }

    public void setName(String name){
        this.name = name;
    }
}