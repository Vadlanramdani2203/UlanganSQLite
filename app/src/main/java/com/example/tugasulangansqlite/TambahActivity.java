package com.example.tugasulangansqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class TambahActivity extends AppCompatActivity {
    TextInputLayout inputJudul, inputDesk;
    Button btn;
    Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        inputJudul = findViewById(R.id.inputlayout);
        inputDesk = findViewById(R.id.inputlayout2);

        btn = findViewById(R.id.btntambah);
        btn.setOnClickListener(v -> {
            DatabaseHelper db = new DatabaseHelper(this);
            note = new Note();
            String judul = inputJudul.getEditText().getText().toString();
            String desk = inputDesk.getEditText().getText().toString();
            note.setJudul(judul);
            note.setDesk(desk);

            db.insert(note);

            Toast.makeText(this, "Data Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });
    }
}