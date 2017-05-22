package app.com.milipade.talitha_koum.newfarmhouse.Models;


import java.io.Serializable;

import app.com.milipade.talitha_koum.newfarmhouse.Core.User;


/**
 * Created by Lincoln on 07/01/16.
 */
public class Notification implements Serializable {
    String id, brif_message, createdAt,subject,imageUri;
    User user;

    public Notification() {
    }

    public Notification(String id, String brif_message, String subject,String createdAt,String imageUri, User user) {
        this.id = id;
        this.brif_message = brif_message;
        this.subject = subject;
        this.imageUri = imageUri;
        this.createdAt = createdAt;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrif_message() {
        return brif_message;
    }

    public void setBrif_message(String brif_message) {
        this.brif_message = brif_message;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
