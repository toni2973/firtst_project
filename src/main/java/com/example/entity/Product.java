package com.example.entity;

/**
 * Created by hhy on 11/19/16.
 */
public class Product {
    private int id;
    private String title;
    private double price;
    private String description;
    private String place;
    private String pic;
    private boolean freemail;




    public boolean isFreemail() {
        return freemail;
    }

    public void setFreemail(boolean freemail) {
        this.freemail = freemail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String decription) {
        this.description = decription;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getId() {  return id; }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}
