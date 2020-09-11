package com.shashi.firestoredemo1;

class JournalModelClass {

    private String title;
    private String thought;

    //We must have an empty constructor for Firestore
    public JournalModelClass() {
    }

    public JournalModelClass(String title, String thought) {
        this.title = title;
        this.thought = thought;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThought() {
        return thought;
    }

    public void setThought(String thought) {
        this.thought = thought;
    }
}
