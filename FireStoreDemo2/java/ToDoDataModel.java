package com.shashi.cloudfirestor3;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

class ToDoDataModel implements Serializable {

    @Exclude
    private String documentId;

    private String title;
    private String description;

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


