package com.example.tugasulangansqlite;

public class Note {
    private int _id;
    private String judul;
    private String deskripsi;
    private String date;

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDesk() {
        return deskripsi;
    }

    public void setDesk(String desk) {
        this.deskripsi = desk;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
