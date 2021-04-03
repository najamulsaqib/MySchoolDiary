package com.example.myschooldiary;

public class model {
    String  Description, Image, Topic, Id;
    public model(){

    }
    public model( String description, String image, String topic, String Id) {

        Description = description;
        Image = image;
        Topic = topic;
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
