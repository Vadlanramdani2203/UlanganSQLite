package com.example.tugasulangansqlite;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    static RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    List<Note> listNote;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.Listview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        setupRecyclerView();
    }

    public void setupRecyclerView() {
        DatabaseHelper db = new DatabaseHelper(this);
        listNote = db.selectNoteList();
        RecyclerviewAdapter adapter = new RecyclerviewAdapter(listNote);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public static void setupRecyclerView(Context context, List<Note> listNote, RecyclerView recyclerView) {
        DatabaseHelper db = new DatabaseHelper(context);
        listNote = db.selectNoteList();

        RecyclerviewAdapter adapter = new RecyclerviewAdapter(listNote);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    public void onClickAddItem(View view) {
        Intent intent = new Intent(getApplicationContext(), TambahActivity.class);
        startActivity(intent);
    }
}