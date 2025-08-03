package com.ritesh.notestakingapp;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> notes = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = findViewById(R.id.list);

        // Add sample notes
        notes.add("hello v2v");
        notes.add("student 1");

        // Correct adapter for ListView
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notes);
        listView.setAdapter(adapter);

        // Register context menu for list
        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem m) {
        Toast.makeText(this, "Delete Clicked", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem m) {
        if (m.getItemId() == R.id.addnote) {
            EditText et = new EditText(this);

            new AlertDialog.Builder(this)
                    .setTitle("Add Note")
                    .setMessage("Write the note you want")
                    .setView(et)
                    .setPositiveButton("Add", (dialog, which) -> {
                        String newNote = et.getText().toString().trim();
                        if (!newNote.isEmpty()) {
                            notes.add(newNote);
                            adapter.notifyDataSetChanged(); // refresh ListView
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        }
        return true;
    }
}
