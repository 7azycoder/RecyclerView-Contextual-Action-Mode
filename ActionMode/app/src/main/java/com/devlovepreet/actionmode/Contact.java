package com.devlovepreet.actionmode;

/**
 * Created by devlovepreet on 23/6/16.
 */
public class Contact {
    int img_id;
    String name,email;

    public  Contact(int img_id, String name,String email)
    {
        this.setImg_id(img_id);
        this.setName(name);
        this.setEmail(email);
    }

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
