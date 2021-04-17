package com.example.myschooldiary;

import java.util.Date;

public class model {
    String  Description, Image, Topic, Id, Day;
    Date date;
    public model(){

    }
    public model( String description, String image, String topic, String Id, Date date, String day) {
        this.Description = description;
        this.Image = image;
        this.Topic = topic;
        this.Id = Id;
        this.date = date;
        this.Day = day;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getTopic() {
        return Topic;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }
}
