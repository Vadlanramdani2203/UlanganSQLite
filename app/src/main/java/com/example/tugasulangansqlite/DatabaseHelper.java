package com.example.tugasulangansqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "Notes";
    private static final String TABLE_NAME = "tbl_notes";
    private static final String _ID = "_id";
    private static final String JUDUL = "judul";
    private static final String DESK = "deskripsi";
    private static final String DATE = "date";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createNotesTable="CREATE TABLE "+ TABLE_NAME +"("+ _ID +" INTEGER PRIMARY KEY,"
                + JUDUL + " TEXT NOT NULL," + DESK + " TEXT NOT NULL," + DATE + " TEXT NOT NULL" + ")";
        db.execSQL(createNotesTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql=("drop table if exists " + TABLE_NAME);
        db.execSQL(sql);
        onCreate(db);
    }

    public void insert(Note note) {
        SQLiteDatabase db =getWritableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        ContentValues values = new ContentValues();
        values.put(JUDUL, note.getJudul());
        values.put(DESK, note.getDesk());
        values.put(DATE, dateFormat.format(date));
        db.insert(TABLE_NAME, null ,values);
    }


    public List<Note> selectNoteList() {
        ArrayList<Note> noteList = new ArrayList<Note>();

        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {_ID, JUDUL, DESK, DATE};

        Cursor c = db.query(TABLE_NAME, columns, null, null, null, null, null);

        while (c.moveToNext()) {
            int id = c.getInt(0);
            String judul = c.getString(1);
            String desk = c.getString(2);
            String date = c.getString(3);

            Note note = new Note();
            note.setId(id);
            note.setJudul(judul);
            note.setDesk(desk);
            note.setDate(date);

            noteList.add(note);
        }

        return noteList;
    }

    public void update(Note note) {
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        values.put(JUDUL, note.getJudul());
        values.put(DESK, note.getDesk());
        values.put(DATE, dateFormat.format(date));
        String whereClause = _ID + " = ' " + note.getId() + "'";
        db.update(TABLE_NAME, values, whereClause, null);
    }

    public void delete(int id) {
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = _ID + "= '"+ id +"' ";
        db.delete(TABLE_NAME, whereClause,null);
    }
}
