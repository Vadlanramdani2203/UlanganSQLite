package com.example.tugasulangansqlite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class EditActivity extends AppCompatActivity {

    Button btnUpdate;
    Note note;
    Bundle intentData;
    TextInputLayout editJudul, editDesk;
    private String judul;
    private String desk;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editJudul = findViewById(R.id.editInputlayout);
        editDesk = findViewById(R.id.editInputlayout2);

        intentData = getIntent().getExtras();
        editJudul.getEditText().setText(intentData.getString("judul"));
        editDesk.getEditText().setText(intentData.getString("deskripsi"));

        btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(v -> {
            DatabaseHelper dbHelper = new DatabaseHelper(this);
            note = new Note();
            judul = editJudul.getEditText().getText().toString();
            desk = editDesk.getEditText().getText().toString();

            if(judul.isEmpty() && desk.isEmpty()) {
                Toast.makeText(this, "Data tidak boleh kosong !", Toast.LENGTH_SHORT).show();
            } else {
                note.setId(intentData.getInt("id"));
                note.setJudul(judul);
                note.setDesk(desk);

                Toast.makeText(this, "Data Berhasil Diubah", Toast.LENGTH_SHORT).show();

                dbHelper.update(note);

                Intent intent = new Intent(getApplicationContext(), MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                MainActivity main = new MainActivity();
                main.setupRecyclerView();
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        DatabaseHelper dbhelper = new DatabaseHelper(this);
        int ID = item.getItemId();

        if (ID == R.id.delete_action) {
            dbhelper.delete(intentData.getInt("id"));

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);

            Toast.makeText(this, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}