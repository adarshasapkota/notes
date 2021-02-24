package io.github.adarshasapkota.notes;

import java.io.Serializable;

public class Note implements Serializable {

    private final String note;
    private final String createdDate;
    private final String lastModifiedDate;
    private String id;

    public Note(String note, String createdDate, String lastModifiedDate) {
        this.note = note;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public Note(String id, String note, String createdDate, String lastModifiedDate) {
        this.id=id;
        this.note = note;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public String getId() {
        return id;
    }
}
