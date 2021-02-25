package com.example.tugasulangansqlite;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.UserViewHolder> {
    List<Note> listNote;

    public RecyclerviewAdapter(List<Note> listNote) {
        this.listNote = listNote;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_adapter, parent, false);
        UserViewHolder userViewHolder = new UserViewHolder(view);

        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerviewAdapter.UserViewHolder holder, int position) {
        final Note note = listNote.get(position);
        holder.judul.setText(note.getJudul());
        holder.desk.setText(note.getDesk());
        holder.date.setText(note.getDate());
    }

    @Override
    public int getItemCount() {
        return listNote.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView date, judul, desk;
        private int id;
        private String tittle;
        private String desc;
        CardView cardView;
        private List<Note> listNote;


        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.listTanggal);
            judul = itemView.findViewById(R.id.listJudul);
            desk = itemView.findViewById(R.id.listdesc);

            cardView = itemView.findViewById(R.id.cardview);
            cardView.setOnLongClickListener(v -> {
                alertDialogAction(itemView.getContext(), getAdapterPosition());

                return false;
            });
        }

        public void alertDialogAction(Context context, int position) {
            String[] optionDialog = { "Edit", "Delete" };
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            DatabaseHelper databaseHelper = new DatabaseHelper(context);
            listNote = databaseHelper.selectNoteList();

            builder.setTitle("Choose Options");
            builder.setItems(optionDialog, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(which == 0) {
                        id = listNote.get(position).getId();
                        tittle = listNote.get(position).getJudul();
                        desc = listNote.get(position).getDesk();

                        Intent intent = new Intent(context, EditActivity.class);
                        intent.putExtra("id", id);
                        intent.putExtra("judul", tittle);
                        intent.putExtra("deskripsi", desc);
                        context.startActivity(intent);

                    } else {
                        DatabaseHelper databaseHelper = new DatabaseHelper(context);
                        databaseHelper.delete(listNote.get(position).getId());

                        listNote = databaseHelper.selectNoteList();
                        MainActivity Main = new MainActivity();
                        Main.setupRecyclerView(context, listNote,  MainActivity.recyclerView);
                        Toast.makeText(context, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }



}
