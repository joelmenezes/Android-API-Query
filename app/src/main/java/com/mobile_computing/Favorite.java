package com.mobile_computing;

/**
 * Created by Joel on 25-01-2017.
 */

public class Favorite {
    private long id;
    private String title;
    private String date;
    private String image;
    private String text;

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }
    public String getImage(){
        return image;
    }

    public void setImage(String image){
        this.image = image;
    }
    public String getText(){
        return text;
    }

    public void setText(String text){
        this.text = text;
    }

    @Override
    //add other values
    public String toString(){
        return title;
    }

}
