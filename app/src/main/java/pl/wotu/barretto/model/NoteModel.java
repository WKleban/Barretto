package pl.wotu.barretto.model;

import com.google.firebase.firestore.GeoPoint;

import java.util.Date;

public class NoteModel {

    private String id;
    private String image_url;
    private String thumb_url;
    private String place;
    private String note;
    private String title;
    private String url;
    private Date timestamp;
    private GeoPoint location;
    private String userID;




    public NoteModel() {
    }

    public NoteModel(String image_url, String thumb_url, String place, String note, String title, String url, Date timestamp, GeoPoint location,int collectionSize) {
        this.image_url = image_url;
        this.thumb_url = thumb_url;
        this.place = place;
        this.note = note;
        this.title = title;
        this.url = url;
        this.timestamp = timestamp;
        this.location = location;
    }




    public void setId(String id) {
        this.id = id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getThumb_url() {
        return thumb_url;
    }

    public void setThumb_url(String thumb_url) {
        this.thumb_url = thumb_url;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public GeoPoint getLocation() {
        return location;
    }

    public void setLocation(GeoPoint location) {
        this.location = location;
    }

    public void setOwnerId(String userID) {
        this.userID=userID;
    }

    public String getOwnerId() {
        return userID;
    }

    public String getId() {
        return id;
    }
}
